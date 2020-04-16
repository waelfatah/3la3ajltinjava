/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.la3ajltin.tests;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import edu.la3ajltin.entities.Produit;
import edu.la3ajltin.services.ProduitService;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.util.converter.FloatStringConverter;
import javafx.util.converter.IntegerStringConverter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import edu.la3ajltin.tools.ConnectionDB;
import java.sql.Connection;
import java.util.Optional;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * FXML Controller class
 *
 * @author Mega-PC
 */
public class DisplayProductController implements Initializable {

    @FXML
    private TableView<Produit> tableProduit;

    @FXML
    private TableColumn<Produit, Integer> col_id_produit;

    @FXML
    private TableColumn<Produit, String> col_nom_produit;

    @FXML
    private TableColumn<Produit, String> col_marque_produit;

    @FXML
    private TableColumn<Produit, Float> col_prix_produit;

    @FXML
    private TableColumn<Produit, Integer> col_quantite_produit;

    @FXML
    private TableColumn<Produit, String> col_type_produit;
    @FXML
    private Button ajouter;
    @FXML
    private Button supprimer;
    @FXML
    private Button refresh;
    @FXML
    private TextField search;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ProduitService PS = new ProduitService();
        // TODO
        col_id_produit.setCellValueFactory(new PropertyValueFactory<>("id_prod")); //from entity
        col_nom_produit.setCellValueFactory(new PropertyValueFactory<>("nom"));
        col_marque_produit.setCellValueFactory(new PropertyValueFactory<>("marque"));
        col_prix_produit.setCellValueFactory(new PropertyValueFactory<>("prix_prod"));
        col_quantite_produit.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        col_type_produit.setCellValueFactory(new PropertyValueFactory<>("type_prod"));
        //modification
        tableProduit.setEditable(true); //Rend le tableau "editable"
        col_nom_produit.setCellFactory(TextFieldTableCell.forTableColumn());
        col_marque_produit.setCellFactory(TextFieldTableCell.forTableColumn());
        col_prix_produit.setCellFactory(TextFieldTableCell.<Produit, Float>forTableColumn(new FloatStringConverter())); //Pour modifier les cellules de la colonne mentionnée
        col_quantite_produit.setCellFactory(TextFieldTableCell.<Produit, Integer>forTableColumn(new IntegerStringConverter()));
        col_type_produit.setCellFactory(ComboBoxTableCell.forTableColumn(FXCollections.observableArrayList("Accessoire", "Vélo")));
        //selection
        tableProduit.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE); //Pour séléctionner plusieurs lignes en même temps
        tableProduit.setItems(PS.afficherListeProduits());

    }

    @FXML
    private void DeleteProd(MouseEvent event) {
        ProduitService PS = new ProduitService();
        System.out.println("You clicked delete button!");

        ObservableList<Produit> listSelected = tableProduit.getSelectionModel().getSelectedItems();
        if (alertConfirmation2()) {
            listSelected.forEach((e) -> {
                PS.supprimerExpert(e);
            });
            tableProduit.getItems().removeAll(listSelected);
        }
    }

    @FXML
    private void Refresh(ActionEvent event) {
        ProduitService PS = new ProduitService();
        System.out.println("You clicked refresh button!");
        tableProduit.setItems(PS.afficherListeProduits());
    }

    @FXML
    private void Add(MouseEvent event) {
        System.out.println("You clicked add button!");
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/edu/la3ajltin/gui/Product.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.setResizable(false);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(DisplayProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void EditString(TableColumn.CellEditEvent<Produit, String> cellEditEvent) {
        ProduitService PS = new ProduitService();
        //System.out.println(Integer.valueOf("18")); //Au cas où la valeur éditée n'est pas une chaîne
        Produit p = tableProduit.getSelectionModel().getSelectedItem();

        String cellColName = cellEditEvent.getTableColumn().getText();
        System.out.println("You edited a cell: id = " + p.getId_prod() + " col = " + cellEditEvent.getTableColumn().getText());//test affichage colonne
        if (alertConfirmation()) {
            p.setNom(cellEditEvent.getNewValue());
            p.setMarque(cellEditEvent.getNewValue());
            p.setType_prod(cellEditEvent.getNewValue());

            PS.modifierProduit(p, cellColName);
            Alert Confirmation_Alert = new Alert(Alert.AlertType.INFORMATION); //Ok
            Confirmation_Alert.setTitle("Modififé!");
            Confirmation_Alert.setHeaderText(null);
            Confirmation_Alert.setContentText("Le produit a été modififé avec succès!");
            Confirmation_Alert.show();
        }
    }

    @FXML
    private void EditInteger(TableColumn.CellEditEvent<Produit, Integer> cellEditEvent) {
        ProduitService PS = new ProduitService();
        //System.out.println(Integer.valueOf("18")); //Au cas où la valeur éditée n'est pas une chaîne
        Produit p = tableProduit.getSelectionModel().getSelectedItem();

        String cellColName = cellEditEvent.getTableColumn().getText();
        System.out.println("You edited a cell: id = " + p.getId_prod() + " col = " + cellEditEvent.getTableColumn().getText());//test affichage colonne
        if (alertConfirmation()) {
            p.setQuantite(cellEditEvent.getNewValue());
            PS.modifierProduit(p, cellColName);
            Alert Confirmation_Alert = new Alert(Alert.AlertType.INFORMATION); //Ok
            Confirmation_Alert.setTitle("Modififé!");
            Confirmation_Alert.setHeaderText(null);
            Confirmation_Alert.setContentText("Le produit a été modififé avec succès!");
            Confirmation_Alert.show();
        }
    }

    @FXML
    private void EditFloat(TableColumn.CellEditEvent<Produit, Float> cellEditEvent) {
        ProduitService PS = new ProduitService();
        //System.out.println(Integer.valueOf("18")); //Au cas où la valeur éditée n'est pas une chaîne
        Produit p = tableProduit.getSelectionModel().getSelectedItem();

        String cellColName = cellEditEvent.getTableColumn().getText();
        System.out.println("You edited a cell: id = " + p.getId_prod() + " col = " + cellEditEvent.getTableColumn().getText());//test affichage colonne
        if (alertConfirmation()) {
            p.setPrix_prod(cellEditEvent.getNewValue());
            PS.modifierProduit(p, cellColName);
            Alert Confirmation_Alert = new Alert(Alert.AlertType.INFORMATION); //Ok
            Confirmation_Alert.setTitle("Modififé!");
            Confirmation_Alert.setHeaderText(null);
            Confirmation_Alert.setContentText("Le Produit a été modifié avec succès");
            Confirmation_Alert.show();

        }
    }

    @FXML
    private void ExportToExcel(ActionEvent event) throws FileNotFoundException, IOException {
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
            Workbook workbook = new HSSFWorkbook();
            Sheet spreadsheet = workbook.createSheet("sample");

            org.apache.poi.ss.usermodel.Row row = spreadsheet.createRow(0);

            for (int j = 0; j < tableProduit.getColumns().size(); j++) {
                row.createCell(j).setCellValue(tableProduit.getColumns().get(j).getText());
            }

            for (int i = 0; i < tableProduit.getItems().size(); i++) {
                row = spreadsheet.createRow(i + 1);
                for (int j = 0; j < tableProduit.getColumns().size(); j++) {
                    if (tableProduit.getColumns().get(j).getCellData(i) != null) {
                        row.createCell(j).setCellValue(tableProduit.getColumns().get(j).getCellData(i).toString());
                    } else {
                        row.createCell(j).setCellValue("");
                    }
                }
            }

            FileOutputStream fileOut = new FileOutputStream(selectedDirectory.getAbsolutePath() + "\\ListeExpert.xls");
            workbook.write(fileOut);
            fileOut.close();

            Alert Confirmation_Alert = new Alert(Alert.AlertType.INFORMATION); //Ok
            Confirmation_Alert.setTitle("EXPORTÉ!");
            Confirmation_Alert.setHeaderText(null);
            Confirmation_Alert.setContentText("Le tableau a été exporté en Excel avec succès!");
            Confirmation_Alert.show();
        }
    }

    @FXML
    private void ExportToPDF(ActionEvent event) throws FileNotFoundException, IOException, SQLException, DocumentException {
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
            String req = "SELECT * FROM produit";
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

            d.add(new Paragraph("Liste des produits:\n\n\n"));
            NamesRow.setWidthPercentage(100);
            NamesRow.setTotalWidth(new float[]{100, 100, 50, 50, 100});
            NamesRow.addCell("Nom Produit");
            NamesRow.addCell("Marque Produit");
            NamesRow.addCell("Prix Produit");
            NamesRow.addCell("Quantité");
            NamesRow.addCell("Type Produit");

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
    private void Search(KeyEvent ke) {
        ProduitService Ps = new ProduitService();
        System.out.println("You searched smth!");
        String rech = search.getText();
        tableProduit.setItems(Ps.rechercherProduit(rech));
    }

    private boolean alertConfirmation() {
        Alert a1 = new Alert(Alert.AlertType.CONFIRMATION);
        a1.setTitle("CONFIRMATION D'UPDATE");
        a1.setHeaderText("CONFIRMATION DE MODIFICATION");
        a1.setContentText("VOUS ETES SUR POUR LA MODIFICATION DE CE PRODUIT ?");
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
        a1.setContentText("VOUS ETES SUR POUR LA SUPPRESSION DE CE PRODUIT ?");
        Optional<ButtonType> result = a1.showAndWait();
        if (result.get() == ButtonType.OK) {
            return true;
        } else {
            return false;
        }

    }
}
