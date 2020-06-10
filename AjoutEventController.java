/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.la3ajltin.gui;

import java.awt.Image;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import edu.la3ajltin.entities.Event;
import edu.la3ajltin.services.EventService;
import java.sql.Date;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
/**
 * FXML Controller class
 *
 * @author pc
 */
public class AjoutEventController implements Initializable {

    @FXML
    private Button close;
    @FXML
    private TextField titre;
    @FXML
    private Button ajouter;
    @FXML
    private TextField description;
    @FXML
    private TextField image;
    @FXML
    private TextField capacité;
    @FXML
    private TextField adresse;
    EventService ES = new EventService();
    @FXML
    private Label NomError;
    @FXML
    private Label NomError1;
    @FXML
    private Label NomError2;
    @FXML
    private Label NomError3;
    @FXML
    private DatePicker dateDébut;
    @FXML
    private DatePicker dateFin;
    
    public int getcapacitéE() {
        return Integer.parseInt(capacité.getText());
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void closeStage(MouseEvent event) {
         Stage stage  = (Stage) close.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void AddProduit(ActionEvent event)throws IOException {
        EventService Ps = new EventService();
        if (!Ps.NomControle(titre.getText())) {
                NomError.setText("Ce champ doit contenir seulement des lettres!");
            } else {
                NomError.setText("");
            }
            if (!Ps.descriptionControle(description.getText())) {
                NomError1.setText("Ce champ doit contenir seulement des lettres!");
            } else {
                NomError1.setText("");
            }
            if (!Ps.lieuControle(adresse.getText())) {
                NomError2.setText("Ce champ doit contenir seulement des lettres!");
            } else {
                NomError2.setText("");
            }
            if (!Ps.QuantiteControle(capacité.getText())) {
                NomError3.setText("Ce champ doit contenir seulement des numéros!");
            } else {
                NomError3.setText("");
            }

            if (Ps.NomControle(titre.getText()) && dateDébut.getValue()!=null && dateFin.getValue()!=null&&(Ps.descriptionControle(description.getText()))
                    && (Ps.lieuControle(adresse.getText())) && (Ps.QuantiteControle(capacité.getText()))) {
                Event E = new Event(titre.getText(),Date.valueOf(dateDébut.getValue()),Date.valueOf(dateFin.getValue()), Integer.valueOf(capacité.getText()), adresse.getText(),description.getText(),image.getText());

                Ps.ajouterEvent(E);
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
                    trayIcon.displayMessage("Event, ".concat(titre.getText()), "Ajouté avec succès !!", TrayIcon.MessageType.INFO);
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
    
}
