/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.la3ajltin.tests;

import edu.la3ajltin.entities.Session;
import java.awt.*;
import java.awt.event.*;
import java.awt.TrayIcon.MessageType;
import java.net.MalformedURLException;
import edu.la3ajltin.services.fos_userService;
import edu.la3ajltin.tools.ConnectionDB;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Mega-PC
 */
public class LoginController implements Initializable {

    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private Button login;
    @FXML
    private Label isconnected;
    @FXML
    private Button closeButton;
    @FXML
    private Button minButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    public void login(ActionEvent Event) throws SQLException, IOException, Exception {

            login();
            if(Session.getUser().getRoles().contains("ROLE_ADMIN")){
            Stage stage = (Stage) login.getScene().getWindow();
            stage.close();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/edu/la3ajltin/gui/MainMenu.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.show();
            }else{
            Stage stage = (Stage) login.getScene().getWindow();
            stage.close();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/edu/la3ajltin/gui/MainMenuFront.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.show();
            }

    }
    public void login() throws SQLException, Exception{
        
        fos_userService fss = new fos_userService();

        if (fss.Authentification(username.getText(), password.getText())) {
            System.out.println(Session.getCurrentSession());
            System.out.println("EY");
            isconnected.setText("connecté");
                    } else {
            isconnected.setText("introuvable");
        }
        try {
                //Obtain only one instance of the SystemTray object
                SystemTray tray = SystemTray.getSystemTray();

                // If you want to create an icon in the system tray to preview
                Image image = Toolkit.getDefaultToolkit().createImage("C:\\Users\\Mega-PC\\Desktop\\NetBeansProjects\\3ala3ajltin\\src\\images\\oui.png");
                //Alternative (if the icon is on the classpath):
                //Image image = Toolkit.getDefaultToolkit().createImage(getClass().getResource("icon.png"));

                TrayIcon trayIcon = new TrayIcon(image, "Java AWT Tray Demo");
                //Let the system resize the image if needed
                trayIcon.setImageAutoSize(true);
                //Set tooltip text for the tray icon
                trayIcon.setToolTip("System tray icon demo");
                tray.add(trayIcon);

                // Display info notification:
                trayIcon.displayMessage("Hello, ".concat(username.getText()), "Vous êtes connecté", MessageType.INFO);
                // Error:
                // trayIcon.displayMessage("Hello, World", "Java Notification Demo", MessageType.ERROR);
                // Warning:
                // trayIcon.displayMessage("Hello, World", "Java Notification Demo", MessageType.WARNING);
            } catch (Exception ex) {
                System.err.print(ex);
            }
    }
    @FXML
    private void closeWindow(javafx.scene.input.MouseEvent event) {
        System.exit(0);
    }

    @FXML
    private void minimizeButton(javafx.scene.input.MouseEvent event) {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    private void changeColorClose(javafx.scene.input.MouseEvent event) {
        closeButton.setStyle("-fx-background-color: #bd0f0f;");
    }

    @FXML
    private void remainColorClose(javafx.scene.input.MouseEvent event) {
        closeButton.setStyle("-fx-background-color: transparent;");
    }

    @FXML
    private void changeColorMin(javafx.scene.input.MouseEvent event) {
        minButton.setStyle("-fx-background-color: #878484;");
    }

    @FXML
    private void remainColorMin(javafx.scene.input.MouseEvent event) {
        minButton.setStyle("-fx-background-color: transparent;");
    }

}
