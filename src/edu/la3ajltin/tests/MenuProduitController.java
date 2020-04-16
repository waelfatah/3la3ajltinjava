/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.la3ajltin.tests;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
 * @author Mega-PC
 */
public class MenuProduitController implements Initializable {

    @FXML
    private AnchorPane ajout;

    @FXML
    private AnchorPane afficher;

    @FXML
    private AnchorPane stat;
    @FXML
    private Label labProduit;
    @FXML
    private Label labAff;
    @FXML
    private Label labStat;
    @FXML
    private ScrollPane myInterface;
    private Parent fxml;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    public void changeAjoutColor(MouseEvent event) {
        ajout.setStyle("-fx-border-color : #ffffff");
        labProduit.setStyle("-fx-text-fill : #f26722");
    }

    @FXML
    public void remainAjoutColor(MouseEvent event) {
        ajout.setStyle("-fx-border-color : #f26722");
        labProduit.setStyle("-fx-text-fill : #ffffff");
    }

    @FXML
    public void changeAffColor(MouseEvent event) {
        afficher.setStyle("-fx-border-color : #ffffff");
        labAff.setStyle("-fx-text-fill : #f26722");
    }

    @FXML
    public void remainAffColor(MouseEvent event) {
        afficher.setStyle("-fx-border-color : #f26722");
        labAff.setStyle("-fx-text-fill : #ffffff");
    }

    @FXML
    public void changeStatColor(MouseEvent event) {
        stat.setStyle("-fx-border-color : #ffffff");
        labStat.setStyle("-fx-text-fill : #f26722");
    }

    @FXML
    public void remainStatColor(MouseEvent event) {
        stat.setStyle("-fx-border-color : #f26722");
        labStat.setStyle("-fx-text-fill : #ffffff");
    }

    @FXML
    void openAjoutProduit(MouseEvent event) throws IOException {
        BoxBlur blur = new BoxBlur(3, 3, 3);

        myInterface.setEffect(blur);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/edu/la3ajltin/gui/AddProduit.fxml"));
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
    private void openAfficherProduit(MouseEvent event) throws IOException {
           fxml = FXMLLoader.load(getClass().getResource("/edu/la3ajltin/gui/DisplayProduct.fxml"));//YOUR FXML DOC          
            myInterface.setContent(fxml);
    }

}
