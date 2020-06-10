/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.la3ajltin.tests;

import edu.la3ajltin.entities.Formation;
import static edu.la3ajltin.entities.Session.getPanier;
import edu.la3ajltin.services.FormationService;
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
//import org.apache.commons.lang3.ArrayUtils;
/**
 * FXML Controller class
 *
 * @author Mega-PC
 */
public class PanierCellController extends ListCell<Formation> {

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
    public void updateItem(Formation formation, boolean empty){
        super.updateItem(formation,empty);
                if(empty || formation == null) {

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
            
                
                javafx.scene.image.Image image= new javafx.scene.image.Image(getClass().getResource("/images/"+formation.getImage()).toExternalForm());
                
                urlImage.setImage(image);
                nomProduit.setText(formation.getTitreF());
                quantite.setText("Quantite : "+String.valueOf(formation.getQteFormationReservation()));
                marque.setText(formation.getLieuFormation());
                prix.setText(String.valueOf(formation.getDateDebutFormation()));
                
                setText(null);
                setGraphic(gridPane);
                }
                
    }
    
    @FXML 
    public void add(ActionEvent event) throws SQLException{
       FormationService ps = new FormationService();
       Formation p = ps.getFormationparNom(nomProduit.getText());
       zid(p);
       
    }
    
        @FXML 
    public void remove(ActionEvent event) throws SQLException{
       FormationService ps = new FormationService();
       Formation p = ps.getFormationparNom(nomProduit.getText());
       nakes(p);
       
    }
    public void zid(Formation p){
          for(int i=0;i<getPanier().size();i++){
          Formation formation= getPanier().get(i);
          if(formation.getId()==p.getId()){
          formation.setQteFormationReservation(formation.getQteFormationReservation()+1);
          getPanier().set(i,formation); 
         
          }
          }
          
    }
    
        public void nakes(Formation p){
          for(int i=0;i<getPanier().size();i++){
          Formation formation= getPanier().get(i);
          if(formation.getId()==p.getId()){
              formation.setQteFormationReservation(formation.getQteFormationReservation()-1);
              if(formation.getQteFormationReservation() == 0){
                 getPanier().remove(i);
              }
              else{
          getPanier().set(i,formation); 
              }
          }
          }
          
    }
}
