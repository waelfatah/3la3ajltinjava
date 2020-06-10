/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.la3ajltin.services;

import edu.la3ajltin.entities.Commande;
import edu.la3ajltin.entities.Session;
import edu.la3ajltin.entities.fos_user;
import edu.la3ajltin.tools.BCrypt;
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
public class fos_userService {

    ConnectionDB db = ConnectionDB.getInstance();
    Connection cn = db.getCnx();

    public boolean Authentification(String username, String password) throws SQLException {
        Statement statement = cn.createStatement();

        String sql = "SELECT * FROM `fos_user` WHERE username = '" + username + "'";
        System.out.println(sql);
        //   ps=cn.prepareStatement(sql);
        ResultSet rs = statement.executeQuery(sql);
        // console requete System.out.println(rs);
        if (rs.next()) {
            String hashedPassword = rs.getString("password");

            if (BCrypt.checkpw(password, hashedPassword)) {
                System.out.println("User trouvé ");
                Session.start(rs.getInt("id"));
                return true;
            } else {
                System.out.println("User introuveable");
            }

        } else {
            System.out.println("User introuveable");
        }
        return false;
        //isconnected.setText("LAAAAAAA");
    }

    public fos_user get_User(int id) {
        String requete = "SELECT * FROM `fos_user` WHERE (id =" + id + ")";

        try {
            PreparedStatement ps = cn.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                fos_user us;
                us = new fos_user(rs.getInt("id"), rs.getString("username"), rs.getString("username_canonical"), rs.getString("email"), rs.getString("email_canonical"), rs.getInt("enabled"), rs.getString("salt"), rs.getString("password"), rs.getDate("last_login"), rs.getString("confirmation_token"), rs.getDate("password_requested_at"), rs.getString("roles"), rs.getString("image"), rs.getString("nom"), rs.getString("prenom"));
                return us;

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public ObservableList<String> getAdminsMails() {
        PreparedStatement st;
        ResultSet rs;
        ObservableList<String> myList = FXCollections.observableArrayList();

        try {
            String req = "SELECT email FROM fos_user where roles='admin'";
            st = cn.prepareStatement(req);
            rs = st.executeQuery(req);
            while (rs.next()) {
                String email_rs = rs.getString("email");

                myList.add(email_rs);
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return myList;
    }

}
