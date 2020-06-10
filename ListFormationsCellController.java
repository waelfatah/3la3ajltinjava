/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.la3ajltin.gui;

import edu.la3ajltin.entities.Formation;
import edu.la3ajltin.services.FormationService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author pc
 */
public class ListFormationsCellController implements Initializable {

    @FXML
    private ListView<Formation> List;
    private ObservableList<Formation> data=FXCollections.observableArrayList();
    @FXML
    private TextField search;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         FormationService Ps = new FormationService();
        data = Ps.afficherListeFormation();
        List.setItems(data);
        
        List.setCellFactory(FormationListview -> new FormationCellController());
    } 
    
    
    // TODO
    
     @FXML
    private void Search(KeyEvent ke) {
        FormationService Ps = new FormationService();
        System.out.println("You searched smth!");
        String rech = search.getText();
        data = Ps.rechercherFormation(rech);
        List.setItems(data);
        List.setCellFactory(FormationListview -> new FormationCellController());
    }

    }    
    

