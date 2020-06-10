/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.la3ajltin.tests;

import edu.la3ajltin.entities.Produit;
//import static edu.la3ajltin.entities.Produit.getProduit;
import edu.la3ajltin.entities.Session;
import static edu.la3ajltin.entities.Session.getCurrentSession;
import static edu.la3ajltin.entities.Session.getPanier;
import edu.la3ajltin.services.ProduitService;
import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author Mega-PC
 */
public class ListProduitsCellController implements Initializable {

    @FXML
    private ListView<Produit> List;
    private ObservableList<Produit> data = FXCollections.observableArrayList();
    @FXML
    private TextField search;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ProduitService Ps = new ProduitService();
        data = Ps.afficherListeVelos();
        List.setItems(data);

        List.setCellFactory(ProduitListview -> new ProduitCellController());
    }

    @FXML
    private void Search(KeyEvent ke) {
        ProduitService Ps = new ProduitService();
        System.out.println("You searched smth!");
        String rech = search.getText();
        data = Ps.rechercherProduit(rech);
        List.setItems(data);
        List.setCellFactory(ProduitListview -> new ProduitCellController());
    }

}
