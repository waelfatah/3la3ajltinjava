/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.la3ajltin.entities;

import edu.la3ajltin.services.CommandeService;
import edu.la3ajltin.services.fos_userService;
import java.util.Iterator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Mega-PC
 */
public class Session {
         private static int idUser;
         private static int idCommande;
         private static ObservableList<Produit> panier=FXCollections.observableArrayList();
         

    public static void start(int currentUserID) {
        idUser = currentUserID;
        
    }
    public static void setIdComm(int idComm){
        idCommande = idComm;
    }
    public static int getCurrentComm(){
        return idCommande;
    }
    public static int getCurrentSession() throws Exception {
        if (idUser != -1) {
            return idUser;
        } else {
            throw new Exception("Session has not started yet!");
        }
    }

    public static void close() throws Exception {
        if (idUser != -1) {
            idUser = -1;
        } else {
            throw new Exception("Session has not started yet!");
        }
    }
    public static fos_user getUser()
    {
        fos_userService u = new fos_userService();
        
        fos_user user = u.get_User(idUser);
        return user;
        
    }

    public static ObservableList<Produit> getPanier() {
        return panier;
    }

    public static Commande getCommande(int id){
        Commande comm = null;
        CommandeService cs = new CommandeService();
        comm=cs.afficherUserCommande(id);
        return comm;
    }
}

