/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;


import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import Utils.DBConnection;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class StatController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private BarChart<?, ?> chart_top;

    @FXML
    private CategoryAxis x;

    @FXML
    private NumberAxis y;
      private Connection con = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;
    @Override
    @FXML
    public void initialize(URL url, ResourceBundle rb) {
                       con = DBConnection.getInstance().getConnection();

         XYChart.Series set1 = new XYChart.Series();
        // TODO
       
        try {
            pst = con.prepareStatement("SELECT Position_ID,COUNT(Position_ID) as nbposition FROM articlej GROUP BY Position_ID");
            rs = pst.executeQuery();
            while(rs.next())
            {
               int nbposition = rs.getInt("nbposition");
               String Position_ID=rs.getString("Position_ID");
               set1.getData().add(new XYChart.Data(Position_ID, nbposition));

            }
        } catch (SQLException ex) {
            Logger.getLogger(ArticleController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        
        /*
        set1.getData().add(new XYChart.Data("Pending", 500));
        set1.getData().add(new XYChart.Data("Solved", 300));
        set1.getData().add(new XYChart.Data("Closed", 150));
        */
        chart_top.getData().addAll(set1);

      
    }    
    
}
