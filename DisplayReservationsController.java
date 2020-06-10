/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.la3ajltin.gui;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import edu.la3ajltin.entities.Formulaire_Inscription;
import edu.la3ajltin.services.EventService;
import edu.la3ajltin.services.ReservationService;
import edu.la3ajltin.tools.ConnectionDB;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

/**
 * FXML Controller class
 *
 * @author pc
 */
public class DisplayReservationsController implements Initializable {

    @FXML
    private Button Confirmer;
    @FXML
    private Button Rejeter;
    @FXML
    private Button refresh;
    @FXML
    private TableView<Formulaire_Inscription> tableProduit;
    @FXML
    private TableColumn<Formulaire_Inscription, Integer> col_id_u;
    @FXML
    private TableColumn<Formulaire_Inscription, Integer> col_iF;
    @FXML
    private TableColumn<Formulaire_Inscription, Integer> nb_place;
    @FXML
    private TableColumn<Formulaire_Inscription, String> col_etat;
    @FXML
    private TextField search;
    public ObservableList<Formulaire_Inscription> data2 = FXCollections.observableArrayList();
    ReservationService es = new ReservationService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
          ReservationService es = new ReservationService();
        // TODO
        
        col_iF.setCellValueFactory(new PropertyValueFactory<>("id_formation"));
        col_id_u.setCellValueFactory(new PropertyValueFactory<>("id_user"));
         nb_place.setCellValueFactory(new PropertyValueFactory<>("nombrePlaceReservation"));
     
        col_etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        
        //modification
        tableProduit.setEditable(true); //Rend le tableau "editable"
        
        col_id_u.setCellFactory(TextFieldTableCell.<Formulaire_Inscription, Integer>forTableColumn(new IntegerStringConverter()));
        col_iF.setCellFactory(TextFieldTableCell.<Formulaire_Inscription, Integer>forTableColumn(new IntegerStringConverter()));
        nb_place.setCellFactory(TextFieldTableCell.<Formulaire_Inscription, Integer>forTableColumn(new IntegerStringConverter()));
        
