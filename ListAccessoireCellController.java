/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.la3ajltin.tests;

import edu.la3ajltin.entities.Produit;
import edu.la3ajltin.services.ProduitService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

/**
 * FXML Controller class
 *
 * @author Mega-PC
 */
public class ListAccessoireCellController implements Initializable {

      @FXML
    private ListView<Produit> List;
        @FXML
    private ObservableList<Produit> data=FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ProduitService Ps = new ProduitService();
        data = Ps.afficherListeProduits();
        List.setItems(data);
        
        List.setCellFactory(ProduitListview -> new ProduitCellController());
    } 
    
}
