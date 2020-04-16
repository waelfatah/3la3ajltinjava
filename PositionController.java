/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;


import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import Utils.DataAccessObject;
import Models.Position;


/**
 * FXML Controller class
 *
 * @author Nour ghidaoui
 */
public class PositionController implements Initializable {

     @FXML
    private AnchorPane Anchorpane_parent;

    @FXML
    private Label label_title;

    @FXML
    private JFXTextField txt_new_position;

    @FXML
    private JFXButton btn_save;

    @FXML
    private TableView<Position> tblview;

    @FXML
    private TableColumn<Position,String> column_position;
    
    private String query;
    private DataAccessObject dao;
    @Override
   
    public void initialize(URL arg0, ResourceBundle arg1) {
		dao = new DataAccessObject();

		btn_save.setOnAction(e->{
			insertNewPosition();
		});
                 refreshTable();
		
	}
	
	
	private void insertNewPosition() {
		query = "INSERT INTO positions VALUES(null, '" +txt_new_position.getText()+ "')";
		dao.saveData(query);
		txt_new_position.setText("");
                refreshTable();
		
	}
        
        
	private void initTable() {
		column_position.setCellValueFactory(cell->cell.getValue().getpPosition());
               
	}
	
	private void refreshTable() {
		initTable();
		query = "SELECT position FROM positions";
		tblview.setItems(dao.getPositionData(query));
	}
    
}
