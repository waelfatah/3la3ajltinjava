/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.la3ajltin.gui;

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
import javafx.scene.effect.BoxBlur;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author pc
 */
public class MenuEFController implements Initializable {

    @FXML
    private ScrollPane myInterface;
    private Parent fxml;
    private AnchorPane ajout;
    private AnchorPane afficher;
    private Label AffichageF;
    private Label AjoutE;
    private Label AffichageE;
    @FXML
    private Button ajoutF;
    @FXML
    private Button affichF;
    @FXML
    private Button ajoutE;
    @FXML
    private Button affichE;
    @FXML
    private Button resv;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public void changeAjoutColor(MouseEvent event) {
        ajout.setStyle("-fx-border-color : #ffffff");
        AjoutE.setStyle("-fx-text-fill : #f26722");
    }

    public void remainAjoutColor(MouseEvent event) {
        ajout.setStyle("-fx-border-color : #f26722");
        AjoutE.setStyle("-fx-text-fill : #ffffff");
    }

    public void changeAffColor(MouseEvent event) {
        afficher.setStyle("-fx-border-color : #ffffff");
        AffichageE.setStyle("-fx-text-fill : #f26722");
    }

    public void remainAffColor(MouseEvent event) {
        afficher.setStyle("-fx-border-color : #f26722");
        AffichageF.setStyle("-fx-text-fill : #ffffff");
    }

    public void changeStatColor(MouseEvent event) {
      
    }

    public void remainStatColor(MouseEvent event) {
       
    }

    @FXML
    private void openAjoutF(ActionEvent event) throws IOException {
        BoxBlur blur = new BoxBlur(3, 3, 3);

        myInterface.setEffect(blur);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AjoutFormation.fxml"));
        Parent parent = fxmlLoader.load();
        Scene scene = new Scene(parent);
        Stage stage = new Stage();

        stage.initStyle(StageStyle.TRANSPARENT);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
        myInterface.setEffect(null);
    }

    @FXML
    private void openAfficherF(ActionEvent event) throws IOException {
           fxml = FXMLLoader.load(getClass().getResource("DisplayFormation.fxml"));//YOUR FXML DOC          
            myInterface.setContent(fxml);
    }

    @FXML
    private void openAjoutE(ActionEvent event) throws IOException {
        BoxBlur blur = new BoxBlur(3, 3, 3);

        myInterface.setEffect(blur);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AjoutEvent.fxml"));
        Parent parent = fxmlLoader.load();
        Scene scene = new Scene(parent);
        Stage stage = new Stage();

        stage.initStyle(StageStyle.TRANSPARENT);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
        myInterface.setEffect(null);
    }

    @FXML
    private void openAfficherE(ActionEvent event)   throws IOException {
           fxml = FXMLLoader.load(getClass().getResource("DisplayEvenement.fxml"));//YOUR FXML DOC          
            myInterface.setContent(fxml);
    }
    @FXML
    private void openAfficherR(ActionEvent event)   throws IOException {
           fxml = FXMLLoader.load(getClass().getResource("DisplayReservations.fxml"));//YOUR FXML DOC          
            myInterface.setContent(fxml);
    }
}
