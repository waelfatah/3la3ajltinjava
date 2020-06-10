/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.la3ajltin.gui;

import com.jfoenix.controls.JFXTextField;
import edu.la3ajltin.entities.Client;
import edu.la3ajltin.entities.UserSession;
import edu.la3ajltin.services.Service_Client;
import edu.la3ajltin.tools.DBConnection;
import edu.la3ajltin.tools.DataSource;
import edu.la3ajltin.tools.SendMail;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
   	


/**
 * FXML Controller class
 *
 * @author Nour ghidaoui
 */
public class ProfilController implements Initializable {

    private DBConnection database;
	private Connection connect;
     DataSource db = DataSource.getInstance();
     Connection cnx = db.getCnx();
    UserSession us; 
    Service_Client sc;
    @FXML
    private JFXTextField Nom;
    @FXML
    private JFXTextField Prenom;
    private JFXTextField Adresse;
    @FXML
    private JFXTextField Email;
    @FXML
    private JFXTextField Username;
    @FXML
    private JFXTextField Password;
    private JFXTextField Ntel;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
   Client R = new Client();
        us = UserSession.getInstace();
        System.out.println(us);
        int id_user = us.getId();
        
    }    
    
    
    public void Desactiver(ActionEvent event) throws SQLException {
        
        Service_Client sc = new Service_Client();
        sc.Delete(us.getId());
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Désactivation du compte");
        alert.setHeaderText(null);
        alert.setContentText("Votre compte a été désactivé");
        alert.showAndWait();

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
    
}
