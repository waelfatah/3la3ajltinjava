/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.la3ajltin.gui;
import edu.la3ajltin.entities.Article;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.sql.Connection;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import doryan.windowsnotificationapi.fr.Notification;
import java.awt.AWTException;
import java.awt.TrayIcon;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import org.apache.commons.lang3.RandomStringUtils;

import edu.la3ajltin.entities.Fos_user_1;
import edu.la3ajltin.entities.UserSession;
import edu.la3ajltin.services.ArticleService;
import edu.la3ajltin.tools.DBConnection;
import edu.la3ajltin.tools.DataAccessObject;
import edu.la3ajltin.tools.DataSource;
import edu.la3ajltin.tools.SendMail;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import edu.la3ajltin.entities.Session;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Date;
import java.sql.ResultSetMetaData;
import javafx.scene.Parent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.DirectoryChooser;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;


public class ArticleController implements Initializable{

    @FXML
    private AnchorPane anchorpane_parent;
    @FXML
    private Pane pane_top;
    @FXML
    private Label label_title;
    @FXML
    private JFXTextField txt_description;
  
   
    @FXML
    private JFXButton btn_save;
    @FXML
    private TableView<Article> tblview;
    @FXML
    private TableColumn<Article,String> column_titre;
    @FXML
    private TableColumn<Article,String> column_description;
     @FXML
    private TableColumn<Article,Date> column_date;
     @FXML
    private TableColumn<Article,String> column_user;
     @FXML
    private TableColumn<Article,Integer> column_ID;
    @FXML
    private TableColumn<Article,String> column_statut;
  



    @FXML
    private AnchorPane anchorpane_center;
    @FXML
    private AnchorPane anchorpane_left;
    @FXML
    private JFXTextField txt_titre;
    @FXML
    private JFXButton btn_edit;
    @FXML
    private JFXButton btn_delete;
    @FXML
    private JFXButton btn_add_new;
    @FXML
    private AnchorPane anchorpane_right;
  
     @FXML
    private JFXButton btn_pdf;
    @FXML
    private JFXTextField prod_search;
      
    
    
    
    
    private FXMLLoader loader;
    private String query,titre,description;
    DataAccessObject dao;
     DataSource database = DataSource.getInstance();
     Connection cnx = database.getCnx();
    private boolean EDIT=false, ADD=true;
    private int ID;
    @FXML
    private JFXButton btn_print;
    
    @FXML
    private JFXButton btn_import;
   
    Connection con;
    @FXML
    private ImageView imgview;
    @FXML
    private JFXButton btn_mail;
    @FXML
    private Pane pane_mail;
    @FXML
    private JFXTextField tfEmailAddress;
    @FXML
    private JFXTextField tfObject;
    @FXML
    private JFXButton btn_send;
    @FXML
    private JFXButton btn_back;
    @FXML
    private JFXButton btn_stat;
    @FXML
    private Label label_email;
    @FXML
    private Label label_object;
    @FXML
    private Label label_text;
    @FXML
    private TextArea tfCorps;
        protected static int lastIdArticle=0;
    @FXML
    private JFXButton btn_rejeter;
    @FXML
    private JFXButton btn_confirmer;

    
@Override
   public void initialize(URL arg0, ResourceBundle arg1) {
       dao = new DataAccessObject(); 
       setCellValueFromTableToTextFieldprod();
        searchProduct();
        
     
       btn_save.setOnAction(e->{
           try {
               saveAccount();
           } catch (Exception ex) {
               Logger.getLogger(ArticleController.class.getName()).log(Level.SEVERE, null, ex);
           }
		});
       
       btn_edit.setOnAction(e->{
			ADD = false;
			EDIT = true;
			editAccount();
		});
  
       btn_add_new.setOnAction(e->{
			EDIT = false;
			ADD = true;
			insertNewAccount();
		});
       btn_delete.setOnAction(e->{
			deleteAccount();
		});

       refreshTable();
          Fos_user_1 f;
          Article a;
     
       
   }
   
