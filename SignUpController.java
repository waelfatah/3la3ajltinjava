/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.la3ajltin.gui;

import edu.la3ajltin.entities.User;
import edu.la3ajltin.services.ServiceUser;
import edu.la3ajltin.tools.mailer;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;


import javafx.stage.Stage;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author azizs
 */
public class SignUpController implements Initializable {
    
   
    @FXML
    private JFXTextField Nom;
    @FXML
    private JFXTextField Prenom;
    @FXML
    private JFXTextField Email;
    @FXML
    private JFXTextField Username;
    @FXML
    private JFXTextField Password;
    @FXML
    private JFXTextField Adresse;
    @FXML
    private JFXTextField Ntel;
    @FXML
    private JFXComboBox<String> Sexe;
    @FXML
    private JFXTextField CIN;
    @FXML
    private JFXDatePicker Date;
  
    @FXML
    private JFXButton ajouter;
    @FXML
    private AnchorPane anchorpane_inscription;
    @FXML
    private AnchorPane anchropane_signup;
    @FXML
    private Label thank_you;
    @FXML
    private Label label_for;
    @FXML
    private Label trusting_us;
    @FXML
    private AnchorPane anchorpane_register;
    @FXML
    private Label signup;
    @FXML
    private Label idtext;
   private FXMLLoader loader;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Sexe.getItems().addAll("Homme", "Femme");
        Sexe.getSelectionModel().select("Homme");
    }    
 public boolean validateNomP() {
        Pattern p = Pattern.compile("[a-zA-Z]+");
        Matcher m = p.matcher(Nom.getText());
        if (m.find() && m.group().equals(Nom.getText())) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validate First Name");
            alert.setHeaderText(null);
            alert.setContentText("Please Enter Valid First Name");
            alert.showAndWait();

            return false;
        }
    }

    public boolean validateEmaillP() {
        Pattern p = Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+");
        Matcher m = p.matcher(Email.getText());
        if (m.find() && m.group().equals(Email.getText())) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validate Email");
            alert.setHeaderText(null);
            alert.setContentText("Please Enter Valid Email");
            alert.showAndWait();

            return false;
        }
    }
    
   

    public boolean validateCINP() {
        Pattern p = Pattern.compile("[0-9]+");
        Matcher m = p.matcher(CIN.getText());
        if (m.find() && m.group().equals(CIN.getText()) && CIN.getText().length() == 8) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validate CIN");
            alert.setHeaderText(null);
            alert.setContentText("Please Enter Valid CIN");
            alert.showAndWait();

            return false;
        }
    }

    public boolean validatePasswordP() {
        // Pattern p = Pattern.compile("(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{6,}");
        // Matcher m = p.matcher(pwdC.getText());
        String pattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{6,}";
        if (Password.getText().matches(pattern)) {

            if (Password.getText().equals(Password.getText())) {
                return true;
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Validate Password");
                alert.setHeaderText(null);
                alert.setContentText("Check your password confirmation");
                alert.showAndWait();
                return false;
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validate Password");
            alert.setHeaderText(null);
            alert.setContentText("Password must contain at least one(Digit, Lowercase, UpperCase and Special Character) and length must be between 6 -15");
            alert.showAndWait();

            return false;
        }
    }
   
    private void ajouter(ActionEvent event) throws Exception {
    try {
            String nom = Nom.getText();
            String prenom = Prenom.getText();
            String email = Email.getText();
            String username = Username.getText();
            String password = Password.getText();
            String cin = CIN.getText();
            String sexe = Sexe.getValue();
            java.sql.Date date_naissance = java.sql.Date.valueOf(Date.getValue());
            String adresse = Adresse.getText();
            String num_tel = Ntel.getText();
        
            ServiceUser sp = new ServiceUser();
            mailer sm = new mailer();
            User p = new User(email, username,  password,  prenom,  nom,  cin,  sexe,  date_naissance,  adresse,  num_tel);
            sp.ajouter1(p);
         
            
            TrayNotification tray =new TrayNotification();
            tray.setTitle("Succès");
        tray.setMessage("Compte crée avec succés ! ");
        tray.setAnimationType(AnimationType.POPUP);
        tray.setNotificationType(NotificationType.INFORMATION);
        tray.showAndWait();
            
           sm.sendMail( "nour3a22@gmail.com",  "Confirmation d'inscription",  "Inscription Réussie");
                /*JOptionPane.showMessageDialog(null, "Inscription reussie");*/
                Alert A1 = new Alert(Alert.AlertType.INFORMATION);
                A1.setTitle("Confirmation d'inscription");
                A1.setHeaderText("Inscription Réussie");
                A1.setContentText("Un mail de vérification a été envoyé, veuillez vérifier votre compte avant de vous connecter.");
                A1.showAndWait();

                Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                stage.close();

        } 
            catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
  

   

    private void table(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("/edu/la3ajltin/gui/SignUp.fxml"));
        Scene tabbleViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(tabbleViewScene);
        window.show();

    }

    @FXML
    private void signUpConfirm(ActionEvent event) throws Exception  {
        
       
         String nom = Nom.getText();
            String prenom = Prenom.getText();
            String email = Email.getText();
            String username = Username.getText();
            String password = Password.getText();
            String cin = CIN.getText();
            String sexe = Sexe.getValue();
            java.sql.Date date_naissance = java.sql.Date.valueOf(Date.getValue());
            String adresse = Adresse.getText();
            String num_tel = Ntel.getText();
        
            ServiceUser sp = new ServiceUser();
            User per = new User(email, username,  password,  prenom,  nom,  cin,  sexe,  date_naissance,  adresse,  num_tel);
            sp.ajouter(per);
            
            mailer.sendMail(per.getEmail(), "Confirmation", "Merci d'avoir choisi 3la 3ajeltin pour accompagner, nous esperons répondre à toutes vos exigences ainsi que satisfaire tous vos besoins /n Cordialement, l'equipe de 3la 3ajeltin");
             TrayNotification tray =new TrayNotification();
            tray.setTitle("Succès");
        tray.setMessage("Compte crée avec succés ! ");
        tray.setAnimationType(AnimationType.POPUP);
        tray.setNotificationType(NotificationType.INFORMATION);
        tray.showAndWait();
            
         
                /*JOptionPane.showMessageDialog(null, "Inscription reussie");*/
                Alert A1 = new Alert(Alert.AlertType.INFORMATION);
                A1.setTitle("Confirmation d'inscription");
                A1.setHeaderText("Inscription Réussie");
                A1.setContentText("Un mail de vérification a été envoyé, veuillez vérifier votre compte avant de vous connecter.");
                A1.showAndWait();

             
		
		}
}
           
             
        
            
    
    

    
    

