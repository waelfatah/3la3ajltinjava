/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.la3ajltin.services;

import edu.la3ajltin.tools.DBConnection;
import edu.la3ajltin.entities.Client;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author NacimGastli
 */
public class Service_Client {

    private Statement ste;
    private PreparedStatement pst, pst2;
    private ResultSet rs;
    private DBConnection con;

    public Service_Client() throws SQLException {
        con = DBConnection.getInstance();
    }

    public int Singin(Client u) {
        try {
            String requete = "SELECT * FROM fos_user Where `password` =? and `username` = ? ";
            System.out.println(requete);
            pst = con.getConnection().prepareStatement(requete);
            pst.setString(1, u.getPassword());
            pst.setString(2, u.getUsername());
            System.out.println("ahla");
            System.out.println(u.getPassword());
            rs = pst.executeQuery();

            boolean v = rs.next();
            if (v) {

                u.setid(rs.getInt("id"));
                if (rs.getString("roles").equalsIgnoreCase("User")) {
                    return 3;
                } else if (rs.getString("roles").equalsIgnoreCase("Admin")) {
                    return 2;
                }
            } else {
                return 0;
            }

        } catch (SQLException ex) {
            Logger.getLogger(Service_Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public int VerifierCompte(Client p, String code) {

        try {
            String requete = "SELECT * FROM fos_user Where PASSWORD =? and ROLES= ? and (EMAIL = ? OR USERNAME = ?) ";
            pst = con.getConnection().prepareStatement(requete);
            pst.setString(1, p.getPassword());
            pst.setInt(2, 2);
            pst.setString(3, p.getEmail());
            pst.setString(4, p.getUsername());

            rs = pst.executeQuery();

            boolean v = rs.next();

            if (v == true) {
                if (rs.getString("code").equals(code) == false) {
                    return 0;
                } else {
                    int id = rs.getInt("id");
                    System.out.println(id);
                    String req = "UPDATE fos_user set enabled=? where id=? ";
                    try {
                        pst2 = con.getConnection().prepareStatement(req);
                        pst2.setInt(1, 1);
                        pst2.setInt(2, id);
                        pst2.executeUpdate();
                        return 1;
                    } catch (SQLException ex) {
                        Logger.getLogger(Service_Client.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(Service_Client.class.getName()).log(Level.SEVERE, null, ex);
        }

        return 0;
    }

    public int updatetab(Client p) {
        String requete = "UPDATE fos_user set email=?, username=?, password=?,  prenom=?, nom=? where id = ?";
        try {
            pst = con.getConnection().prepareStatement(requete);
            pst.setString(1, p.getEmail());
            pst.setString(2, p.getUsername());
            pst.setString(3, p.getPrenom());
            pst.setString(4, p.getNom());
          
            pst.setInt(7, p.getid());
            System.out.println(p);
            pst.executeUpdate();
            return 1;
        } catch (SQLException ex) {
            Logger.getLogger(Service_Client.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }

    }

    public int update(Client p) throws SQLException {
        try {
            pst = con.getConnection().prepareStatement("UPDATE `fos_user` SET `username`=?,`email`=?,`password`=?,`nom`=?,`prenom`=? WHERE `id`=?");
            pst.setString(2, p.getEmail());
            pst.setString(1, p.getUsername());
            pst.setString(2, p.getPassword());
            pst.setString(5, p.getPrenom());
            pst.setString(4, p.getNom());
          
            pst.executeUpdate();
            return 1;
        } catch (Exception e) {
            Logger.getLogger(Service_Client.class.getName()).log(Level.SEVERE, null, e);
            return 0;
        }

    }

    //@Override
    public void Delete(int id) {
        String req = "UPDATE test set desactiver=? where id=? ";
        try {
            pst2 = con.getConnection().prepareStatement(req);
            pst2.setInt(1, 1);
            pst2.setInt(2, id);
            pst2.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(Service_Client.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public Client chercher(int id) {
        try {
            String requete = "SELECT `id`, `email`, `username`, `password`, `prenom`, `nom`  FROM `fos_user` WHERE `id`=? ";
            pst = con.getConnection().prepareStatement(requete);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            while (rs.next()) {
                Client R = new Client(rs.getInt("id"), rs.getString("email"),
                        rs.getString("username"), rs.getString("password"), rs.getString("prenom"),
                        rs.getString("nom"));
               
                return R;

            }

        } catch (SQLException ex) {
            Logger.getLogger(Service_Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public int SingIn(Client t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getMd5(String input) {
        try {

            // Static getInstance method is called with hashing MD5 
            MessageDigest md = MessageDigest.getInstance("MD5");

            // digest() method is called to calculate message digest 
            //  of an input digest() return array of byte 
            byte[] messageDigest = md.digest(input.getBytes());

            // Convert byte array into signum representation 
            BigInteger no = new BigInteger(1, messageDigest);

            // Convert message digest into hex value 
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } // For specifying wrong message digest algorithms 
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

}
