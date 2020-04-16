/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.la3ajltin.tools;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ConnectionDB {
    
    public Connection cnx;
    public static ConnectionDB instance;
    String url = "jdbc:mysql://localhost:3306/pidev";
    String login = "root";
    String mdp = "";
    public ConnectionDB(){
        
        try {
            cnx = DriverManager.getConnection(url,login,mdp);
            System.out.println("Connexion à la base établie!");
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    public static ConnectionDB getInstance(){
        if (instance == null)
            instance = new ConnectionDB();
        return instance;
    }
    public Connection getCnx(){
        return cnx;
    }
}
