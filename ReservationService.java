/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.la3ajltin.services;

import edu.la3ajltin.entities.Formulaire_Inscription;
import edu.la3ajltin.tools.ConnectionDB;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import edu.la3ajltin.entities.Formulaire_Inscription;
import edu.la3ajltin.entities.Formation;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**
 *
 * @author pc
 */
public class ReservationService {
     ConnectionDB db = ConnectionDB.getInstance();
     Connection cn = db.getCnx();
    
     public void ajouterReservation(Formulaire_Inscription r){
        
    
try {
            String requete=" INSERT INTO formulaire_inscription (id_formation,id_user,nomParticipant,prenomParticipant,dateInscription,nombrePlaceReservation,telephone,email,etat)"
                    + " VALUES (?,?,?,?,?,?,?,?,?)  ";
            
            
            PreparedStatement pst=cn.prepareStatement(requete);
            
            pst.setInt(1,r.getId_formation()); //tekhou l point d'interrogation 
            pst.setInt(2,r.getId_user());
            pst.setString(3,r.getNomParticipant());
            pst.setString(4,r.getPrenomParticipant());
            pst.setDate(5, (Date)r.getDateInscription());
            pst.setInt(6,r.getNombrePlaceReservation());
            pst.setInt(7,r.getTelephone());
            pst.setString(8,r.getEmail());
            pst.setString(9,r.getEtat());
           
            pst.executeUpdate();
            System.out.println("Reservation ajoute");
        } catch (SQLException ex) {
            Logger.getLogger(ReservationService.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("hahahahah");
        }  
}
          public ObservableList getNomFormation(){
          ObservableList options=FXCollections.observableArrayList();  
          try{
           
           String requete=("SELECT titleF FROM formation ");
            PreparedStatement pst = cn.prepareStatement(requete);
            
            ResultSet  rs=pst.executeQuery();
            while(rs.next()){
                options.add(rs.getString("titleF"));
                
            }
            
        
            }catch (SQLException ex) {
            Logger.getLogger(ReservationService.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("hahahahah");
      }
    
    
    return options;
}
      
      
       public List<Formation> getFormation(){
          List<Formation> options = new ArrayList<>() ;  
          try{
           
           String requete=("SELECT * FROM formation ");
            PreparedStatement pst = cn.prepareStatement(requete);
            
            ResultSet  rs=pst.executeQuery();
            while(rs.next()){
              options.add(new Formation(rs.getInt("id"),rs.getString("titleF")));
                
            }
            
        
            }catch (SQLException ex) {
            Logger.getLogger(ReservationService.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("hahahahah");
      }
    
    
    return options;
}
 
       public ObservableList<Formulaire_Inscription> afficherReservations(){
     
           ObservableList<Formulaire_Inscription>  listReservations= FXCollections.observableArrayList();
        
        try {
           
            Statement st=cn.createStatement();
            String requete="SELECT * FROM formulaire_inscription";
           ResultSet rs = st.executeQuery(requete);
           while (rs.next()){
             Formulaire_Inscription ev=new Formulaire_Inscription();
            ev.setId(rs.getInt("id")); //NAJEM NHOT 1 FEL PARAMETRE// SELON L BD ATTRIBUT LOWEL 1 
            ev.setId_formation(rs.getInt("id_formation")); //NAJEM NHOT 2 FEL PARAMETRE  //ATTRIBUT 2 FEL BD
            ev.setDateInscription(rs.getDate("dateInscription")); //NAJEM NHOT 3 FEL PARAMETRE //ATRIBUT 3 FEL BD
            ev.setNombrePlaceReservation(rs.getInt("nombrePlaceReservation"));
            ev.setEtat(rs.getString("etat"));
            
            listReservations.add(ev);
           }
   
        } catch (SQLException ex) {
            Logger.getLogger(FormationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listReservations;
}
         public ObservableList<Formulaire_Inscription> afficherMesReservations(int id_user) {
        PreparedStatement st;
        ResultSet rs;
        ObservableList<Formulaire_Inscription> myList = FXCollections.observableArrayList();

        try {
            String req = "SELECT * FROM formulaire_Inscription WHERE  id_user = '"+id_user+"'";
            st = cn.prepareStatement(req);
            rs = st.executeQuery(req);
            while (rs.next()) {
                Integer id_formation_rs = rs.getInt("id_formation");
                Integer id_user_rs= rs.getInt("id_user");
                 //Get from database
                Integer nombrePlaceReservation_rs = rs.getInt("nombrePlaceReservation");
                Date date_rs = rs.getDate("dateInscription");
                String etat_rs = rs.getString("etat");
                
                myList.add(new Formulaire_Inscription(id_formation_rs,id_user_rs, date_rs ,nombrePlaceReservation_rs,etat_rs));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return myList;

    }
    
    public void removeReservation(Formulaire_Inscription f)
    {
        
        try {
            String query = "DELETE FROM formulaire_inscription, WHERE `id`= ?";
            PreparedStatement pst = cn.prepareStatement(query);
            pst.setInt(1, f.getId());
            pst.executeUpdate();
            //System.out.println("Remove success");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
    }
     
    
    public void setEtatConfirmer(int id)
    {
        
         String requete = "UPDATE formulaire_inscription SET  `etat`=REPLACE(etat,'en cours','confirmÃ©') WHERE `id`="+id;
    try{
        Formulaire_Inscription r=new Formulaire_Inscription();
            Statement st = cn.createStatement();
            st.executeUpdate(requete);
            System.out.println("reservation confirmÃ©");
        } catch (SQLException ex) {
            
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
  
      
}
    public void setEtatIgnorer(int id)
    {
        
         String requete = "UPDATE formulaire_inscription SET  `etat`=REPLACE(etat,'en cours','ignorÃ©') WHERE `id`="+id;
    try{
        Formulaire_Inscription r=new Formulaire_Inscription();
            Statement st = cn.createStatement();
            st.executeUpdate(requete);
            System.out.println("reservation ignorÃ©");
        } catch (SQLException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
  
      
}
     public Formulaire_Inscription afficherUserReservation(int id_reservation) {
        PreparedStatement st;
        ResultSet rs;
        Formulaire_Inscription comm =null;

        try {
            String req = "SELECT * FROM formulaire_Inscription WHERE id= = '"+id_reservation+"' ";
            st = cn.prepareStatement(req);
            rs = st.executeQuery(req);
            while (rs.next()) {
                Integer id_user_rs = rs.getInt("id_user");
                Integer id_formation_rs = rs.getInt("id_formation"); //Get from database
                Date date_rs = rs.getDate("dateInscription");
                Integer nombrePlaceReservation_rs = rs.getInt("nombrePlaceReservation");
                String etat_rs = rs.getString("etat");
                comm = (new Formulaire_Inscription(id_user_rs,id_formation_rs,date_rs,nombrePlaceReservation_rs, etat_rs));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return comm;

    }

    
}
