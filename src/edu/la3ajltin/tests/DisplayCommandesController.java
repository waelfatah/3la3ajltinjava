/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.la3ajltin.tests;

import edu.la3ajltin.entities.Commande;
import edu.la3ajltin.entities.Produit;
import edu.la3ajltin.entities.Session;
import edu.la3ajltin.services.CommandeService;
import edu.la3ajltin.services.ProduitService;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author Mega-PC
 */
public class DisplayCommandesController implements Initializable {

    @FXML
    private ListView<Commande> List;
    @FXML
    private ObservableList<Commande> data = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        CommandeService Cs = new CommandeService();
        try {
            data = Cs.afficherCommandes(Session.getCurrentSession());
        } catch (Exception ex) {
            Logger.getLogger(DisplayCommandesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        List.setItems(data);

        List.setCellFactory(CommandeListview -> new CommandeCellController());
    }

}
