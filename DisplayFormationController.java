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
import edu.la3ajltin.entities.Event;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import edu.la3ajltin.entities.Formation;
import edu.la3ajltin.services.EventService;
import edu.la3ajltin.services.FormationService;
import edu.la3ajltin.tools.ConnectionDB;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

/**
 * FXML Controller class
 *
 * @author pc
 */
public class DisplayFormationController implements Initializable {

    @FXML
    private Button ajouter;
    @FXML
    private Button supprimer;
    @FXML
    private Button refresh;
    @FXML
    private TableView<Formation> tableProduit;
    @FXML
    private TableColumn<Formation, Integer> col_id_f;
    @FXML
    private TableColumn<Formation, String> col_titre;
    @FXML
    private TableColumn<Formation, LocalDate> col_dateD;
    @FXML
    private TableColumn<Formation, LocalDate> col_dateF;
    @FXML
    private TableColumn<Formation, Integer> col_capacité;
    @FXML
    private TableColumn<Formation, String> col_adresse;
    @FXML
    private TableColumn<Formation, String> col_description;
    @FXML
    private TableColumn<Formation, String> Photo;
    @FXML
    private TextField search;
    @FXML
    private Button supprimer1;
      FormationService ES = new FormationService();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         FormationService PS = new FormationService();
        // TODO
        col_id_f.setCellValueFactory(new PropertyValueFactory<>("id")); //from entity
        col_titre.setCellValueFactory(new PropertyValueFactory<>("titleF"));
        col_dateD.setCellValueFactory(new PropertyValueFactory<>("dateDebutFormation"));
        col_dateF.setCellValueFactory(new PropertyValueFactory<>("dateFinFormation"));
        col_capacité.setCellValueFactory(new PropertyValueFactory<>("nbrDePlaceF"));
        col_adresse.setCellValueFactory(new PropertyValueFactory<>("lieuFormation"));
       col_description.setCellValueFactory(new PropertyValueFactory<>("descriptionFormation"));
       Photo.setCellValueFactory(new PropertyValueFactory<>("image"));
//        modification
       /* tableProduit.setEditable(true); //Rend le tableau "editable"*/
        col_titre.setCellFactory(TextFieldTableCell.forTableColumn());
        col_capacité.setCellFactory(TextFieldTableCell.<Formation, Integer>forTableColumn(new IntegerStringConverter()));
        col_adresse.setCellFactory(TextFieldTableCell.forTableColumn());
        col_description.setCellFactory(TextFieldTableCell.forTableColumn());
        //selection
        tableProduit.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE); //Pour séléctionner plusieurs lignes en même temps
        tableProduit.setItems(PS.afficherListeFormation());
        // TODO
        // TODO
    }    

    @FXML
    private void Add(MouseEvent event) {
        System.out.println("You clicked add button!");
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/edu/la3ajltin/gui/AjoutFormation.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.setResizable(false);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(DisplayEvenementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void DeleteProd(MouseEvent event) {
         FormationService PS = new FormationService();
        System.out.println("You clicked delete button!");

        ObservableList<Formation> listSelected = tableProduit.getSelectionModel().getSelectedItems();
        if (alertConfirmation2()) {
            listSelected.forEach((e) -> {
                PS.supprimerExpert(e);
            });
            tableProduit.getItems().removeAll(listSelected);
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
    private void Refresh(ActionEvent event) {
        FormationService PS = new FormationService();
        System.out.println("You clicked refresh button!");
        tableProduit.setItems(PS.afficherListeFormation());
    }

    public void changeNomCellEvent(TableColumn.CellEditEvent edittedCell
    ) {
        Formation EventSelected = tableProduit.getSelectionModel().getSelectedItem();
        EventSelected.setTitreF(edittedCell.getNewValue().toString());
    }

    @FXML
    public void changeDescriptionCellEvent(TableColumn.CellEditEvent edittedCell
    ) {
        Formation EventSelected = tableProduit.getSelectionModel().getSelectedItem();
        EventSelected.setDescriptionFormation(edittedCell.getNewValue().toString());
    }
    

    @FXML
    public void changeAdresseCellEvent(TableColumn.CellEditEvent edittedCell
    ) {
        Formation EventSelected = tableProduit.getSelectionModel().getSelectedItem();
        EventSelected.setLieuFormation(edittedCell.getNewValue().toString());
    }
    @FXML
    public void changeCapacitéCellEvent(TableColumn.CellEditEvent edittedCell
    ) {
        Formation EventSelected = tableProduit.getSelectionModel().getSelectedItem();
        EventSelected.setNbrDePlaceF(Integer.parseInt(edittedCell.getNewValue().toString()));
    }

    @FXML
    private void ExportToExcel(ActionEvent event) {
    }

    @FXML
    private void ExportToPDF(ActionEvent event)
         throws FileNotFoundException, IOException, SQLException, DocumentException {
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
            String req = "SELECT * FROM formation";
            Statement st;
            ResultSet rs;

            st = cn.createStatement();
            rs = st.executeQuery(req);
            ResultSetMetaData rsmd = rs.getMetaData();
            int colNb = rsmd.getColumnCount();
            PdfPTable NamesRow = new PdfPTable(5);
            Document d = new Document(PageSize.A4.rotate());
            PdfWriter.getInstance(d, new FileOutputStream(selectedDirectory.getAbsolutePath() + "\\ListeFormation.pdf"));

            d.open();

            d.add(new Paragraph("Liste des evenements:\n\n\n"));
            NamesRow.setWidthPercentage(100);
            NamesRow.setTotalWidth(new float[]{100, 100, 50, 50, 100});
            NamesRow.addCell("Titre");
            NamesRow.addCell("DateDébut");
            NamesRow.addCell("DateFin");
            NamesRow.addCell("nbrDePlace");
            NamesRow.addCell("Type Adresse");
            NamesRow.addCell("Description");
            NamesRow.addCell("Photo");

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

    @FXML
    private void Search(KeyEvent event) {
         FormationService Ps = new FormationService();
        System.out.println("You searched smth!");
        String rech = search.getText();
        tableProduit.setItems(Ps.rechercherFormation(rech));
    }

    @FXML
    private void EditEvent(ActionEvent event) {
        if (tableProduit.getSelectionModel().getSelectedItem() != null) {

            Formation E = tableProduit.getSelectionModel().getSelectedItem();

            ES.modifierFormation(E);
            Alert MealAlert = new Alert(Alert.AlertType.INFORMATION);
            MealAlert.setTitle("Modifier");
            MealAlert.setHeaderText(null);
            MealAlert.setContentText("Evenement Modifié !");
            MealAlert.showAndWait();

        } else {
            //Alert Select Meal :
            Alert selectMealAlert = new Alert(Alert.AlertType.WARNING);
            selectMealAlert.setTitle("Selectionner un evenement");
            selectMealAlert.setHeaderText(null);
            selectMealAlert.setContentText("You need to select Event first!");
            selectMealAlert.showAndWait();
            //Alert Select Meal !
        }
        
    }
    
}
