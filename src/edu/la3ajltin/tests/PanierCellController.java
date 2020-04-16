/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.la3ajltin.tests;

import edu.la3ajltin.entities.Produit;
import static edu.la3ajltin.entities.Session.getPanier;
import edu.la3ajltin.services.ProduitService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.ListCell;
import org.apache.commons.lang3.ArrayUtils;
/**
 * FXML Controller class
 *
 * @author Mega-PC
 */
public class PanierCellController extends ListCell<Produit> {

    @FXML
    private AnchorPane gridPane;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void updateItem(Produit produit, boolean empty){
        super.updateItem(produit,empty);
                if(empty || produit == null) {

            setText(null);
            setGraphic(null);

        } else {
            if (mLLoader == null) {
                mLLoader = new FXMLLoader(getClass().getResource("/edu/la3ajltin/gui/PanierCell.fxml"));
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
                quantite.setText("Quantite : "+String.valueOf(produit.getQteProduitPanier()));
                marque.setText(produit.getMarque());
                prix.setText(String.valueOf(produit.getQteProduitPanier()*produit.getPrix_prod()+" Dt"));
                
                setText(null);
                setGraphic(gridPane);
                }
                
    }
    
    @FXML 
    public void add(ActionEvent event) throws SQLException{
       ProduitService ps = new ProduitService();
       Produit p = ps.getProduitparNom(nomProduit.getText());
       zid(p);
       
    }
    
        @FXML 
    public void remove(ActionEvent event) throws SQLException{
       ProduitService ps = new ProduitService();
       Produit p = ps.getProduitparNom(nomProduit.getText());
       nakes(p);
       
    }
    public void zid(Produit p){
          for(int i=0;i<getPanier().size();i++){
          Produit produit= getPanier().get(i);
          if(produit.getId_prod()==p.getId_prod()){
          produit.setQteProduitPanier(produit.getQteProduitPanier()+1);
          getPanier().set(i,produit); 
         
          }
          }
          
    }
    
        public void nakes(Produit p){
          for(int i=0;i<getPanier().size();i++){
          Produit produit= getPanier().get(i);
          if(produit.getId_prod()==p.getId_prod()){
              produit.setQteProduitPanier(produit.getQteProduitPanier()-1);
              if(produit.getQteProduitPanier() == 0){
                 getPanier().remove(i);
              }
              else{
          getPanier().set(i,produit); 
              }
          }
          }
          
    }
}
