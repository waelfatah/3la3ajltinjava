/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.la3ajltin.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.sql.ResultSet;
import edu.la3ajltin.entities.UserSession;
import java.sql.Statement;

import com.jfoenix.controls.JFXButton;

import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXPasswordField;
import java.awt.BorderLayout;
import java.io.IOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;

import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import edu.la3ajltin.entities.Admin;
import edu.la3ajltin.entities.Client;
import edu.la3ajltin.tools.DBConnection;
import edu.la3ajltin.tools.DataAccessObject;
import edu.la3ajltin.tools.PasswordUtils;
import edu.la3ajltin.tools.SendMail1;
import edu.la3ajltin.services.ServiceAdmin;
import edu.la3ajltin.services.Service_Client;
import edu.la3ajltin.entities.UserSession;

public class SessionController implements Initializable {

    @FXML
    private AnchorPane anchorpane_parent;
    @FXML
    private AnchorPane anchorpane_login;
    @FXML
    private AnchorPane anchorpane_accueil;
    @FXML
    private JFXButton btn_confirm;
    @FXML
    private JFXButton btn_signup;
    @FXML
    private JFXTextField txt_username;
    @FXML
    private JFXPasswordField txt_mdp;
    @FXML
    private Label welcome;
    @FXML
    private Label slogan;
    @FXML
    private Label dont;
    DataAccessObject dao;
    private DBConnection database;
    private Connection connect;
    @FXML

    private ImageView image;
    UserSession US;
    Connection con;
    PreparedStatement preparedStatement;
    ResultSet resultSet;
    private Statement ste;
    Label idtext;
    PasswordUtils p = new PasswordUtils();
    @FXML
    private Label signin;
    @FXML
    private AnchorPane mur;
    @FXML
    private Label errorLogin;
    @FXML
    private Label errorPass;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        US = UserSession.getInstace();
        System.out.println(US);
        SendMail1 s = new SendMail1();
        int code = s.GenerateCode();
        System.out.println(code);
        btn_signup.setOnAction(e -> {
            SignUp();
        });
    }

    @FXML
    public void Signin(ActionEvent event) throws IOException, SQLException, InterruptedException {
        String username = txt_username.getText();
        String password = txt_mdp.getText();
        int verif1 = 2;

        Client C = new Client(username, password);
        C.setUsername(username);
        C.setPassword(password);

        Service_Client SA = new Service_Client();
        System.out.println(C.getPassword());
        verif1 = SA.Singin(C);
        System.out.println(verif1);
        if (verif1 == 3) {
            String nom = "";
            String prenom = "";
            int id = 0;
            US = UserSession.getInstace(nom, prenom, id);
            US.setNom(C.Session(C).getNom());
            US.setPrenom(C.Session(C).getPrenom());
            US.setId(C.Session(C).getid());
            BlogsoloController.idUser = C.getid();
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.close();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/edu/la3ajltin/gui/MainMenuFront.fxml")));

            stage.setScene(scene);
            stage.show();

        } else if (verif1 == 2) {
            Admin A = new Admin(username, password);
            A.setUsername(username);
            A.setPassword(password);
            String nom = "";
            String prenom = "";
            int id = 0;
            US = UserSession.getInstace(nom, prenom, id);
            US.setNom(A.Session(A).getNom());
            US.setPrenom(A.Session(A).getPrenom());
            US.setId(A.Session(A).getid());
            TestController.idUser = A.getid();
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.close();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/edu/la3ajltin/gui/MainMenu.fxml")));
            scene.getStylesheets().add(getClass().getResource("/edu/la3ajltin/gui/Article.css").toExternalForm());
            stage.setScene(scene);
            stage.show();

        }

    }

    public void Host(ActionEvent event) throws IOException, SQLException {
        String username = txt_username.getText();
        String password = txt_mdp.getText();
        int verif1 = 0;

        Admin C = new Admin(username, password);
        C.setUsername(username);
        C.setPassword(password);
        ServiceAdmin SA = new ServiceAdmin();
        System.out.println(C);
        verif1 = SA.SingIn(C);
        System.out.println(verif1);
        if (verif1 == 3) {
            String nom = "";
            String prenom = "";
            int id = 0;
            US = UserSession.getInstace(nom, prenom, id);
            US.setNom(C.Session(C).getNom());
            US.setPrenom(C.Session(C).getPrenom());
            US.setId(C.Session(C).getid());
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.close();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/edu/la3ajltin/gui/Test.fxml")));
            stage.setScene(scene);
            stage.show();
        }

    }

    @FXML
    private void SignUp() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/edu/la3ajltin/gui/SignUp.fxml"));
            SignUpController controller = new SignUpController();
            loader.setController(controller);
            loader.load();
            Scene scene = new Scene(loader.getRoot());

            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void handleInscription() throws IOException {
        SessionController.showInscription();
    }

    public static boolean showInscription() throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(SessionController.class.getResource("/edu/la3ajltin/gui/SignUp.fxml"));
        AnchorPane page = (AnchorPane) loader.load();
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Remplissez le formulaire d'inscription");
        dialogStage.initModality(Modality.WINDOW_MODAL);

        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        dialogStage.showAndWait();
        return false;
    }

}
