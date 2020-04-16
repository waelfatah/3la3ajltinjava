/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 
 */
public class DataSource {
    Connection cnx;
    String url="jdbc:mysql://localhost:3306/pidev";
    String login = "root";
    String mdp="";
    static DataSource mycnx;
            
    private DataSource() {
        try {
        cnx = DriverManager.getConnection(url, login, mdp);
        System.out.println("Connexion établie");
        } catch (SQLException ex) {
            Logger.getLogger(DataSource.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Connection getCnx() {
        return cnx;
    }
    
    public static DataSource getInstance() {
        if(mycnx == null) {
            mycnx = new DataSource();
        }
        return mycnx;
    }
}
