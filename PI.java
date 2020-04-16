/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi;


import java.io.IOException;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

/**
 *
 * @author Nour ghidaoui
 */
public class PI extends Application {
    private FXMLLoader loader;
    
    @Override
    
       public void start(Stage primaryStage) throws IOException {
		
	// Parent root = FXMLLoader.load(getClass().getResource("Test.fxml"));
            	 Parent root = FXMLLoader.load(getClass().getResource("/GUI/Test.fxml"));

            Scene scene = new Scene(root);
            
      
       scene.getStylesheets().add(getClass().getResource("/GUI/Style.css").toExternalForm());
        primaryStage.setTitle("");
        primaryStage.setScene(scene);
        primaryStage.show();
	}
       public static void main(String[] args)
       {
         launch(args);  
       }
    }
    

