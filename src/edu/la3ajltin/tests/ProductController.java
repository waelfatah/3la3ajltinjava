/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.la3ajltin.tests;

import edu.la3ajltin.entities.Produit;
import edu.la3ajltin.services.ProduitService;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Mega-PC
 */
public class ProductController implements Initializable {

    ObservableList<String> typeProdList = FXCollections
            .observableArrayList("Accessoire", "Vélo");
    /**
     * Initializes the controller class.
     */
 @FXML
    private Button close;
    @FXML
    private TextField nomProduit;

    @FXML
    private TextField marqueProduit;

    @FXML
    private TextField prixProduit;

    @FXML
    private TextField quantiteProduit;

    @FXML
    private Button ajouter;

    @FXML
    private ComboBox typeProduitBox;

    @FXML
    private Label NomError;

    @FXML
    private Label MarqueError;

    @FXML
    private Label PrixError;

    @FXML
    private Label QuantiteError;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        typeProduitBox.setItems(typeProdList);
    }

    @FXML
    public void AddProduit(ActionEvent event) throws IOException {
        ProduitService Ps = new ProduitService();
            if (!Ps.NomControle(nomProduit.getText())) {
                NomError.setText("Ce champ doit contenir seulement des lettres!");
            } else {
                NomError.setText("");
            }
            if (!Ps.MarqueControle(marqueProduit.getText())) {
                MarqueError.setText("Ce champ doit contenir seulement des lettres!");
            } else {
                MarqueError.setText("");
            }
            if (!Ps.PrixControle(prixProduit.getText())) {
                PrixError.setText("Ce champ doit être un entier!");
            } else {
                PrixError.setText("");
            }
            if (!Ps.QuantiteControle(quantiteProduit.getText())) {
                QuantiteError.setText("Ce champ doit contenir seulement des numéros!");
            } else {
                QuantiteError.setText("");
            }

            if (Ps.NomControle(nomProduit.getText()) && (Ps.MarqueControle(marqueProduit.getText()))
                    && (Ps.PrixControle(prixProduit.getText())) && (Ps.QuantiteControle(quantiteProduit.getText()))) {
                Produit P = new Produit(nomProduit.getText(), marqueProduit.getText(), Float.valueOf(prixProduit.getText()), Integer.valueOf(quantiteProduit.getText()), typeProduitBox.getValue().toString());

                Ps.ajouterProduit(P);
                try {
                    //Obtain only one instance of the SystemTray object
                    SystemTray tray = SystemTray.getSystemTray();

                    // If you want to create an icon in the system tray to preview
                    Image image = Toolkit.getDefaultToolkit().createImage("oui.png");
                    //Alternative (if the icon is on the classpath):
                    //Image image = Toolkit.getDefaultToolkit().createImage(getClass().getResource("icon.png"));

                    TrayIcon trayIcon = new TrayIcon(image, "Java AWT Tray Demo");
                    //Let the system resize the image if needed
                    trayIcon.setImageAutoSize(true);
                    //Set tooltip text for the tray icon
                    trayIcon.setToolTip("System tray icon demo");
                    tray.add(trayIcon);

                    // Display info notification:
                    trayIcon.displayMessage("Produit, ".concat(nomProduit.getText()), "Ajouté avec succès !!", TrayIcon.MessageType.INFO);
                    // Error:
                    // trayIcon.displayMessage("Hello, World", "Java Notification Demo", MessageType.ERROR);
                    // Warning:
                    // trayIcon.displayMessage("Hello, World", "Java Notification Demo", MessageType.WARNING);
                } catch (Exception ex) {
                    System.err.print(ex);
                }
                Stage stage = (Stage) ajouter.getScene().getWindow();
                stage.close();
            } else {
                   System.out.println("Nope");
            }
        
    }
    @FXML
    private void closeStage(MouseEvent event) {
        
        Stage stage  = (Stage) close.getScene().getWindow();
        stage.close();
    }
}
