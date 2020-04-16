/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.imageio.ImageIO;
import org.apache.commons.lang3.RandomStringUtils;
import static GUI.ArticleController.saveToFileImageNormal;
import Models.Article;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import org.controlsfx.control.textfield.TextFields;
import Models.ArticleJ;
import Models.ArticleListCell;
import Models.Client;
import Utils.DataAccessObject;
import Utils.SendMail;
import Services.Service_Client;
import Models.UserSession;
import Services.ArticleService;
import Utils.DBConnection;
import Utils.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.scene.input.KeyEvent;
/**
 * FXML Controller class
 *
 * @author Nour ghidaoui
 */
public class AccueilController implements Initializable {

    @FXML
    private AnchorPane parent;
    private AnchorPane articlesolo;
    private AnchorPane ajout;
    
    @FXML
    private Pane menu;
    @FXML
    private AnchorPane blog;
    @FXML
    private ListView<Article> articleList;
    @FXML
    private AnchorPane back;
    @FXML
    private Label articles;
    @FXML
    private JFXButton retourner;
    @FXML
    private JFXButton lireseul;
    @FXML
    private JFXButton ajouter;
    private String query,titre,description,image,photo; 
    @FXML
    private AnchorPane ajouterarticle;
    @FXML
    private Pane form;
  
    @FXML
    private JFXTextField txt_titre;
    @FXML
    private JFXTextField txt_description;
    @FXML
    private Label titrelabel;
    @FXML
    private JFXButton btn_import;
    @FXML
    private ImageView imgview;
    @FXML
    private JFXButton btn_confirmer;
     @FXML
    private JFXButton btn_discover;
        @FXML
    private ImageView velo;
    @FXML
    private JFXButton btn_retour;
    private  DataAccessObject dao = new DataAccessObject(); 
    private FXMLLoader loader;
    @FXML
    private Label rights;
    @FXML
    private Label to;
    @FXML
    private Label first;
    @FXML
    private Label world;
    @FXML
    private AnchorPane profil;
    @FXML
    private AnchorPane infos;
    @FXML
    private JFXTextField Prenom;
    @FXML
    private JFXTextField Nom;
    @FXML
    private JFXTextField Username;
    @FXML
    private JFXTextField Adresse;
    @FXML
    private JFXTextField Email;
    @FXML
    private JFXTextField Password;
    @FXML
    private JFXTextField Ntel;
    @FXML
    private ImageView icone;
    @FXML
    private Label info;
    @FXML
    private JFXButton modif;
    @FXML
    private JFXButton desactiver;
    @FXML
    private JFXButton retourner_profil;
     
    	private DBConnection database;
	private Connection connect;
     DataSource db = DataSource.getInstance();
     Connection cnx = db.getCnx();
    UserSession us; 
    Service_Client sc;

    @FXML
    private JFXButton deconnexion;
        protected static int idTest;
    @FXML
    private TextField input;
    @FXML
    private Label to1;
 

