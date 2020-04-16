/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
import static GUI.ArticleController.saveToFileImageNormal;
import Models.Article;
import Models.Fos_user;
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


import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.List;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import Utils.DBConnection;
import Utils.DataAccessObject;
import Models.Test;
import Utils.DataSource;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.jfoenix.controls.JFXPasswordField;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import org.apache.commons.lang3.RandomStringUtils;



public class TestController implements Initializable{
    public static int idUser ;  
    @FXML
    private AnchorPane anchorpane_parent;
    @FXML
    private Pane pane_top;
    @FXML
    private Label label_title;
    @FXML
    private JFXButton btn_save;
    @FXML
    private TableView<Fos_user> tblview;
    @FXML
    private AnchorPane anchorpane_center;
    @FXML
    private AnchorPane anchorpane_left;
    @FXML
    private JFXButton btn_edit;
    @FXML
    private JFXButton btn_delete;
    @FXML
    private JFXButton btn_add_new;
    @FXML
    private AnchorPane anchorpane_right;
    @FXML
    private ImageView imgview;
    
    private String roles [] = {"User", "Admin"};
    private FXMLLoader loader;
    private String query,nom,prenom,role,mdp,email,username,id;
    DataAccessObject dao;
    private DBConnection database;
    private Connection connect;
    private boolean EDIT=false, ADD=true;
    private int ID;
    @FXML
    private JFXTextField txt_nom;
    @FXML
    private JFXTextField txt_email;
    @FXML
    private JFXPasswordField txt_password;
    DataSource dataBase = DataSource.getInstance();
    Connection cnx = dataBase.getCnx();
    @FXML
    private JFXTextField txt_prenom;
    @FXML
    private JFXTextField txt_username;
    @FXML
    private JFXComboBox<String> combo_role;
    @FXML
    private JFXButton btn_print;
    @FXML
    private TableColumn<Fos_user,String> column_nom;
    @FXML
    private TableColumn<Fos_user,String> column_prenom;
    @FXML
    private TableColumn<Fos_user,String> column_username;
    @FXML
    private TableColumn<Fos_user,String> column_role;
    @FXML
    private TableColumn<Fos_user,String> column_mdp;
     @FXML
    private TableColumn<Fos_user,String> column_email;
     @FXML
    private TableColumn<Fos_user,Integer> column_ID;
    @FXML
    private JFXTextField prod_search;
    
@Override
   public void initialize(URL arg0, ResourceBundle arg1) {
       dao = new DataAccessObject();
       initRole();
        searchProduct();
      
       btn_save.setOnAction(e->{
			saveAccount();
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
		
 
       combo_role.getSelectionModel().select(0);
       refreshTable();
       
   }
       @FXML   
   private void saveAccount() { // for saving
		
		nom = txt_nom.getText();
		prenom = txt_prenom.getText();
                email = txt_email.getText();
                username = txt_username.getText();
		role = combo_role.getSelectionModel().getSelectedItem();
		mdp = txt_password.getText();
               Image image1=null;
               image1= imgview.getImage();
            String photo = null;
        try {
            photo = saveToFileImageNormal(image1);
        } catch (SQLException ex) {
            Logger.getLogger(ArticleController.class.getName()).log(Level.SEVERE, null, ex);
        }
                
		 if (EDIT){
                  query = "UPDATE fos_user SET   username='"+username+"', email='"+email+"', password='"+mdp+"',roles='"+role+"', nom='"+nom+"', prenom='"+prenom+"' WHERE id='"+ID+"'";   

                 }
		else if(ADD){ // if add button is pressed
		  query = "INSERT INTO fos_user(username,username_canonical,email,email_canonical,enabled,password,roles,nom,prenom,image) VALUES('"+username+"', '"+username+"', '"+email+"', '"+email+"','1','"+mdp+"','"+role+"','"+nom+"','"+prenom+"','"+photo+"');";
                        
		}
		
		dao.saveData(query);
		
		txt_nom.setText("");
		txt_prenom.setText("");
                txt_email.setText("");
                txt_username.setText("");
                txt_password.setText("");
		combo_role.getSelectionModel().select(0);
		
		refreshTable();
		
		ADD = true;
	}
   @FXML
   private void deleteAccount() {
		Fos_user selected = tblview.getSelectionModel().getSelectedItem();
		ID = selected.getId();
               
		query = "DELETE FROM fos_user WHERE id='"+ID+"'";
		dao.saveData(query);
		refreshTable();
	}
   
        @FXML   
   private void editAccount()
   {
       Fos_user selected = tblview.getSelectionModel().getSelectedItem();
		ID = selected.getId();
		txt_nom.setText(selected.getNom());
		txt_prenom.setText(selected.getPrenom());
                txt_email.setText(selected.getEmail());
                txt_username.setText(selected.getUsername());
                txt_password.setText(selected.getPassword());
		combo_role.getSelectionModel().select(selected.getRoles());
		
   }

  
   private void initTable() {
       column_nom.setCellValueFactory(new PropertyValueFactory<>("nom")); //from entity
        column_prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        column_username.setCellValueFactory(new PropertyValueFactory<>("username"));
        column_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        column_mdp.setCellValueFactory(new PropertyValueFactory<>("password"));
        column_ID.setCellValueFactory(new PropertyValueFactory<>("id"));
        column_role.setCellValueFactory(new PropertyValueFactory<>("roles"));
        
       
      
	}
	
	private void refreshTable() {
		initTable();
		query = "SELECT  f.id, f.username,f.email,f.password,f.roles,f.nom,f.prenom FROM fos_user as f ";
		tblview.setItems(dao.getAccountsData(query));
		
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
            String req = "SELECT * FROM fos_user";
            PreparedStatement st;
            ResultSet rs;
            
            st = cnx.prepareStatement(req);
            rs = st.executeQuery(req);
            ResultSetMetaData rsmd = rs.getMetaData();
            int colNb = rsmd.getColumnCount();
            PdfPTable NamesRow = new PdfPTable(5);
            Document d = new Document(PageSize.A4.rotate());
            PdfWriter.getInstance(d, new FileOutputStream(selectedDirectory.getAbsolutePath() + "\\ListeUser.pdf"));

            d.open();

            d.add(new Paragraph("Liste des utilisateurs :\n\n\n"));
            NamesRow.setWidthPercentage(100);
            NamesRow.setTotalWidth(new float[]{100, 100, 50, 50, 100});
        
           
            NamesRow.addCell("ID");
            
            NamesRow.addCell("Username");
            NamesRow.addCell("Email");
            NamesRow.addCell("Mot de passe");
            NamesRow.addCell("Role");
            
            NamesRow.addCell("Nom"); 
            NamesRow.addCell("Prenom");
          
            

            d.add(NamesRow);
            while (rs.next()) {
                PdfPTable pt = new PdfPTable(5);
                pt.setWidthPercentage(100);
                pt.setTotalWidth(new float[]{100, 100, 50, 50, 100});
                pt.addCell("" + rs.getInt(1));
                pt.addCell("" + rs.getString(2));
                pt.addCell("" + rs.getString(4));
                pt.addCell("" + rs.getString(8));
                pt.addCell("" + rs.getString(12));
                pt.addCell("" + rs.getString(13));
                pt.addCell("" + rs.getString(14));
               
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
        
     private void insertNewAccount() { // for adding new account
		txt_nom.setText("");
		txt_prenom.setText("");
                txt_email.setText("");
                txt_password.setText("");
                txt_username.setText("");
		combo_role.getSelectionModel().select(0);
		
	}
   
      public void searchProduct(){
         
prod_search.setOnKeyReleased(e->{
    if(prod_search.getText().equals("")){
        
       refreshTable();
    }
    else{
       initTable();
          String sql = "Select * from fos_user where nom LIKE '%"+prod_search.getText()+"%'"
                + "UNION Select * from fos_user where username LIKE '%"+prod_search.getText()+"%'" 
                  + "UNION Select * from fos_user where prenom LIKE '%"+prod_search.getText()+"%'" 
                  + "UNION Select * from fos_user where id LIKE '%"+prod_search.getText()+"%'" ;
         
        
        tblview.setItems(dao.getAccountsDat3(sql));
    
    
    }
});
}

       private void initRole() {
		List<String> list = new ArrayList<String>();

		// foreach loop
		for(String content:roles) {
			list.add(content);
		}
		
		// convert list to observable list
		ObservableList obList = FXCollections.observableArrayList(list);
		combo_role.setItems(obList);

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
    Fos_user art=tblview.getItems().get(tblview.getSelectionModel().getSelectedIndex());

    //tf_rate.setText(idprod);


           
                TableColumn.CellEditEvent edittedcell = null;
               Fos_user ar=gettemp(edittedcell);  
          System.out.println(ar);
                String photo=ar.getImage();
                        System.out.println(photo);
            Image imageURL= new Image("file:///C:/wamp64/www/PI/web/Uploads/article/photo/" + photo);
            System.out.println(imageURL);
            imgview.setImage(imageURL);}
    }); 
         }
    public Fos_user gettemp(TableColumn.CellEditEvent edittedCell) {
        Fos_user test = tblview.getSelectionModel().getSelectedItem();
      
        return test;
    }
       
       
      
       
       

       
       
       
}
   
   
