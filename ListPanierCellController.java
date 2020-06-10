/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.la3ajltin.tests;

import edu.la3ajltin.entities.Commande;
import edu.la3ajltin.entities.Formation;
import edu.la3ajltin.entities.Formulaire_Inscription;
//import edu.la3ajltin.entities.Produit;
import edu.la3ajltin.entities.Session;
import static edu.la3ajltin.entities.Session.getPanier;
import edu.la3ajltin.entities.fos_user;
import edu.la3ajltin.services.CommandeProduitsService;
import edu.la3ajltin.services.CommandeService;
import edu.la3ajltin.services.FormationService;
import edu.la3ajltin.services.ProduitService;
import edu.la3ajltin.services.ReservationService;
import edu.la3ajltin.services.fos_userService;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Stream;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

/**
 * FXML Controller class
 *
 * @author Mega-PC
 */
public class ListPanierCellController implements Initializable {

    @FXML
    private ListView<Formation> List;
    private ObservableList<Formation> data = FXCollections.observableArrayList();
    @FXML
    private Button buyButton;
    @FXML
    private Label prixtot;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        data = Session.getPanier();
        List.setItems(data);
        List.setCellFactory(PanierListview -> new PanierCellController());
        Stream<Formation> sp = Session.getPanier().stream();
        //double sum = sp.mapToDouble(p -> p.getPrixTotal()).sum();
       // prixtot.setText(String.valueOf(sum));
    }

    @FXML
    private void changeColorBuy(MouseEvent event) {
        buyButton.setStyle("-fx-background-color : #f26722");
    }

    @FXML
    private void remainColorBuy(MouseEvent event) {
        buyButton.setStyle("-fx-background-color : #3b3b3c");
    }

    @FXML
    private void ProceedWithChekout(ActionEvent event) throws Exception {
   

    }
