/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.la3ajltin.gui;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import edu.la3ajltin.entities.Formation;
import edu.la3ajltin.services.FormationService;
import edu.la3ajltin.entities.Formulaire_Inscription;
import edu.la3ajltin.services.ReservationService;
import javafx.scene.control.ListCell;
import edu.la3ajltin.entities.Session;
import static edu.la3ajltin.entities.Session.getCurrentSession;
import static edu.la3ajltin.entities.Session.getPanier;
import static edu.la3ajltin.entities.Formation.getProduit;
import edu.la3ajltin.entities.Produit;
import edu.la3ajltin.services.ProduitService;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;

import java.util.Objects;
/**
 * FXML Controller class
 *
 * @author pc
 */
public class FormationCellController extends ListCell<Formation> {

    @FXML
    private AnchorPane gridPane;
    @FXML
    private ImageView urlImage;
    @FXML
    private Label nomProduit;
   
    @FXML
    private Label quantite;
   
    @FXML
    private Button buyButton;
    @FXML
    private Label adresse;
      private FXMLLoader mLLoader;
    @FXML
    private Label description;
    @FXML
    private Label date;

    /**
     * Initializes the controller class.
     */
    @Override
    
    public void updateItem(Formation formation, boolean empty) {
        FormationService ps = new FormationService();
        ReservationService Cs = new ReservationService();
         
        super.updateItem(formation,empty);
        if(empty || formation == null) {
            
            setText(null);
            setGraphic(null);

        } else {
            
                if (mLLoader == null) {
                    mLLoader = new FXMLLoader(getClass().getResource("/edu/la3ajltin/gui/FormationCell.fxml"));
                    mLLoader.setController(this);
                    
                    try {
                        mLLoader.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    
                }
                
                
                javafx.scene.image.Image image= new javafx.scene.image.Image(getClass().getResource("/images/"+formation.getImage()).toExternalForm());
                
                urlImage.setImage(image);
                nomProduit.setText(formation.getTitreF());
               // if(Cs.getQuantiteProduit(Session.getCurrentComm(), produit.getId_prod())==0){   
                quantite.setText(String.valueOf(formation.getNbrDePlaceF()));
               // }
               // else{
                    //quantite.setText("Quantite : "+String.valueOf(Cs.getQuantiteProduit(Session.getCurrentComm(), produit.getId_prod())));
               // }
                description.setText(formation.getDescriptionFormation());
               date.setText(String.valueOf(formation.getDateDebutFormation()));
              adresse.setText(formation.getLieuFormation());
              setText(null);
              setGraphic(gridPane);
                
        }
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
   public void addProductToCart(ActionEvent event) throws Exception{
        FormationService ps = new FormationService();
       Formation p = ps.getFormationparNom(nomProduit.getText());
        addCart(p);
               try {
            //Obtain only one instance of the SystemTray object
            SystemTray tray = SystemTray.getSystemTray();

            // If you want to create an icon in the system tray to preview
            Image image = Toolkit.getDefaultToolkit().createImage("oui.png");
            //Alternative (if the icon is on the classpath):
            //Image image = Toolkit.getDefaultToolkit().createImage(getClass().getResource("icon.png"));

            TrayIcon trayIcon = new TrayIcon(image, "Java AWT Tray Demo");
            //Let the system resize the image if needed
            trayIcon.setImageAutoSize(true);
            //Set tooltip text for the tray icon
            trayIcon.setToolTip("System tray icon demo");
            tray.add(trayIcon);

            // Display info notification:
            trayIcon.displayMessage("Produit, "+nomProduit.getText(), "Ajoutée avec succès dans le panier!!", TrayIcon.MessageType.INFO);
            // Error:
            // trayIcon.displayMessage("Hello, World", "Java Notification Demo", MessageType.ERROR);
            // Warning:
            // trayIcon.displayMessage("Hello, World", "Java Notification Demo", MessageType.WARNING);
        } catch (Exception ex) {
            System.err.print(ex);
        } 
        
    }
     public void addCart(Formation p) throws Exception{
        
       int idUser = getCurrentSession();
        System.out.println(getProduit(p.getId()));
        if((idUser != -1)&&(!getProduit(p.getId()))){
            getPanier().add(p);
            p.setQteFormationReservation(1);
            
        } 
        else{
            System.out.println(getProduit(p.getId()));
                for(int i=0;i<getPanier().size();i++){
          Formation produit= getPanier().get(i);
          if(Objects.equals(produit.getId(), p.getId())){
          produit.setQteFormationReservation(produit.getQteFormationReservation()+1);
          getPanier().set(i,produit); 
          }
                }
        }
    }
    
    }    

   

   