        col_etat.setCellFactory(TextFieldTableCell.forTableColumn());
       
        
        //selection
        tableProduit.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE); //Pour séléctionner plusieurs lignes en même temps
        tableProduit.setItems(es.afficherReservations());
        
    }    

   

    @FXML
    private void Refresh(ActionEvent event) {
         ReservationService PS = new ReservationService();
        System.out.println("You clicked refresh button!");
        tableProduit.setItems(PS.afficherReservations());
    }

    @FXML
    private void changeNomCellEvent(TableColumn.CellEditEvent edittedCell) {
    }

    @FXML
    private void ExportToPDF(ActionEvent event)  throws FileNotFoundException, IOException, SQLException, DocumentException {
        ConnectionDB db = ConnectionDB.getInstance();
        Connection cn = db.getCnx();
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File selectedDirectory = directoryChooser.showDialog(new Stage());

        if (selectedDirectory == null) {
            Alert null_alert = new Alert(Alert.AlertType.ERROR);
            null_alert.setTitle(null);
            null_alert.setHeaderText(null);
            null_alert.setContentText("Aucun dossier séléctionné!");
            null_alert.showAndWait();
        } else {
            System.out.println(selectedDirectory.getAbsolutePath());
            String req = "SELECT * FROM formualire_Inscription ";
            Statement st;
            ResultSet rs;

            st = cn.createStatement();
            rs = st.executeQuery(req);
            ResultSetMetaData rsmd = rs.getMetaData();
            int colNb = rsmd.getColumnCount();
            PdfPTable NamesRow = new PdfPTable(5);
            Document d = new Document(PageSize.A4.rotate());
            PdfWriter.getInstance(d, new FileOutputStream(selectedDirectory.getAbsolutePath() + "\\ListeProduit.pdf"));

            d.open();

            d.add(new Paragraph("Liste des reservations:\n\n\n"));
            NamesRow.setWidthPercentage(100);
            NamesRow.setTotalWidth(new float[]{100, 100, 50, 50, 100});
            NamesRow.addCell("id");
            NamesRow.addCell("id_formation");
            NamesRow.addCell("id_user");
            NamesRow.addCell("nomParticipant");
            NamesRow.addCell("prenomParticipant");
            NamesRow.addCell("dateInscription");
            NamesRow.addCell("nombrePlaceReservation");
            NamesRow.addCell("telephone");
            NamesRow.addCell("email");           
            NamesRow.addCell("etat");
            d.add(NamesRow);
            while (rs.next()) {
                PdfPTable pt = new PdfPTable(5);
                pt.setWidthPercentage(100);
                pt.setTotalWidth(new float[]{100, 100, 50, 50, 100});

                pt.addCell("" + rs.getString(2));
                pt.addCell("" + rs.getString(3));
                pt.addCell("" + rs.getString(4));
                pt.addCell("" + rs.getString(5));
                pt.addCell("" + rs.getString(6));
                pt.addCell("" + rs.getString(7));
                pt.addCell("" + rs.getString(8));
                d.add(pt);
            }
            Alert Confirmation_Alert = new Alert(Alert.AlertType.INFORMATION); //Ok
            Confirmation_Alert.setTitle("EXPORTÉ!");
            Confirmation_Alert.setHeaderText(null);
            Confirmation_Alert.setContentText("Le tableau a été exporté en PDF avec succès!");
            Confirmation_Alert.show();

            d.close();
        }

    }
    
    
    
    private boolean alertConfirmation() {
        Alert a1 = new Alert(Alert.AlertType.CONFIRMATION);
        a1.setTitle("CONFIRMATION D'UPDATE");
        a1.setHeaderText("CONFIRMATION DE MODIFICATION");
        a1.setContentText("VOUS ETES SUR POUR LA MODIFICATION DE CET EVENT ?");
        Optional<ButtonType> result = a1.showAndWait();
        if (result.get() == ButtonType.OK) {
            return true;
        } else {
            return false;
        }

    }

    private boolean alertConfirmation2() {
        Alert a1 = new Alert(Alert.AlertType.CONFIRMATION);
        a1.setTitle("CONFIRMATION DE SUPPRESSION");
        a1.setHeaderText("CONFIRMATION DE SUPPRESSION");
        a1.setContentText("VOUS ETES SUR POUR LA SUPPRESSION DE CET EVENT ?");
        Optional<ButtonType> result = a1.showAndWait();
        if (result.get() == ButtonType.OK) {
            return true;
        } else {
            return false;
        }
    }

    @FXML
    private void Search(KeyEvent event) {
    }


    @FXML
    private void Add(ActionEvent event) throws IOException {
         if(tableProduit.getSelectionModel().getSelectedItem()!=null){
            Formulaire_Inscription r = tableProduit.getSelectionModel().getSelectedItem();
            es.setEtatConfirmer(r.getId());
            Alert successE = new Alert(Alert.AlertType.INFORMATION);
            successE.setTitle("Confirmation");
            successE.setHeaderText(null);
            successE.setContentText("reservation confirmée !");
            successE.showAndWait();
             FXMLLoader loader = new FXMLLoader(getClass().getResource("DisplayReservations.fxml"));
            Parent root = loader.load();
            tableProduit.getScene().setRoot(root);
        
         }
    }

    @FXML
    private void DeleteProd(ActionEvent event) throws IOException{
         if(tableProduit.getSelectionModel().getSelectedItem()!=null){
            Formulaire_Inscription r = tableProduit.getSelectionModel().getSelectedItem();
            es.setEtatIgnorer(r.getId());
            es.removeReservation(r);
            Alert successE = new Alert(Alert.AlertType.INFORMATION);
            successE.setTitle("Annulation");
            successE.setHeaderText(null);
            successE.setContentText("reservation ignorée !");
            successE.showAndWait();
             FXMLLoader loader = new FXMLLoader(getClass().getResource("DisplayReservations.fxml"));
            Parent root = loader.load();
            tableProduit.getScene().setRoot(root);
         }
    }
     
   

    
}
