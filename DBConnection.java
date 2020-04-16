package Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConnection {

	private final String dburl = "jdbc:mysql://localhost:3306/pidev";
	private final String username = "root";
	private final String password = ""; 
	private Connection cnx;
        private static DBConnection instance;
	public DBConnection(){
            try {
                cnx = DriverManager.getConnection(dburl,username,password);
                System.out.println("Connexion à la base établie!");
            } catch (SQLException ex) {
                Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
	}
        public static DBConnection getInstance(){
         if(instance==null)
        instance=new DBConnection();
        return instance;
}

	public Connection getConnection() {
		try {
			cnx = DriverManager.getConnection(dburl, username, password);
		}catch(Exception e) {
			e.printStackTrace();
		}

		return cnx;
	}

         public Connection getCnx(){
        return cnx;
    }
	public void close(Connection connect, PreparedStatement pstmt, ResultSet rs) {
		try {
			if(connect != null)
				connect.close();
			if(pstmt != null)
				pstmt.close();
			if(rs != null)
				rs.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void close(Connection connect, PreparedStatement pstmt) {
		try {
			close(connect, pstmt, null);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void close(PreparedStatement pstmt) {
		try {
			close(null, pstmt, null);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
