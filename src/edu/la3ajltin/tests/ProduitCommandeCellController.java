/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.la3ajltin.tests;

import edu.la3ajltin.entities.Commande;
import edu.la3ajltin.entities.Produit;
import edu.la3ajltin.entities.Session;
import edu.la3ajltin.services.CommandeProduitsService;
import edu.la3ajltin.services.ProduitService;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Mega-PC
 */
public class ProduitCommandeCellController implements Initializable {

    @FXML
    private ListView<Produit> List;
    private ObservableList<Produit> data = FXCollections.observableArrayList();
    @FXML
    private Label idCommande;
    @FXML
    private Label prixTotal;
    @FXML
    private Label date;
    @FXML
    private Button close;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            Commande comm = Session.getCommande(Session.getCurrentComm());
            idCommande.setText(String.valueOf(comm.getId_commande()));
            prixTotal.setText(String.valueOf(comm.getPrix_total()));
            date.setText(String.valueOf(comm.getDate()));
            CommandeProduitsService Ps = new CommandeProduitsService();
            data = Ps.afficherProduitsCommandes(Session.getCurrentComm());
            System.out.println(data.toString());
            List.setItems(data);
            List.setCellFactory(ProduitListview -> new ProduitCellController());
        } catch (Exception ex) {
            Logger.getLogger(ProduitCommandeCellController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void closeWindow(MouseEvent event) {
        Stage stage = (Stage) close.getScene().getWindow();
        stage.close();
    }

}
