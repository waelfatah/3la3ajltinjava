/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.la3ajltin.services;

import edu.la3ajltin.tools.ConnectionDB;
import edu.la3ajltin.entities.Event;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.time.LocalDate;

/**
 *
 * @author pc
 */
public class EventService {
    ConnectionDB db = ConnectionDB.getInstance();
    Connection cn = db.getCnx();
    
     public void ajouterEvent(Event E) {
        try {
            String req = "INSERT INTO event (title,dateDebutEvent,dateFinEvent,nbrDePlace,lieuEvent,descriptionEvent,image) VALUES (?,?,?,?,?,?,?)";
           
            PreparedStatement st = cn.prepareStatement(req);
            st.setString(1, E.getTitre());
            st.setDate(2, E.getDateDebutEvent());
            st.setDate(3, E.getDateFinEvent());
            st.setInt(4, E.getNbrDePlace());
            st.setString(5, E.getLieuEvent());
            st.setString(6, E.getDescriptionEvent());
            st.setString(7, E.getImage());
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public ObservableList<Event> afficherListeEvent() {
        PreparedStatement st;
        ResultSet rs;
        ObservableList<Event> myList = FXCollections.observableArrayList();

        try {
            String req = "SELECT * FROM event";

            st = cn.prepareStatement(req);
            rs = st.executeQuery(req);
            while (rs.next()) {
               Event ev=new Event();
            ev.setId(rs.getInt("id")); //NAJEM NHOT 1 FEL PARAMETRE// SELON L BD ATTRIBUT LOWEL 1 
            ev.setTitre(rs.getString("title")); //NAJEM NHOT 2 FEL PARAMETRE  //ATTRIBUT 2 FEL BD
            ev.setDateDebutEvent(rs.getDate("dateDebutEvent"));
            ev.setDateFinEvent(rs.getDate("dateDebutEvent"));//NAJEM NHOT 3 FEL PARAMETRE //ATRIBUT 3 FEL BD
            ev.setNbrDePlace(rs.getInt("nbrDePlace"));
            ev.setLieuEvent(rs.getString("lieuEvent"));
            ev.setDescriptionEvent(rs.getString("descriptionEvent"));
            ev.setImage(rs.getString("image"));
           
                myList.add(ev);
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return myList;

    }

  

    public void supprimerExpert(Event e) {
        try {

            String req = "DELETE FROM event WHERE id = ?";
            PreparedStatement st = cn.prepareStatement(req);
            st.setInt(1, e.getId());
            st.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void modifierEvent(Event e) {
        String query = "UPDATE `event` SET `title`=?,`dateDebutEvent`=?,"
                     + "`dateFinEvent`=?,`nbrDePlace`=?,`lieuEvent`=? ,`descriptionEvent`=?,`image`=? WHERE  id =?";
       
        try {
            
            PreparedStatement pst = cn.prepareStatement(query); 
            
            pst.setString(1,e.getTitre()); //tekhou l point d'interrogation 1
            pst.setDate(2,e.getDateDebutEvent());
            pst.setDate(3,e.getDateFinEvent());//tekhou l point d'interrogation 2
            pst.setInt(4,e.getNbrDePlace());
            pst.setString(5,e.getLieuEvent());
            pst.setString(6,e.getDescriptionEvent());
            pst.setString(7,e.getImage());
            pst.setInt(8,e.getId());
            
            pst.executeUpdate();
            System.out.println("Update success");
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la mise à jour " + ex.getMessage());
        }
    }

  /*  public ObservableList<Event> rechercherEvent(String recherche) {

        PreparedStatement st;
        ResultSet rs;
        ObservableList<Event> myList = FXCollections.observableArrayList();
        Event ev=new Event();
        

        try {
            String req = "SELECT * FROM event WHERE "
                    + "(id = ? "
                    + "OR title LIKE ?"
                    + "OR nbrDePlace LIKE ? "
                    + "OR lieuEvent LIKE ? "
                    + "OR DescriptionEvent LIKE ? )";
            st = cn.prepareStatement(req);
            
            if (recherche.matches("[0-9]+")) {
                st.setInt(1, Integer.valueOf(recherche));
                st.setInt(3, Integer.valueOf(recherche));
              
            } else {
                st.setInt(1, 0);
                st.setInt(3, 0);
                
            }
            st.setString(2, recherche + "%");
            st.setString(4, recherche + "%");
            st.setString(5, recherche + "%");


            /*for(int i=0; i<recherche.length();i++){
                if(recherche.charAt(i) )                  
            }
            rs = st.executeQuery();
            
            while (rs.next()) {
               Integer id = rs.getInt("id"); //Get from database
                String titre = rs.getString("title");
                Date dateDebutEvent =rs.getDate("dateDebutEvent");
                Date dateFinEvent =rs.getDate("dateFinEvent");
                Integer nbrDePlace = rs.getInt("nbrDePlace");
                String lieuEvent = rs.getString("lieuEvent");
                String descriptionEvent = rs.getString("descriptionEvent");
                String image = rs.getString("image");
               myList.add(ev);
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return myList;

    }*/
    public ObservableList<Event> rechercherEvent(String recherche) {
        Event ev=new Event();
         cn = ConnectionDB.getInstance().getCnx();
        PreparedStatement st;
        ResultSet rs;
        ObservableList<Event> myList = FXCollections.observableArrayList();

        try {
            String req = "SELECT * FROM event WHERE "
                    + "(id = ? "
                    + "OR title LIKE ?"
                    + "OR nbrDePlace LIKE ? "
                    + "OR lieuEvent LIKE ? "
                    + "OR DescriptionEvent LIKE ? )";
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
                String titre = rs.getString("title");
                Date dateDebutEvent =rs.getDate("dateDebutEvent");
                Date dateFinEvent =rs.getDate("dateFinEvent");
                Integer nbrDePlace = rs.getInt("nbrDePlace");
                String lieuEvent = rs.getString("lieuEvent");
                String descriptionEvent = rs.getString("descriptionEvent");
                String image = rs.getString("image");
                myList.add(ev);
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return myList;

    }

    public boolean NomControle(String titre) {
        if (!titre.trim().matches("[a-zA-Z_]+")) {
            return false;
        } else {
            return true;
        }

    }

    public boolean lieuControle(String lieuEvent) {
        if (!lieuEvent.trim().matches("[a-zA-Z_]+")) {
            return false;
        } else {
            return true;
        }

    }
    public boolean descriptionControle(String descriptionEvent) {
        if (!descriptionEvent.trim().matches("[a-zA-Z_]+")) {
            return false;
        } else {
            return true;
        }

    }

    public boolean QuantiteControle(String nbrDePlace) { //INTEGER
        if (!nbrDePlace.trim().matches("[0-9]+")) {

            return false;
        } else {
            return true;
        }

    }
    

    

    public Event getEventparNom(String titr) throws SQLException {
        Event e = null;
        PreparedStatement ps = cn.prepareStatement("SELECT * FROM `event` where (title LIKE ?)");
        ps.setString(1, titr + "%");
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Integer id = rs.getInt("id"); //Get fr om database
                String titre = rs.getString("title");
                Date dateDebutEvent =rs.getDate("dateDebutEvent");
                Date dateFinEvent =rs.getDate("dateFinEvent");
                Integer nbrDePlace = rs.getInt("nbrDePlace");
                String lieuEvent = rs.getString("lieuEvent");
                String descriptionEvent = rs.getString("descriptionEvent");
                String image = rs.getString("image");
            e = new Event(id, titre, dateDebutEvent,dateFinEvent, nbrDePlace, lieuEvent,descriptionEvent,image);
        }
        return e;
    }

    

}