    /**
     * Initializes the controller class.
     */
     @Override
    public void initialize(URL url, ResourceBundle rb) {
         String[] possibleWords={"nanou","Salma","article","rrrrrr","rrrr","ddddddddddddd","aloooooooo","nour","journée","validation","nn","Safa monstre","belle","aventure"};
        TextFields.bindAutoCompletion(input, possibleWords);
      
     Client R = new Client();
        us = UserSession.getInstace();
        System.out.println(us);
        int id_user = us.getId();
        
        
           
      
        try {
            Load();
        } catch (SQLException ex) {
            Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   
      @FXML
    private void Search(KeyEvent ke) {
        ArticleService a = new ArticleService();
        System.out.println("You searched smth!");
        String rech = input.getText();
          System.out.println(rech);
        articleList.setItems(a.rechercherArticle(rech));
    }
   
    
   
     private void Load() throws SQLException {
      
         
      
        articleList.getItems().clear();
           
        
        ArticleController as = new ArticleController();
        
        
        ObservableList<Article> alist =as.getListArticle();
       
        articleList.setItems(alist);
        articleList.setCellFactory(new Callback<ListView<Article>, ListCell<Article>>() { 
       
       @Override 
            public ListCell<Article> call(ListView<Article> lv) { 
                return new ArticleListCell(); 
            }
            
        });     
     }
     
     
       
       
        
        @FXML
        private void addImage(ActionEvent event) throws IOException{
            FileChooser fc = new FileChooser();

            FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
            FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
            fc.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
            File selectedFile = fc.showOpenDialog(null);
            try {
                BufferedImage bufferedImage = ImageIO.read(selectedFile);
                  Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                 imgview.setImage(image);
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
        
        

         public static String saveToFileImageNormal(Image image)throws SQLException  {

            String ext = "jpg";
            File dir = new File("C:\\wamp64\\www\\PI\\web\\Uploads\\article\\photo");
            String name;
            name = String.format("%s.%s", RandomStringUtils.randomAlphanumeric(8), ext);
            File outputFile = new File(dir, name);
            BufferedImage bImage = SwingFXUtils.fromFXImage(image, null);
            try {
                ImageIO.write(bImage, "png", outputFile);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return name;
        }
         @FXML
     private void insertNewAccount() { // for adding new account
		titre = txt_titre.getText();
                Date date = Date.valueOf(java.time.LocalDate.now());
		description = txt_description.getText();
               Image image1=null;
             image1= imgview.getImage();
            String photo = null;
        try {
            photo = saveToFileImageNormal(image1);
        } catch (SQLException ex) {
            Logger.getLogger(ArticleController.class.getName()).log(Level.SEVERE, null, ex);
        }
	       query = "INSERT INTO article(Titre,Description,Date,id_user,image,updated_at,etat) VALUES('"+titre+"', '"+description+"', '"+date+"','"+UserSession.getCurrentSession()+"','"+photo+"','"+date+"','"+"en cours"+"');";
	

		dao.saveData(query);
            Alert successE = new Alert(Alert.AlertType.INFORMATION);
            successE.setTitle("Demande de publication");
            successE.setHeaderText(null);
            successE.setContentText("Votre demande de publication sera traitée dans les plus brefs délais, Merci de votre implication!");
            successE.showAndWait();		
		txt_titre.setText("");
		txt_description.setText("");
		
     }
     
     
     public Client Chercher(int id_user) throws SQLException {
        
        Client R = new Client();
        Service_Client sc = new Service_Client();
        R = sc.chercher(id_user);
        return R;
    }
     
     public void Afficher() throws SQLException {
        Client R = new Client();
        R = Chercher(us.getId());
       Username.setText(R.getUsername());

        Nom.setText(R.getNom());
       
        Prenom.setText(R.getPrenom());
    
        Email.setText(R.getEmail());
        
        Ntel.setText(R.getNum_tel());
        
        Adresse.setText(R.getAdresse());
        
        Password.setText(R.getPassword());
        
      
    }
 
     
     
     @FXML
    public void Modifier(ActionEvent event) throws SQLException {

        String email = Email.getText();
        String username = Username.getText();
        String password = Password.getText();
        String prenom = Prenom.getText();
        String nom = Nom.getText();
        String adresse = Adresse.getText();
        String num_tel = Ntel.getText();
        SendMail sm = new SendMail();
        Client P = new Client(us.getId(), email, username,password, prenom, nom, adresse, num_tel);
        Service_Client ss = new Service_Client();
        int i = 0;
        i = ss.update(P);
        System.out.println(i);
        if (i == 1) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Modifier Profil");
            alert.setHeaderText(null);
            alert.setContentText("Votre profil a été modifié");
            alert.showAndWait();
            TrayNotification tray =new TrayNotification();
            tray.setTitle("Succès");
        
        tray.setAnimationType(AnimationType.POPUP);
        tray.setNotificationType(NotificationType.INFORMATION);
        tray.showAndWait();
        }
    }
    @FXML
    private void Deconnexion(ActionEvent event) throws IOException {
        Node node;
        node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/GUI/Session.fxml")));
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    public void Desactiver(ActionEvent event) throws SQLException {
        
        Service_Client sc = new Service_Client();
        sc.Delete(us.getId());
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Désactivation du compte");
        alert.setHeaderText(null);
        alert.setContentText("Votre compte a été désactivé");
        alert.showAndWait();

    }
    private void table(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("/GUI/Session.fxml"));
        Scene tabbleViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(tabbleViewScene);
        window.show();

    }
    
    @FXML
    private void Read(ActionEvent event) throws IOException {
        if(articleList.getSelectionModel().getSelectedItem() != null){
            Article a = articleList.getSelectionModel().getSelectedItem();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/blogsolo.fxml"));
            Parent root = loader.load();
            articleList.getScene().setRoot(root);
            BlogsoloController blog = loader.getController();
            System.out.println(a.getTitre());
            
            blog.titreLabel.setText(a.getTitre());
           
            
            blog.descriptionLabel.setText(a.getDescription());
            Image imageURL= new Image("file:///C:/wamp64/www/PI/web/Uploads/article/photo/" + a.getImage());
            System.out.println(imageURL);
            blog.articleView.setImage(imageURL);
            BlogsoloController.idTest=a.getIdArticle();
          
    
        }
    }

      
     
     @FXML
          private void handlebuttonAction(ActionEvent event){
              if (event.getSource() == retourner)
           {
               menu.toFront();
           } 
              
               if (event.getSource() == btn_discover)
           {
               blog.toFront();
           } 
              
               if (event.getSource() == ajouter)
           {
               ajouterarticle.toFront();
           } 
               if (event.getSource() == btn_retour)
           {
               blog.toFront();
           } 
                if (event.getSource() == retourner_profil)
           {
               menu.toFront();
           } 
                
               
          }
          
          

    
    
          
}
