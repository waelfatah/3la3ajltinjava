/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;


import com.jfoenix.controls.JFXTextField;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import Utils.DBConnection;

/**
 *
 * @author NacimGastli
 */
public class Admin extends User{

    public Admin(String username, String password) {
        super(username, password);
    }

    public Admin(int id, String prenom, String nom) {
        super(id, prenom, nom);
    }

   
    
    public Admin Session(Admin p) throws SQLException {
        Statement ste;
        PreparedStatement pst;
        ResultSet rs;
        
         DBConnection connect ;
        connect = DBConnection.getInstance();
        String requete = "SELECT * FROM fos_user Where `password` =? and `roles`= ? and  `username` = ? ";

        pst = DBConnection.getInstance().getConnection().prepareStatement(requete);
        pst.setString(1, p.getPassword());
        pst.setString(2, "admin");
        pst.setString(3, p.getUsername());
        rs = pst.executeQuery();
        
        while (rs.next()) {
            
            Admin R = new Admin( rs.getInt("id"),rs.getString("prenom"), rs.getString("nom"));
            R.setNom(rs.getString("nom"));
            R.setPrenom(rs.getString("prenom"));
            R.setid(rs.getInt("id"));
          
            return R;
        }
        return null;

    }

    @Override
    public String toString() {
        return "Admin{" + '}';
    }
    
}
