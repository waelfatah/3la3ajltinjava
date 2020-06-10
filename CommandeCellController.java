/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.la3ajltin.tests;

import edu.la3ajltin.entities.Commande;
import edu.la3ajltin.entities.Produit;
import edu.la3ajltin.entities.Session;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
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
public class CommandeCellController extends ListCell<Commande> {

    @FXML
    private Label idCommande;
    @FXML
    private Hyperlink details;
    @FXML
    private Label prixTotal;
    @FXML
    private Label date;
    private FXMLLoader mLLoader;
    @FXML
    private AnchorPane gridPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void updateItem(Commande commande, boolean empty) {
        super.updateItem(commande, empty);
        if (empty || commande == null) {

            setText(null);
            setGraphic(null);

        } else {
            if (mLLoader == null) {
                mLLoader = new FXMLLoader(getClass().getResource("/edu/la3ajltin/gui/CommandeCell.fxml"));
                mLLoader.setController(this);

                try {
                    mLLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            idCommande.setText(String.valueOf(commande.getId_commande()));
            
            prixTotal.setText(String.valueOf(commande.getPrix_total()));
            date.setText(String.valueOf(commande.getDate()));

            setText(null);
            setGraphic(gridPane);
        }

    }

    public void details(MouseEvent event) throws IOException {
        Session.setIdComm(Integer.valueOf(idCommande.getText()));
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/edu/la3ajltin/gui/ProduitCommandeCell.fxml"));
        Parent parent = fxmlLoader.load();
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
    }

}