    @FXML
    private void ExportToPDF(ActionEvent event) throws FileNotFoundException, IOException, SQLException, DocumentException {

        
        
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File selectedDirectory = directoryChooser.showDialog(new Stage());

        if (selectedDirectory == null) {
            Alert null_alert = new Alert(Alert.AlertType.ERROR);
            null_alert.setTitle(null);
            null_alert.setHeaderText(null);
            null_alert.setContentText("Aucun dossier séléctionné!");
            null_alert.showAndWait();
        } else {
            System.out.println(selectedDirectory.getAbsolutePath());
            String req = "SELECT * FROM article";
            PreparedStatement st;
            ResultSet rs;
            
            st = cnx.prepareStatement(req);
            rs = st.executeQuery(req);
            ResultSetMetaData rsmd = rs.getMetaData();
            int colNb = rsmd.getColumnCount();
            PdfPTable NamesRow = new PdfPTable(5);
            Document d = new Document(PageSize.A4.rotate());
            PdfWriter.getInstance(d, new FileOutputStream(selectedDirectory.getAbsolutePath() + "\\ListeArticle.pdf"));

            d.open();

            d.add(new Paragraph("Liste des articles:\n\n\n"));
            NamesRow.setWidthPercentage(100);
            NamesRow.setTotalWidth(new float[]{100, 100, 50, 50, 100});
            NamesRow.addCell("id_Article"); 
            NamesRow.addCell("Titre");
            NamesRow.addCell("Description");
           
            NamesRow.addCell("Date");
            NamesRow.addCell("id_User");
         
            NamesRow.addCell("Statut");
                
            

            d.add(NamesRow);
            while (rs.next()) {
                PdfPTable pt = new PdfPTable(5);
                pt.setWidthPercentage(100);
                pt.setTotalWidth(new float[]{100, 100, 50, 50, 100});
                pt.addCell("" + rs.getInt(1));
                pt.addCell("" + rs.getString(2));
                pt.addCell("" + rs.getString(3));
                pt.addCell("" + rs.getDate(4));
                pt.addCell("" + rs.getInt(5));
               
                pt.addCell("" + rs.getString(8));
               
                d.add(pt);
            }
            Alert Confirmation_Alert = new Alert(Alert.AlertType.INFORMATION); //Ok
            Confirmation_Alert.setTitle("EXPORTÉ!");
            Confirmation_Alert.setHeaderText(null);
            Confirmation_Alert.setContentText("Le tableau a été exporté en PDF avec succès!");
            Confirmation_Alert.show();

            d.close();
        }

    }
    
   @FXML   
    private void accepterArticle (ActionEvent event) throws IOException{
         if(tblview.getSelectionModel().getSelectedItem()!=null){
            Article r = tblview.getSelectionModel().getSelectedItem();
            ArticleService as=new ArticleService();
            as.setEtatConfirmer(r.getIdArticle());
            refreshTable();

            Alert successE = new Alert(Alert.AlertType.INFORMATION);
            successE.setTitle("Confirmation");
            successE.setHeaderText(null);
            successE.setContentText("article approuvé !");
            successE.showAndWait();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/la3ajltin/gui/Article.fxml"));
            Parent root = loader.load();
            tblview.getScene().setRoot(root);
        
         }
         
         }
    
    @FXML
    private void annulerArticle (ActionEvent event) throws IOException{
         if(tblview.getSelectionModel().getSelectedItem()!=null){
            Article r = tblview.getSelectionModel().getSelectedItem();
            ArticleService as=new ArticleService();
            as.setEtatIgnorer(r.getIdArticle());
            refreshTable();
            Alert successE = new Alert(Alert.AlertType.INFORMATION);
            successE.setTitle("Rejet");
            successE.setHeaderText(null);
            successE.setContentText("article rejeté !");
            successE.showAndWait();
             FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/la3ajltin/gui/Article.fxml"));
            Parent root = loader.load();
            tblview.getScene().setRoot(root);
        
         }
         
         
         
         
         
         }
  
   @FXML
   private void saveAccount() throws Exception { // for saving
    
		Date date = Date.valueOf(java.time.LocalDate.now());
                
		titre = txt_titre.getText();
		description = txt_description.getText();
               Image image1=null;
             image1= imgview.getImage();
            String photo = null;
        try {
            photo = saveToFileImageNormal(image1);
        } catch (SQLException ex) {
            Logger.getLogger(ArticleController.class.getName()).log(Level.SEVERE, null, ex);
        }
		
		if(EDIT) { // if edit button is pressed
			query = "UPDATE article SET titre='"+titre+"', description='"+description+"' WHERE idArticle="+ID+"";   
		        Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Modification d'un article");
                alert.setHeaderText(null);
                alert.setContentText("Vous venez de modifier un article");
                alert.showAndWait();
                }else if(ADD){ // if add button is pressed
                    System.out.println(UserSession.getCurrentSession());
			query = "INSERT INTO article(Titre,Description,Date,id_user,image,updated_at,etat) VALUES('"+titre+"', '"+description+"', '"+date+"',"+Session.getCurrentSession()+",'"+photo+"','"+date+"','en cours');";
                      
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Ajout d'un article");
                alert.setHeaderText(null);
                alert.setContentText("Vous venez d'ajouter un article");
                alert.showAndWait();
		}
		
		dao.saveData(query);
		
		txt_titre.setText("");
		txt_description.setText("");
		

		refreshTable();
		
		ADD = true;
	}
   
