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
public class CommandeProduitsService {

    ConnectionDB db = ConnectionDB.getInstance();
    Connection cn = db.getCnx();

  /*  public void ajouter(int idprod, int quantite) {
        try {
            String req = "INSERT INTO comamnde_produits (id_commande,id_prod,quantite) SELECT  c.id_commande,'" + idprod + "' AS id_prod,'" + quantite + "' FROM commande c ORDER BY c.id_commande DESC LIMIT 1";
            PreparedStatement st = cn.prepareStatement(req);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public int getQuantiteProduit(int idcomm, int id) throws SQLException {
        int quantite = 0;

        PreparedStatement ps = cn.prepareStatement("SELECT quantite FROM `comamnde_produits` where (id_prod = ? AND id_commande = ?)");
        ps.setInt(1, id);
        ps.setInt(2, idcomm);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Integer quantiteav_rs = rs.getInt("quantite");
            quantite = quantiteav_rs;
            return quantite;
        }

        return quantite;
    }

    public ObservableList<Produit> afficherProduitsCommandes(int id_commande) {
        PreparedStatement st;
        ResultSet rs;
        ObservableList<Produit> myList = FXCollections.observableArrayList();

        try {
            String req = "SELECT * FROM produit INNER JOIN comamnde_produits  ON produit.id_prod = comamnde_produits.id_prod WHERE comamnde_produits.id_commande = '" + id_commande + "'";
            st = cn.prepareStatement(req);
            rs = st.executeQuery(req);
            while (rs.next()) {
                Integer id_prod_rs = rs.getInt("id_prod"); //Get from database
                String nom_rs = rs.getString("nom");
                String marque_rs = rs.getString("marque");
                Float prix_rs = rs.getFloat("prix_prod");
                Integer quantite_rs = rs.getInt("quantite");
                String type_prod_rs = rs.getString("type_prod");
                String urlImage_rs = rs.getString("url_image");

                myList.add(new Produit(id_prod_rs, nom_rs, marque_rs, prix_rs, quantite_rs, type_prod_rs, urlImage_rs));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return myList;

    }*/
}
