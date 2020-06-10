/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.la3ajltin.services;

import edu.la3ajltin.entities.Article;
import edu.la3ajltin.tools.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
 	
     
/**
 *
 * @author Nour ghidaoui
 */
public class ArticleService {
     DBConnection database = DBConnection.getInstance();
     Connection connect = database.getCnx();
        
        
     public ObservableList<Article> rechercherArticle(String recherche) {
         connect = DBConnection.getInstance().getConnection();
        PreparedStatement st;
        ResultSet rs;
        ObservableList<Article> myList = FXCollections.observableArrayList();

        try {
            String req = "SELECT * FROM article WHERE "
                    + "(idArticle = ? "
                    + "OR titre LIKE ?"
                    + "OR description LIKE ?)";
            System.out.println(req);
            st = connect.prepareStatement(req);
            if (recherche.matches("[0-9]+")) {
                st.setInt(1, Integer.valueOf(recherche));
              
            } else {
                st.setInt(1, 0);
                
            }
            st.setString(2, recherche + "%");
            st.setString(3, recherche + "%");
          
            rs = st.executeQuery();
            while (rs.next()) {
                Integer idArticle_rs = rs.getInt("idArticle"); //Get from database
                String titre_rs = rs.getString("titre");
                   System.out.println(titre_rs);
                String description_rs = rs.getString("description");
               
                String urlImage_rs = rs.getString("image");
                myList.add(new Article(idArticle_rs, titre_rs, description_rs,urlImage_rs));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return myList;

    }
     
     
      public void setEtatConfirmer(int id)
    {
        
         String requete = "UPDATE article SET  `etat`='Confirmé' WHERE `idArticle`="+id;
    try{
        Article a=new Article();
            Statement st = connect.createStatement();
            st.executeUpdate(requete);
            System.out.println("Article approuvé");
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
  
      
}
    public void setEtatIgnorer(int id)
    {
        
         String requete = "UPDATE article SET  `etat`='Rejeté' WHERE `idArticle`="+id;
    try{
       Article r=new Article();
            Statement st = connect.createStatement();
            st.executeUpdate(requete);
            System.out.println("article rejeté");
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
  
      
}
    
}


