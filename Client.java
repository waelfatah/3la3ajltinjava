/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;


import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import Utils.DBConnection;


/**
 *
 * @author NacimGastli
 */
public class Client extends User {

    public Client(String username, String password) {
        super(username, password);
    }
    public Client(int id,String Prenom, String Nom) {
        super(id, Prenom, Nom);
    }

   
   public Client(){
 }
   
   
   
   public Client(int id, String email, String username,String password, String prenom, String nom, String adresse, String num_tel) {
        super(id, email, username,password, prenom, nom, adresse, num_tel);
    }

   public Client(String username, String password,String email, String num_tel, String nom, String adresse, String prenom) {
        super(username,password,email,num_tel,nom,adresse,prenom);
    }
   

  
 

   
    public Client Session(Client p) throws SQLException {
        Statement ste;
        PreparedStatement pst;
        ResultSet rs;
        DBConnection connect ;
        connect = DBConnection.getInstance();
        String requete = "SELECT * FROM fos_user Where `password` =? and `roles`= ? and  `username` = ? ";

        pst = connect.getConnection().prepareStatement(requete);
        pst.setString(1, p.getPassword());
        pst.setString(2, "user");
        pst.setString(3, p.getUsername());
        rs = pst.executeQuery();
        
        while (rs.next()) {
            
            Client R = new Client( rs.getInt("id"),rs.getString("prenom"), rs.getString("nom"));
            R.setNom(rs.getString("nom"));
            R.setPrenom(rs.getString("prenom"));
            R.setid(rs.getInt("id"));
          
            return R;
        }
        return null;

    }

    
    @Override
    public String toString() {
        return "Client{" + "id=" + getid() + ", email=" + getEmail() + 
                ", username=" + getUsername() + ", password=" + getPassword() + 
                ", prenom=" + getPrenom() + ", nom=" + getNom() +", adresse=" + getAdresse() +  ", cin=" + getCin() + 
                ", sexe=" + getSexe() + ", date_naissance=" + getDate_naissance() + 
                 ", Desactiver=" +getDesactiver()+'}';
    }
    
    
    
   
    
   
}