   @FXML
   private void deleteAccount() {
		Article selected = tblview.getSelectionModel().getSelectedItem();
		ID = selected.getIdArticle();
		query = "DELETE FROM article WHERE idArticle="+ID+"";
		dao.saveData(query);
		refreshTable();
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Suppression d'un article");
                alert.setHeaderText(null);
                alert.setContentText("Vous venez de supprimer un article");
                alert.showAndWait();

//
//                Node node = (Node) event.getSource();
//                Stage stage = (Stage) node.getScene().getWindow();
//                stage.close();
	}
   
   
   private void editAccount()
   {
       Article selected = tblview.getSelectionModel().getSelectedItem();
		ID = selected.getIdArticle();
		txt_titre.setText(selected.getTitre());
		txt_description.setText(selected.getDescription());
                
		
                
   }

  
   private void initTable() {
               
        column_titre.setCellValueFactory(new PropertyValueFactory<>("Titre"));
        column_description.setCellValueFactory(new PropertyValueFactory<>("Description"));
        column_date.setCellValueFactory(new PropertyValueFactory<>("Date")); 
        column_user.setCellValueFactory(new PropertyValueFactory<>("id_user"));
        column_ID.setCellValueFactory(new PropertyValueFactory<>("idArticle")); 
        column_statut.setCellValueFactory(new PropertyValueFactory<>("etat"));

                
                
	}
	
