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
import edu.la3ajltin.services.CommandeProduitsService;
import edu.la3ajltin.services.ProduitService;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.control.ListCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Mega-PC
 */
public class ProduitCellController extends ListCell<Produit> {
    
    @FXML
    private Button buyButton;
    @FXML
    private ImageView urlImage;
    @FXML
    private Label nomProduit;
    @FXML
    private Label marque;
    @FXML
    private Label quantite;
    @FXML
    private Label prix;
    private FXMLLoader mLLoader;
    @FXML
    private AnchorPane gridPane;


    /**
     * Initializes the controller class.
     */

    

    @Override
    public void updateItem(Produit produit, boolean empty) {
        ProduitService ps = new ProduitService();
        CommandeProduitsService Cs = new CommandeProduitsService();
        super.updateItem(produit,empty);
        if(empty || produit == null) {
            
            setText(null);
            setGraphic(null);

        } else {
            try {
                if (mLLoader == null) {
                    mLLoader = new FXMLLoader(getClass().getResource("/edu/la3ajltin/gui/ProduitCell.fxml"));
                    mLLoader.setController(this);
                    
                    try {
                        mLLoader.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    
                }
                
                
                javafx.scene.image.Image image= new javafx.scene.image.Image(getClass().getResource("/images/"+produit.getUrlImage()).toExternalForm());
                
                urlImage.setImage(image);
                nomProduit.setText(produit.getNom());
              /*  if(Cs.getQuantiteProduit(Session.getCurrentComm(), produit.getId_prod())==0){   
                quantite.setText("Stock disponible : "+String.valueOf(ps.getQuantiteAV(produit)));
                }
                else{
                    quantite.setText("Quantite : "+String.valueOf(Cs.getQuantiteProduit(Session.getCurrentComm(), produit.getId_prod())));
                }*/
                marque.setText(produit.getMarque());
                prix.setText(String.valueOf(produit.getPrix_prod()+" Dt"));
                if(ps.getQuantiteAV(produit)==0){
                    quantite.setStyle("-fx-text-fill : red");
                    buyButton.setDisable(true);
                }
                setText(null);
                setGraphic(gridPane);
            } catch (SQLException ex) {
                Logger.getLogger(ProduitCellController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    @FXML
    private void changeColorBuy(MouseEvent event){
        buyButton.setStyle("-fx-background-color : #f26722");
    }
    
    @FXML
    private void remainColorBuy(MouseEvent event){
        buyButton.setStyle("-fx-background-color : #3b3b3c");    }
    @FXML
    public void addProductToCart(ActionEvent event) throws Exception{
       
    }
   /* public void addCart(Produit p) throws Exception{
        
        int idUser = getCurrentSession();
        System.out.println(getProduit(p.getId_prod()));
        if((idUser != -1)&&(!getProduit(p.getId_prod()))){
            getPanier().add(p);
            p.setQteProduitPanier(1);
            
        } 
        else{
            System.out.println(getProduit(p.getId_prod()));
                for(int i=0;i<getPanier().size();i++){
          Produit produit= getPanier().get(i);
          if(Objects.equals(produit.getId_prod(), p.getId_prod())){
          produit.setQteProduitPanier(produit.getQteProduitPanier()+1);
          getPanier().set(i,produit); 
          }
                }
        }
    }*/
}
