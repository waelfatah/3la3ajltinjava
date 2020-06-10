/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.la3ajltin.gui;

import edu.la3ajltin.entities.Event;
import edu.la3ajltin.services.EventService;
import edu.la3ajltin.services.ReservationService;
import edu.la3ajltin.tools.ConnectionDB;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;


/**
 * FXML Controller class
 *
 * @author pc
 */
public class EventCellController extends ListCell<Event> {

    @FXML
    private AnchorPane gridPane;
    @FXML
    private Label nomProduit;
    @FXML
    private Label description;
    @FXML
    private Label quantite;
    @FXML
    private Label likes;
    @FXML
    private Label date;
    @FXML
    private Button buyButton;
    @FXML
    private Label adresse;
    @FXML
    private ImageView urlImage;
    private FXMLLoader mLLoader;
ConnectionDB db = ConnectionDB.getInstance();
            Connection cn = db.getCnx();
             public static int idUser;
    public static int idTest;
         
    /**
     * Initializes the controller class.
     */
    @Override
    
    public void updateItem(Event event, boolean empty) {
        EventService ps = new EventService();
        //ReservationService Cs = new ReservationService();
         
        super.updateItem(event,empty);
        if(empty || event == null) {
            
            setText(null);
            setGraphic(null);

        } else {
            
                if (mLLoader == null) {
                    mLLoader = new FXMLLoader(getClass().getResource("/edu/la3ajltin/gui/EventCell.fxml"));
                    mLLoader.setController(this);
                    
                    try {
                        mLLoader.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    
                }
                
                
                javafx.scene.image.Image image= new javafx.scene.image.Image(getClass().getResource("/images/"+event.getImage()).toExternalForm());
                
                urlImage.setImage(image);
                nomProduit.setText(event.getTitre());
               // if(Cs.getQuantiteProduit(Session.getCurrentComm(), produit.getId_prod())==0){   
                quantite.setText(String.valueOf(event.getNbrDePlace()));
               // }
               // else{
                    //quantite.setText("Quantite : "+String.valueOf(Cs.getQuantiteProduit(Session.getCurrentComm(), produit.getId_prod())));
               // }
                description.setText(event.getDescriptionEvent());
               date.setText(String.valueOf(event.getDateDebutEvent()));
              adresse.setText(event.getLieuEvent());
             // likes.setText(event.getTotallikesFromDB(event.getId())));
              setText(null);
              setGraphic(gridPane);
                
        }
    }
   
    
     @FXML
        private void Like(ActionEvent event) throws SQLException {
        
   }
        
     
    
           
    
    @FXML
    private void changeColorBuy(MouseEvent event){
        buyButton.setStyle("-fx-background-color : #f26722");
    }

    @FXML
    private void remainColorBuy(MouseEvent event) {
          buyButton.setStyle("-fx-background-color : #3b3b3c");  
    }

    @FXML
    private void addProductToCart(ActionEvent event) {
    }
    
    }    