	private void refreshTable() {
		initTable();
		query = "SELECT a.id_user,a.idArticle,a.Titre, a.Description, a.date,a.etat,f.id FROM article as a " + 
				"JOIN fos_user as f ON a.id_user=f.id ";
		tblview.setItems(dao.getAccountsData2(query));
		
	}
        
         
        public ObservableList<Article> getListArticle(){
        ObservableList<Article> articleList = FXCollections.observableArrayList();
        String requete = "Select * from article  where etat='Confirmé'";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while(rs.next()) {
                Article nvA = new Article(rs.getInt("idArticle"), rs.getString("Titre"),rs.getString("Description"), rs.getString("etat"), rs.getString("image"),rs.getDate("date"),rs.getInt("id_user"));
                nvA.setNbDislike(getTotalDislikesFromDB(nvA.getIdArticle()));
                nvA.setPnbLikes(getTotalLikesFromDB(nvA.getIdArticle()));
              
                articleList.add(nvA);
           
            
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return articleList;
    }
        public int getTotalLikesFromDB(int idArticle) {
        try {
            Statement st = cnx.createStatement();
            String requete = "SELECT count(*) FROM likes where idArticle = " + idArticle;
            ResultSet rs = st.executeQuery(requete);

            while (rs.next()) {
                return rs.getInt("count(*)");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return 0;
        }
return 0;
    }
        
           public int getTotalDislikesFromDB(int idArticle) {
        try {
            Statement st = cnx.createStatement();
            String requete = "SELECT count(*) FROM dislike where idArticle = " + idArticle;
            ResultSet rs = st.executeQuery(requete);

            while (rs.next()) {
                return rs.getInt("count(*)");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return 0;
        }
return 0;
    }
        
       
     private void insertNewAccount() { // for adding new account
		txt_titre.setText("");
		txt_description.setText("");
                
		
	}
    
     public void searchProduct(){
         
prod_search.setOnKeyReleased(e->{
    if(prod_search.getText().equals("")){
        
       refreshTable();
    }
    else{
       initTable();
          String sql = "Select * from article where Titre LIKE '%"+prod_search.getText()+"%'"
                + "UNION Select * from article where Description LIKE '%"+prod_search.getText()+"%'" ;
        
        tblview.setItems(dao.getAccountsDat4(sql));
    
    
    }
});
}


      
       
    
       
        
        @FXML
        private void showStat() {
		try {
			loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("Stat.fxml"));
			StatController controller = new StatController();
			loader.setController(controller);
			loader.load();
			Scene scene = new Scene(loader.getRoot());
			//scene.getStylesheets().add(getClass().getResource("Position.css").toExternalForm());
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.show();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
        
        
        
        @FXML
          private void handlebuttonAction(ActionEvent event){
              if (event.getSource() == btn_mail)
           {
               pane_mail.toFront();
           } 
                 else if (event.getSource() == btn_back)
           {
               anchorpane_center.toFront();
           } 
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
         private void setCellValueFromTableToTextFieldprod(){
              tblview.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
    Article art=tblview.getItems().get(tblview.getSelectionModel().getSelectedIndex());

    //tf_rate.setText(idprod);


           
                TableColumn.CellEditEvent edittedcell = null;
               Article ar=gettemp(edittedcell);  
          System.out.println(ar);
                String photo=ar.getImage();
                        System.out.println(photo);
            Image imageURL= new Image("file:///C:/wamp64/www/PI/web/Uploads/article/photo/" + photo);
            System.out.println(imageURL);
            imgview.setImage(imageURL);}
    }); 
         }
    public Article gettemp(TableColumn.CellEditEvent edittedCell) {
        Article test = tblview.getSelectionModel().getSelectedItem();
      
        return test;
    }
    
    @FXML
    public void sendmail() throws AWTException, MalformedURLException {

        String mail = (String) tfEmailAddress.getText();
        String Objet = (String) tfObject.getText();
        String Corp = (String) tfCorps.getText();
        SendMail.sendMail(mail, Objet, Corp);

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText("The Email Has Been Sent ");
        alert.showAndWait();

        Notification.sendNotification("The Email has been sent .", TrayIcon.MessageType.INFO);

    }
    
//    public void incrementNbLikes(int article_ID){
//         String requete="UPDATE articlej SET pnbLikes=pnbLikes+1  WHERE article_ID="+article_ID;
//         try{
//            Statement st = cnx.createStatement();
//            st.executeUpdate(requete);
//            System.out.println("like added");
//            
//        } catch (SQLException ex) {
//              }
//     }
//    
//     public void incrementNbDislike(int article_ID){
//         String requete="UPDATE articlej SET nbDislike=nbDislike+1  WHERE article_ID="+article_ID;
//         try{
//            Statement st = cnx.createStatement();
//            st.executeUpdate(requete);
//            System.out.println("Dislike added");
//            
//        } catch (SQLException ex) {
//              }
//     }
    public void likerArticle(int article_ID,int idU) throws SQLException{
         Date date = Date.valueOf(java.time.LocalDate.now());
        if(!verifLike(article_ID,idU)){
           
                
        String requete = "INSERT into likes(idArticle,idUser,Date)values ('"+article_ID+"','"+idU+"','"+date+"')";
        try{
        Statement st = cnx.createStatement();
            st.executeUpdate(requete);
           // System.out.println("like ajouté");
        } catch (SQLException ex) {
        }
        //incrementNbLikes(article_ID);
        }else{
             Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Rate  article");
                alert.setHeaderText(null);
                alert.setContentText("Vous aimez déja cet article");
                alert.showAndWait();
        }
    }
    
    public void DislikerArticle(int article_ID,int idU) throws SQLException{
         Date date = Date.valueOf(java.time.LocalDate.now());
        if(!verifDislike(article_ID,idU)){
        String requete = "INSERT into Dislike(idArticle,idUser,Date)values ('"+article_ID+"','"+idU+"','"+date+"')";
        try{
        Statement st = cnx.createStatement();
            st.executeUpdate(requete);
           // System.out.println("like ajouté");
        
        } catch (SQLException ex) {
        }
        //incrementNbDislike(article_ID);
        }else{
             Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Rate  article");
                alert.setHeaderText(null);
                alert.setContentText("Vous n'aimez pas déja cet article");
                alert.showAndWait();
        }
        
    }
    public boolean verifLike(int article_ID,int idU) throws SQLException{
        String requete = "select * from likes WHERE idArticle="+article_ID+" AND idUser="+idU;
        int idLike=0;
        try{
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while(rs.next()){
                idLike=rs.getInt(1);
              
            }
        }catch (SQLException ex){
         dao.saveData(query);                }
        if(idLike == 0){
            return false;}
        else {return true;  } 
        }
    
    
     public boolean verifDislike(int article_ID,int idU) throws SQLException{
        String requete = "select * from dislike WHERE idArticle="+article_ID+" AND idUser="+idU;
        int idDislike=0;
        try{
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while(rs.next()){
                idDislike=rs.getInt(1);
              
            }
        }catch (SQLException ex){
         dao.saveData(query);                }
        if(idDislike == 0){
            return false;}
        else {return true;  } 
        }

    
}
   
   