/*
    public void sendInvoice(Commande c) throws SQLException {

        fos_userService fs = new fos_userService();
        CommandeProduitsService cs = new CommandeProduitsService();
        data = cs.afficherProduitsCommandes(104);
        String from = "waelfateh98@gmail.com";
        String pwd = "walloulti98";
        // Assuming you are sending email from localhost
        String host = "smtp.gmail.com";
        int q1 = cs.getQuantiteProduit(104, data.get(0).getId_prod());
        int q2 = cs.getQuantiteProduit(104, data.get(1).getId_prod());
        Transport t = null;
        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.setProperty("mail.smtp.host", host);

        // Get the default Session object.
        javax.mail.Session session = javax.mail.Session.getDefaultInstance(properties, null);

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            fos_user us = Session.getUser();
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(us.getEmail()));
            // set body of the email. 
            message.setContent("<head>\n"
                    + "    <meta charset=\"utf-8\">\n"
                    + "    <title>A simple, clean, and responsive HTML invoice template</title>\n"
                    + "    \n"
                    + "    <style>\n"
                    + "    .invoice-box {\n"
                    + "        max-width: 800px;\n"
                    + "        margin: auto;\n"
                    + "        padding: 30px;\n"
                    + "        border: 1px solid #eee;\n"
                    + "        box-shadow: 0 0 10px rgba(0, 0, 0, .15);\n"
                    + "        font-size: 16px;\n"
                    + "        line-height: 24px;\n"
                    + "        font-family: 'Helvetica Neue', 'Helvetica', Helvetica, Arial, sans-serif;\n"
                    + "        color: #555;\n"
                    + "    }\n"
                    + "    \n"
                    + "    .invoice-box table {\n"
                    + "        width: 100%;\n"
                    + "        line-height: inherit;\n"
                    + "        text-align: left;\n"
                    + "    }\n"
                    + "    \n"
                    + "    .invoice-box table td {\n"
                    + "        padding: 5px;\n"
                    + "        vertical-align: top;\n"
                    + "    }\n"
                    + "    \n"
                    + "    .invoice-box table tr td:nth-child(2) {\n"
                    + "        text-align: right;\n"
                    + "    }\n"
                    + "    \n"
                    + "    .invoice-box table tr.top table td {\n"
                    + "        padding-bottom: 20px;\n"
                    + "    }\n"
                    + "    \n"
                    + "    .invoice-box table tr.top table td.title {\n"
                    + "        font-size: 45px;\n"
                    + "        line-height: 45px;\n"
                    + "        color: #333;\n"
                    + "    }\n"
                    + "    \n"
                    + "    .invoice-box table tr.information table td {\n"
                    + "        padding-bottom: 40px;\n"
                    + "    }\n"
                    + "    \n"
                    + "    .invoice-box table tr.heading td {\n"
                    + "        background: #eee;\n"
                    + "        border-bottom: 1px solid #ddd;\n"
                    + "        font-weight: bold;\n"
                    + "    }\n"
                    + "    \n"
                    + "    .invoice-box table tr.details td {\n"
                    + "        padding-bottom: 20px;\n"
                    + "    }\n"
                    + "    \n"
                    + "    .invoice-box table tr.item td{\n"
                    + "        border-bottom: 1px solid #eee;\n"
                    + "    }\n"
                    + "    \n"
                    + "    .invoice-box table tr.item.last td {\n"
                    + "        border-bottom: none;\n"
                    + "    }\n"
                    + "    \n"
                    + "    .invoice-box table tr.total td:nth-child(2) {\n"
                    + "        border-top: 2px solid #eee;\n"
                    + "        font-weight: bold;\n"
                    + "    }\n"
                    + "    \n"
                    + "    @media only screen and (max-width: 600px) {\n"
                    + "        .invoice-box table tr.top table td {\n"
                    + "            width: 100%;\n"
                    + "            display: block;\n"
                    + "            text-align: center;\n"
                    + "        }\n"
                    + "        \n"
                    + "        .invoice-box table tr.information table td {\n"
                    + "            width: 100%;\n"
                    + "            display: block;\n"
                    + "            text-align: center;\n"
                    + "        }\n"
                    + "    }\n"
                    + "    \n"
                  + "    /** RTL **//*\n"
                  /*  + "    .rtl {\n"
                    + "        direction: rtl;\n"
                    + "        font-family: Tahoma, 'Helvetica Neue', 'Helvetica', Helvetica, Arial, sans-serif;\n"
                    + "    }\n"
                    + "    \n"
                    + "    .rtl table {\n"
                    + "        text-align: right;\n"
                    + "    }\n"
                    + "    \n"
                    + "    .rtl table tr td:nth-child(2) {\n"
                    + "        text-align: left;\n"
                    + "    }\n"
                    + "    </style>\n"
                    + "</head><body>\n"
                    + "    <div class=" + "invoice-box" + ">\n"
                    + "        <table cellpadding=" + 0 + " cellspacing=" + 0 + ">\n"
                    + "            <tr class=" + "top" + ">\n"
                    + "                <td colspan=" + 2 + ">\n"
                    + "                    <table>\n"
                    + "                        <tr>\n"
                    + "                            <td class=\"title\">\n"
                    + "                                <img src=" + "<a href=\"https://ibb.co/hLrxnY5\"><img src=\"https://i.ibb.co/5GCXzKD/3la3ajltinfront.png\" alt=\"3la3ajltinfront\" border=" + 0 + "></a>"
                    + "                            </td>\n"
                    + "                            \n"
                    + "                            <td>\n"
                    + "                                "
                  /*  + "                                Created: " + c.getDate() + "<br>\n"
                    + "                            </td>\n"
                    + "                        </tr>\n"
                    + "                    </table>\n"
                    + "                </td>\n"
                    + "            </tr>\n"
                    + "            \n"
                    + "            <tr class=\"information\">\n"
                    + "                <td colspan=" + 2 + ">\n"
                    + "                    <table>\n"
                    + "                        <tr>\n"
                    + "                            <td> Merci de nous faire confiance !"
                    + "                            </td>\n"
                    + "                            \n"
                    + "                            <td>\n"
                    + "                                3la3ajltin Corp.<br>\n"
                    + "                                Wael Fatah<br>\n"
                    + "                                wael.fatah@esprit.tn\n"
                    + "                            </td>\n"
                    + "                        </tr>\n"
                    + "                    </table>\n"
                    + "                </td>\n"
                    + "            </tr>\n"
                    + "            \n"
                    + "            \n"
                    + "            <tr class=\"heading\">\n"
                    + "                <td>\n"
                    + "                    Produit\n"
                    + "                </td>\n"
                    + "                \n"
                    + "                <td>\n"
                    + "                    Quantite\n"
                    + "                </td>\n"
                    + "                <td>\n"
                    + "                    Prix\n"
                    + "                </td>\n"
                    + "            </tr>\n"
                    + "            \n"
                    + "            <tr class=" + "item" + ">\n"
                    + "                <td>\n"
                    + "                   " + data.get(0).getNom() + "\n"
                    + "                </td>\n"
                    + "                \n"
                    + "                <td>\n"
                    + "                    " + q1 + "\n"
                    + "                </td>\n"
                    + "                <td>\n"
                    + "                    " + data.get(0).getPrix_prod() + "\n"
                    + "                </td>\n"
                    + "            </tr>\n"
                    + "            \n"
                    + "            <tr class=" + "item" + ">\n"
                    + "                <td>\n"
                    + "                   " + data.get(1).getNom() + "\n"
                    + "                </td>\n"
                    + "                \n"
                    + "                <td>\n"
                    + "                    " + q2 + "\n"
                    + "                </td>\n"
                    + "                <td>\n"
                    + "                    " + data.get(1).getPrix_prod() + "\n"
                    + "                </td>\n"
                    + "            </tr>\n"
                    + "            \n"
                    + "            <tr class=" + "item" + ">\n"
                    + "                <td>\n"
                    + "                   " + data.get(2).getNom() + "\n"
                    + "                </td>\n"
                    + "                \n"
                    + "                <td>\n"
                    + "                    " + q1 + "\n"
                    + "                </td>\n"
                    + "                <td>\n"
                    + "                    " + data.get(2).getPrix_prod() + "\n"
                    + "                </td>\n"
                    + "            </tr>\n"
                    + "            \n"
                    + "            <tr class=\"total\">\n"
                    + "                <td></td>\n"
                    + "                \n"
                    + "                <td>\n"
                    + "                   Total: " + c.getPrix_total() + "\n"
                    + "                </td>\n"
                    + "            </tr>\n"
                    + "        </table>\n"
                    + "    </div>\n"
                    + "</body>", "text/html");

           /* t = session.getTransport("smtps");
            t.connect(host, from, pwd);
            // Send message
            t.sendMessage(message, message.getAllRecipients());
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    } 

   /* public void sendMail(Produit p) {
        // Recipient's email ID needs to be mentioned.

        // Sender's email ID needs to be mentioned
        fos_userService fs = new fos_userService();

        String from = "waelfateh98@gmail.com";
        String pwd = "walloulti98";
        // Assuming you are sending email from localhost
        String host = "smtp.gmail.com";

        Transport t = null;
        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.setProperty("mail.smtp.host", host);

        // Get the default Session object.
        javax.mail.Session session = javax.mail.Session.getDefaultInstance(properties, null);

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            String[] emails = fs.getAdminsMails().stream().map(a -> a).toArray(String[]::new);
            for (String email : emails) {
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
            }

            // Set Subject: header field
            message.setSubject("Alerte: Ce produit va expirer. Vous devez en rajouter");

            // Now set the actual message
            message.setText("Bonjour,\nLe produit " + p.getNom() + " d'ID : " + p.getId_prod()
                    + "\nVa expirer, vous devez en commander au près de vos partenaires.");
            t = session.getTransport("smtps");
            t.connect(host, from, pwd);
            // Send message
            t.sendMessage(message, message.getAllRecipients());
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }*/
}

