/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.la3ajltin.services;

import edu.la3ajltin.entities.Formation;
import edu.la3ajltin.tools.ConnectionDB;
import java.sql.Connection;
import java.sql.Date;
import java.time.LocalDate;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author pc
 */
public class FormationService {
     ConnectionDB db = ConnectionDB.getInstance();
    Connection cn = db.getCnx();
    
     public void ajouterFormation(Formation E) {
        try {
            String req = "INSERT INTO formation (titleF,dateDebutFormation,dateFinFormation,nbrDePlaceF,lieuFormation,descriptionFormation,image) VALUES (?,?,?,?,?,?,?)";
          //  Date dateDebut = java.sql.Date.valueOf(E.getDateDebutFormation());
           // Date dateFin = java.sql.Date.valueOf(E.getDateFinFormation());
            PreparedStatement st = cn.prepareStatement(req);
            st.setString(1, E.getTitreF());
            st.setDate(2, E.getDateDebutFormation());
            st.setDate(3, E.getDateFinFormation());
            st.setInt(4, E.getNbrDePlaceF());
            st.setString(5, E.getLieuFormation());
            st.setString(6, E.getDescriptionFormation());
            st.setString(7, E.getImage());
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public ObservableList<Formation> afficherListeFormation() {
        PreparedStatement st;
        ResultSet rs;
        ObservableList<Formation> myList = FXCollections.observableArrayList();

        try {
            String req = "SELECT * FROM formation";

            st = cn.prepareStatement(req);
            rs = st.executeQuery(req);
            while (rs.next()) {
             Formation ev=new Formation();
            ev.setId(rs.getInt("id")); //NAJEM NHOT 1 FEL PARAMETRE// SELON L BD ATTRIBUT LOWEL 1 
            ev.setTitreF(rs.getString("titleF")); //NAJEM NHOT 2 FEL PARAMETRE  //ATTRIBUT 2 FEL BD
                System.out.println(rs.getString("titleF"));
            ev.setDateDebutFormation(rs.getDate("dateDebutFormation"));
            ev.setDateFinFormation(rs.getDate("dateDebutFormation"));//NAJEM NHOT 3 FEL PARAMETRE //ATRIBUT 3 FEL BD
            ev.setNbrDePlaceF(rs.getInt("nbrDePlaceF"));
            ev.setLieuFormation(rs.getString("lieuFormation"));
            ev.setDescriptionFormation(rs.getString("descriptionFormation"));
            ev.setImage(rs.getString("image"));
           
                myList.add(ev);
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return myList;

    }

  

    public void supprimerExpert(Formation e) {
        try {

            String req = "DELETE FROM formation WHERE id = ?";
            PreparedStatement st = cn.prepareStatement(req);
            st.setInt(1, e.getId());
            st.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void modifierFormation(Formation e) {
        String query = "UPDATE `formation` SET `titleF`=?,`dateDebutFormation`=?,"
                     + "`dateFinFormation`=?,`nbrDePlaceF`=?,`lieuFormation`=? ,`descriptionFormation`=?,`image`=? WHERE  id =?";
      //  Date dateDebut = java.sql.Date.valueOf(e.getDateDebutFormation());
            //Date dateFin = java.sql.Date.valueOf(e.getDateFinFormation());
        try {
            
            PreparedStatement pst = cn.prepareStatement(query); 
            pst.setString(1,e.getTitreF()); //tekhou l point d'interrogation 1
            pst.setDate(2,e.getDateDebutFormation());
            pst.setDate(3,e.getDateFinFormation());//tekhou l point d'interrogation 2
            pst.setInt(4,e.getNbrDePlaceF());
            pst.setString(5,e.getLieuFormation());
            pst.setString(6,e.getDescriptionFormation());
            pst.setString(7,e.getImage());
            pst.setInt(8,e.getId());
            pst.executeUpdate();
            System.out.println("Update success");
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la mise à jour " + ex.getMessage());
        }
    }

    public ObservableList<Formation> rechercherFormation(String recherche) {

        Formation ev=new Formation();
         cn = ConnectionDB.getInstance().getCnx();
        PreparedStatement st;
        ResultSet rs;
        ObservableList<Formation> myList = FXCollections.observableArrayList();

        try {
            String req = "SELECT * FROM formation WHERE "
                    + "(id = ? "
                    + "OR titleF LIKE ?"
                    + "OR nbrDePlaceF LIKE ? "
                    + "OR lieuFormation LIKE ? "
                    + "OR DescriptionFormation LIKE ? )";
            System.out.println(req);
            st = cn.prepareStatement(req);
            if (recherche.matches("[0-9]+")) {
                st.setInt(1, Integer.valueOf(recherche));
              
            } else {
                st.setInt(1, 0);
                
            }
            st.setString(2, recherche + "%");
            st.setString(4, recherche + "%");
            st.setString(5, recherche + "%");
          
            rs = st.executeQuery();
            while (rs.next()) {
                Integer id = rs.getInt("id"); //Get from database
                String titreF = rs.getString("titleF");
                Date dateDebutFormation =rs.getDate("dateDebutFormation");
                Date dateFinFormation =rs.getDate("dateFinFormation");
                Integer nbrDePlaceF = rs.getInt("nbrDePlaceF");
                String lieuFormation = rs.getString("lieuFormation");
                String descriptionFormation = rs.getString("descriptionFormation");
                String image = rs.getString("image");
                myList.add(ev);
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return myList;

    }

    public boolean NomControle(String titreF) {
        if (!titreF.trim().matches("[a-zA-Z_]+")) {
            return false;
        } else {
            return true;
        }

    }

    public boolean lieuControle(String lieuFormation) {
        if (!lieuFormation.trim().matches("[a-zA-Z_]+")) {
            return false;
        } else {
            return true;
        }

    }
    public boolean descriptionControle(String descriptionFormation) {
        if (!descriptionFormation.trim().matches("[a-zA-Z_]+")) {
            return false;
        } else {
            return true;
        }

    }

    public boolean QuantiteControle(String nbrDePlaceF) { //INTEGER
        if (!nbrDePlaceF.trim().matches("[0-9]+")) {

            return false;
        } else {
            return true;
        }

    }

    

    public Formation getFormationparNom(String titr) throws SQLException {
        
        Formation e = null;
        PreparedStatement ps = cn.prepareStatement("SELECT * FROM `formation` where (titleF LIKE ?)");
        ps.setString(1, titr + "%");
        ResultSet rs = ps.executeQuery();
       
        while (rs.next()) {
                Integer id = rs.getInt("id"); //Get from database
                String titreF = rs.getString("titleF");
                Date dateDebutFormation =rs.getDate("dateDebutFormation");
                Date dateFinFormation =rs.getDate("dateFinFormation");
                Integer nbrDePlaceF = rs.getInt("nbrDePlaceF");
                String lieuFormation = rs.getString("lieuFormation");
                String descriptionFormation = rs.getString("descriptionFormation");
                String image = rs.getString("image");
           e = new Formation(id,titreF,dateDebutFormation,dateFinFormation,nbrDePlaceF,lieuFormation,descriptionFormation,image);
        }
        return e;
}
    
    
    public int getQuantiteAV(Formation p) throws SQLException {
        int qteAv = 0;
        PreparedStatement ps = cn.prepareStatement("SELECT qteApresReservation FROM `formation` where (id = ?)");
        ps.setInt(1, p.getId());
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Integer quantiteav_rs = rs.getInt("quantite_av");
            qteAv = quantiteav_rs;
            return qteAv;
        }
        return qteAv;
    }
       
       
}
