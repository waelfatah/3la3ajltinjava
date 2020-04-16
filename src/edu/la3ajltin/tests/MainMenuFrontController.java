/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.la3ajltin.tests;

import edu.la3ajltin.entities.Session;
import edu.la3ajltin.entities.fos_user;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Mega-PC
 */
public class MainMenuFrontController implements Initializable {

    private Parent fxml;
    @FXML
    private HBox veloBox;
    @FXML
    private HBox accessBox;
    @FXML
    private Label veloLabel;
    @FXML
    private Label accessLabel;
    @FXML
    private HBox offresBox;
    @FXML
    private Label offresLabel;
    @FXML
    private HBox blogBox;
    @FXML
    private Label blogLabel;
    @FXML
    private HBox eventsBox;
    @FXML
    private Label eventsLabel;
    @FXML
    private HBox entretienBox;
    @FXML
    private Label entretienLabel;
    @FXML
    private HBox marquesBox;
    @FXML
    private Label marquesLabel;
    @FXML
    private HBox salesBox;
    @FXML
    private Label salesLabel;
    @FXML
    private HBox reccBox;
    @FXML
    private Label reccLabel;
    @FXML
    private Label enaWin;
    @FXML
    private Label enaWinKbir;
    @FXML
    private Button closeButton;
    @FXML
    private Button minButton;
    @FXML
    private Button gestionVélos;
    @FXML
    private ScrollPane myInterface;
    @FXML
    private Button meConnecter;
    @FXML
    private Label prenom;
    @FXML
    private Label nom;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            int idUser = Session.getCurrentSession();
            if (idUser != -1) {
                fos_user User = Session.getUser();
                prenom.setText(User.getPrenom());
                nom.setText(User.getNom());
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void changeColorVelo(MouseEvent event) {
        veloLabel.setStyle("-fx-background-color: #3b3b3c;");
    }

    private void remainColorVelo(MouseEvent event) {
        veloLabel.setStyle("-fx-background-color : transparent");
    }

    @FXML
    private void changeColorAccess(MouseEvent event) {
        accessLabel.setStyle("-fx-background-color: #3b3b3c;");
    }

    @FXML
    private void remainColorAccess(MouseEvent event) {
        accessLabel.setStyle("-fx-background-color : transparent");
    }

    @FXML
    private void closeWindow(MouseEvent event) {
        System.exit(0);
    }

    @FXML
    private void minimizeButton(MouseEvent event) {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    private void changeColorClose(MouseEvent event) {
        closeButton.setStyle("-fx-background-color: #bd0f0f;");
    }

    @FXML
    private void remainColorClose(MouseEvent event) {
        closeButton.setStyle("-fx-background-color: transparent;");
    }

    @FXML
    private void changeColorMin(MouseEvent event) {
        minButton.setStyle("-fx-background-color: #878484;");
    }

    @FXML
    private void remainColorMin(MouseEvent event) {
        minButton.setStyle("-fx-background-color: transparent;");
    }

    @FXML
    private void LoadGestionProduits(ActionEvent event) {
        try {
            enaWin.setText("Acceuil - Vélos");
            enaWinKbir.setText("Vélos");
            accessLabel.setStyle("-fx-background-color : transparent");
            veloLabel.setStyle("-fx-background-color : #3b3b3c");
            fxml = FXMLLoader.load(getClass().getResource("/edu/la3ajltin/gui/ListProduitsCell.fxml"));//YOUR FXML DOC          
            myInterface.setContent(fxml);
//            fxml.getScene().setFill(Color.TRANSPARENT);
//            fxml.getScene().getStylesheets().setAll(gestionassurance.First.class.getResource("/gestionassurance/gui/styles.css").toString());
        } catch (IOException ex) {

        }

    }

    @FXML
    private void LoadGestionAccessoires(ActionEvent event) {
        try {
            enaWin.setText("Acceuil - Accessoires");
            enaWinKbir.setText("Accessoires");
            veloLabel.setStyle("-fx-background-color : transparent");
            accessLabel.setStyle("-fx-background-color : #3b3b3c");
            fxml = FXMLLoader.load(getClass().getResource("/edu/la3ajltin/gui/ListAccessoireCell.fxml"));//YOUR FXML DOC          
            myInterface.setContent(fxml);
//            fxml.getScene().setFill(Color.TRANSPARENT);
//            fxml.getScene().getStylesheets().setAll(gestionassurance.First.class.getResource("/gestionassurance/gui/styles.css").toString());
        } catch (IOException ex) {

        }

    }

    @FXML
    private void LoadPanier(ActionEvent event) {
        try {
            enaWin.setText("Acceuil - Mon Panier");
            enaWinKbir.setText("Mon Panier");
            fxml = FXMLLoader.load(getClass().getResource("/edu/la3ajltin/gui/ListPanierCell.fxml"));//YOUR FXML DOC          
            myInterface.setContent(fxml);
//            fxml.getScene().setFill(Color.TRANSPARENT);
//            fxml.getScene().getStylesheets().setAll(gestionassurance.First.class.getResource("/gestionassurance/gui/styles.css").toString());
        } catch (IOException ex) {
        }
    }

    @FXML
    private void LoadGestionCommandes(ActionEvent event) {
        try {
            enaWin.setText("Acceuil - Mes Commandes");
            enaWinKbir.setText("Mes Commandes");
            marquesLabel.setStyle("-fx-background-color : #3b3b3c");
            fxml = FXMLLoader.load(getClass().getResource("/edu/la3ajltin/gui/DisplayCommandes.fxml"));//YOUR FXML DOC          
            myInterface.setContent(fxml);
//            fxml.getScene().setFill(Color.TRANSPARENT);
//            fxml.getScene().getStylesheets().setAll(gestionassurance.First.class.getResource("/gestionassurance/gui/styles.css").toString());
        } catch (IOException ex) {
        }
    }

    @FXML
    private void LoadOnlineShop(MouseEvent event) throws IOException {
         String url = "http://localhost/3la3ajltin/web/app_dev.php/shop/front/shop";
         java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
    }
}
