package edu.la3ajltin.tools;

import edu.la3ajltin.entities.Article;
import edu.la3ajltin.entities.Position;
import edu.la3ajltin.entities.ArticleJ;
import edu.la3ajltin.entities.Fos_user_1;
import edu.la3ajltin.entities.Test;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DataAccessObject {

	private DBConnection database = new DBConnection();
	private ResultSet rs;
	private PreparedStatement pstmt;
	private Connection connect;
	
	public DataAccessObject() {
		
	}
	
	public void saveData(String query) {
		try {
			connect = database.getConnection(); // get connection 
			pstmt = connect.prepareStatement(query);
                      
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			database.close(connect, pstmt, null);
		}
	}
	
	public ObservableList<Position> getPositionData(String query){
		ObservableList<Position> list = FXCollections.observableArrayList();
		try {
			connect = database.getConnection();
			pstmt = connect.prepareStatement(query);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				list.add(new Position(rs.getString(1)));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public ObservableList<Fos_user_1> getAccountsData(String query){
		ObservableList list = FXCollections.observableArrayList();
		try {
			connect = database.getConnection();
			pstmt = connect.prepareStatement(query);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				list.add(new Fos_user_1(rs.getInt("id"), rs.getString("username"), rs.getString("email"), rs.getString("password"), rs.getString("roles"), rs.getString("nom"), rs.getString("prenom")));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
        
        public ObservableList<Article> getAccountsData2(String query){
		ObservableList list = FXCollections.observableArrayList();
		try {
			connect = database.getConnection();
			pstmt = connect.prepareStatement(query);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				list.add(new Article(rs.getInt("idArticle"), rs.getString("Titre"), rs.getString("Description"), rs.getString("etat"),rs.getInt("id_user"),rs.getDate("Date")));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
        
            public ObservableList<Article> getAccountsDat4(String query){
		ObservableList list = FXCollections.observableArrayList();
		try {
			connect = database.getConnection();
			pstmt = connect.prepareStatement(query);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				list.add(new Article(rs.getInt("idArticle"), rs.getString("Titre"), rs.getString("Description"), rs.getString("etat"),rs.getInt("id_user"),rs.getDate("Date")));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
        
     
            public ObservableList<Fos_user_1> getAccountsDat3(String query){
		ObservableList list = FXCollections.observableArrayList();
		try {
			connect = database.getConnection();
			pstmt = connect.prepareStatement(query);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				list.add(new Fos_user_1(rs.getInt("id"), rs.getString("username"), rs.getString("email"), rs.getString("password"), rs.getString("roles"), rs.getString("nom"), rs.getString("prenom")));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	
	
	public ObservableList<String> getPositionComboBox(String query){
		ObservableList list = FXCollections.observableArrayList();
		try {
			connect = database.getConnection();
			pstmt = connect.prepareStatement(query);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				list.add(rs.getString(1));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	
}









