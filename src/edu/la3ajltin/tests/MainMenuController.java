/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.la3ajltin.tests;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Mega-PC
 */
public class MainMenuController implements Initializable {

    private Parent fxml;
    @FXML
    private Button gestProduit;

    @FXML
    private Label produit;

    @FXML
    private ScrollPane myInterface;

    @FXML
    private Button closeButton;

    @FXML
    private Button minButton;
    @FXML
    private Label Evenement;
    @FXML
    private Label offre;
    @FXML
    private Label blog;
    @FXML
    private Label entretiens;
    @FXML
    private Label reclamation;
    @FXML
    private Label livraison;
    @FXML
    private Button login;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }

    @FXML
    private void LoadGestionProduits(ActionEvent event) {
        try {
            produit.setStyle("-fx-background-color : #f26722");
            fxml = FXMLLoader.load(getClass().getResource("/edu/la3ajltin/gui/MenuProduit.fxml"));//YOUR FXML DOC          
            myInterface.setContent(fxml);
//            fxml.getScene().setFill(Color.TRANSPARENT);
//            fxml.getScene().getStylesheets().setAll(gestionassurance.First.class.getResource("/gestionassurance/gui/styles.css").toString());
        } catch (IOException ex) {

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
    private void changeColorClose(MouseEvent event) {
        closeButton.setStyle("-fx-background-color: #bd0f0f;");
    }

    @FXML
    private void remainColorClose(MouseEvent event) {
        closeButton.setStyle("-fx-background-color: #2e2d2d;");
    }

    @FXML
    private void changeColorMin(MouseEvent event) {
        minButton.setStyle("-fx-background-color: #878484;");
    }

    @FXML
    private void remainColorMin(MouseEvent event) {
        minButton.setStyle("-fx-background-color: #2e2d2d;");
    }

    @FXML
    private void allerFront(ActionEvent event) throws IOException {
        Stage stage = (Stage) login.getScene().getWindow();
        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/edu/la3ajltin/gui/MainMenuFront.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
    }

    @FXML
    private void LoadOnlineBack(MouseEvent event) throws IOException {
        String url = "http://localhost/3la3ajltin/web/app_dev.php/admin/";
        java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
    }

}
