/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.la3ajltin.gui;

import edu.la3ajltin.entities.Event;
import edu.la3ajltin.services.EventService;
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
public class ListEventsCellController implements Initializable {

    @FXML
    private ListView<Event> List;
     private ObservableList<Event> data=FXCollections.observableArrayList();
    @FXML
    private TextField search;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          EventService Ps = new EventService();
        data = Ps.afficherListeEvent();
        List.setItems(data);
        
        List.setCellFactory(EventListview -> new EventCellController());
        // TODO
    }    

    @FXML
    private void Search(KeyEvent event) {
          EventService Ps = new EventService();
        System.out.println("You searched smth!");
        String rech = search.getText();
        data = Ps.rechercherEvent(rech);
        List.setItems(data);
        List.setCellFactory(EventListview -> new EventCellController());
    }
    
}
 
    
