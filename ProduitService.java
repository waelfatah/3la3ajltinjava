/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.la3ajltin.services;

import edu.la3ajltin.entities.Produit;
import edu.la3ajltin.tools.ConnectionDB;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Mega-PC
 */
public class ProduitService {

    ConnectionDB db = ConnectionDB.getInstance();
    Connection cn = db.getCnx();

    public void ajouterProduit(Produit p) {
        try {
            String req = "INSERT INTO produit (nom,marque,prix_prod,quantite,type_prod,quantite_av) VALUES (?,?,?,?,?,?)";
            PreparedStatement st = cn.prepareStatement(req);
            st.setString(1, p.getNom());
            st.setString(2, p.getMarque());
            st.setFloat(3, p.getPrix_prod());
            st.setInt(4, p.getQuantite());
            st.setString(5, p.getType_prod());
            st.setInt(6, p.getQuantite());
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public ObservableList<Produit> afficherListeProduits() {
        PreparedStatement st;
        ResultSet rs;
        ObservableList<Produit> myList = FXCollections.observableArrayList();

        try {
            String req = "SELECT * FROM produit WHERE type_prod = 'accessoire'";

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

    }

    public ObservableList<Produit> afficherListeVelos() {
        PreparedStatement st;
        ResultSet rs;
        ObservableList<Produit> myList = FXCollections.observableArrayList();

        try {
            String req = "SELECT * FROM produit WHERE type_prod = 'velo'";

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

    }

    public void supprimerExpert(Produit p) {
        try {

            String req = "DELETE FROM produit WHERE id_prod = ?";
            PreparedStatement st = cn.prepareStatement(req);
            st.setInt(1, p.getId_prod());
            st.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void modifierProduit(Produit p, String colName) {
        try {
            switch (colName) {
                case "Nom Produit":
                    colName = "nom";
                    break;
                case "Marque Produit":
                    colName = "marque";
                    break;
                case "Prix Produit":
                    colName = "prix_prod";
                    break;
                case "Quantité":
                    colName = "quantite";
                    break;
                case "Type Produit":
                    colName = "type_prod";
                    break;
            }
            String req = "UPDATE produit SET " + colName + " = ? WHERE id_prod = ?";
            System.out.println(req);
            PreparedStatement st = cn.prepareStatement(req);
            switch (colName) {

                case "nom":
                    st.setString(1, p.getNom());
                    break;

                case "marque":
                    st.setString(1, p.getMarque());
                    break;
                case "prix_prod":
                    st.setFloat(1, p.getPrix_prod());
                    break;
                case "quantite":
                    st.setInt(1, p.getQuantite());
                    break;
                case "type_prod":
                    st.setString(1, p.getType_prod());
                    break;
            }

            st.setInt(2, p.getId_prod());
            st.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public ObservableList<Produit> rechercherProduit(String recherche) {

        PreparedStatement st;
        ResultSet rs;
        ObservableList<Produit> myList = FXCollections.observableArrayList();

        try {
            String req = "SELECT * FROM produit WHERE "
                    + "(id_prod = ? "
                    + "OR nom LIKE ?"
                    + "OR marque LIKE ? "
                    + "OR prix_prod LIKE ?"
                    + "OR quantite LIKE ? "
                    + "OR url_image LIKE ? "
                    + "OR type_prod LIKE ? )";
            st = cn.prepareStatement(req);
            if (recherche.matches("[0-9]+")) {
                st.setInt(1, Integer.valueOf(recherche));
                st.setInt(5, Integer.valueOf(recherche));
                st.setFloat(4, Float.valueOf(recherche));
            } else {
                st.setInt(1, 0);
                st.setInt(5, 0);
                st.setFloat(4, 0);
            }
            st.setString(2, recherche + "%");
            st.setString(3, recherche + "%");
            st.setString(6, recherche + "%");
             st.setString(7, recherche + "%");


            /*for(int i=0; i<recherche.length();i++){
                if(recherche.charAt(i) )                  
            }*/
            rs = st.executeQuery();
            while (rs.next()) {
                Integer id_prod_rs = rs.getInt("id_prod"); //Get from database
                String nom_rs = rs.getString("nom");
                String marque_rs = rs.getString("marque");
                Float prix_prod_rs = rs.getFloat("prix_prod");
                Integer quantite_rs = rs.getInt("quantite");
                String type_prod_rs = rs.getString("type_prod");
                String urlImage_rs = rs.getString("url_image");
                myList.add(new Produit(id_prod_rs, nom_rs, marque_rs, prix_prod_rs, quantite_rs, type_prod_rs, urlImage_rs));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return myList;

    }

    public boolean NomControle(String nom) {
        if (!nom.trim().matches("[a-zA-Z_]+")) {
            return false;
        } else {
            return true;
        }

    }

    public boolean MarqueControle(String marque) {
        if (!marque.trim().matches("[a-zA-Z_]+")) {
            return false;
        } else {
            return true;
        }

    }

    public boolean QuantiteControle(String quantite) { //INTEGER
        if (!quantite.trim().matches("[0-9]+")) {

            return false;
        } else {
            return true;
        }

    }

    public boolean PrixControle(String prix) { //INTEGER
        if (!prix.trim().matches("[0-9]+$+")) {

            return false;
        } else {
            return true;
        }

    }

    public Produit getProduitparNom(String nom) throws SQLException {
        Produit p = null;
        PreparedStatement ps = cn.prepareStatement("SELECT * FROM `produit` where (nom LIKE ?)");
        ps.setString(1, nom + "%");
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Integer id_prod_rs = rs.getInt("id_prod"); //Get from database
            String nom_rs = rs.getString("nom");
            String marque_rs = rs.getString("marque");
            Float prix_rs = rs.getFloat("prix_prod");
            Integer quantite_rs = rs.getInt("quantite");
            String type_prod_rs = rs.getString("type_prod");
            String urlImage_rs = rs.getString("url_image");
            p = new Produit(id_prod_rs, nom_rs, marque_rs, prix_rs, quantite_rs, type_prod_rs, urlImage_rs);
        }
        return p;
    }

    public int getQuantiteAV(Produit p) throws SQLException {
        int qteAv = 1;
        PreparedStatement ps = cn.prepareStatement("SELECT quantite_av FROM `produit` where (id_prod = ?)");
        ps.setInt(1, p.getId_prod());
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Integer quantiteav_rs = rs.getInt("quantite_av");
            qteAv = quantiteav_rs;
            return qteAv;
        }
        return qteAv;
    }

}
