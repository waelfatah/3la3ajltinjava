/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.la3ajltin.services;

import edu.la3ajltin.entities.Commande;
//import edu.la3ajltin.entities.Produit;
import edu.la3ajltin.tools.ConnectionDB;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Mega-PC
 */
public class CommandeService {

    ConnectionDB db = ConnectionDB.getInstance();
    Connection cn = db.getCnx();

 /*   public void ajouterProduit(Commande c) {
        try {
            String req = "INSERT INTO commande (id_user,prix_total,date) VALUES (?,?,?)";
            System.out.println(req);
            PreparedStatement st = cn.prepareStatement(req);
            st.setInt(1, c.getId_user());
            st.setFloat(2, c.getPrix_total());
            st.setDate(3, (Date) c.getDate());
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void updateQuantite(int nbVentes,int qte, int id) throws SQLException {
        String req = "UPDATE produit SET nb_ventes=?,quantite_av = ? WHERE id_prod = ?";
        PreparedStatement st = cn.prepareStatement(req);
        st.setInt(1, nbVentes);
        st.setInt(2, qte);
        st.setInt(3, id);
        st.executeUpdate();

    }
    
    public ObservableList<Commande> afficherCommandes(int id_user) {
        PreparedStatement st;
        ResultSet rs;
        ObservableList<Commande> myList = FXCollections.observableArrayList();

        try {
            String req = "SELECT * FROM commande WHERE  id_user = '"+id_user+"'";
            st = cn.prepareStatement(req);
            rs = st.executeQuery(req);
            while (rs.next()) {
                Integer id_user_rs= rs.getInt("id_user");
                Integer id_comm_rs = rs.getInt("id_commande"); //Get from database
                Float prix_rs = rs.getFloat("prix_total");
                Date date_rs = rs.getDate("date");
                
                myList.add(new Commande(id_user_rs,id_comm_rs, prix_rs, date_rs));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return myList;

    }
        public Commande afficherUserCommande(int id_commande) {
        PreparedStatement st;
        ResultSet rs;
        Commande comm =null;

        try {
            String req = "SELECT * FROM commande WHERE id_commande = '"+id_commande+"' ";
            st = cn.prepareStatement(req);
            rs = st.executeQuery(req);
            while (rs.next()) {
                Integer id_user_rs = rs.getInt("id_user");
                Integer id_comm_rs = rs.getInt("id_commande"); //Get from database
                Float prix_rs = rs.getFloat("prix_total");
                Date date_rs = rs.getDate("date");
                
                comm = (new Commande(id_user_rs,id_comm_rs, prix_rs, date_rs));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return comm;

    }*/

}
