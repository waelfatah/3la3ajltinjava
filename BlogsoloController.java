/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import static GUI.AccueilController.idTest;

/**
 * FXML Controller class
 *
 * @author Nour ghidaoui
 */
public class BlogsoloController implements Initializable {

    @FXML
    protected AnchorPane blogsolo;
    @FXML
    protected ImageView articleView;
    @FXML
    protected JFXButton backButton;
    @FXML
    protected Label titreLabel;
 
    @FXML
    protected JFXButton likeButton;
    @FXML
    private AnchorPane article;
    @FXML
    protected Label lieuLabel;
    @FXML
    protected Label descriptionLabel;
    @FXML
    protected ImageView velo;
    @FXML
    private Label evaluer;
   
    public static int idUser;
    public static int idTest;
    @FXML
    private JFXButton dislikeButton;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
     
    @FXML
        private void Like(ActionEvent event) throws SQLException {
        ArticleController as = new ArticleController();
        as.likerArticle(idTest, idUser);
        
   }
        
         @FXML
        private void Dislike(ActionEvent event) throws SQLException {
        ArticleController as = new ArticleController();
        as.DislikerArticle(idTest, idUser);
   }
        
         @FXML
    private void Back(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/Accueil.fxml"));
        Parent root = loader.load();
        titreLabel.getScene().setRoot(root);
    }
}