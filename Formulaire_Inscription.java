/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.la3ajltin.entities;

import java.sql.Date;

/**
 *
 * @author pc
 */
public class Formulaire_Inscription {
    private int id;
    private int id_formation;
    private int id_user;
    private String nomParticipant;
    private String prenomParticipant;
    private Date dateInscription;
    private int nombrePlaceReservation;
    private int telephone;
    private String email;
    private String etat;
    
    
    public Formulaire_Inscription(){
        
    }
   
    public Formulaire_Inscription(int id,int id_formation,int id_user,String nomParticipant,String prenomParticipant,Date dateInscription,int nombrePlaceReservation,int telephone,String email,String etat){
        this.id=id;
        this.id_formation=id_formation;
        this.id_user=id_user;
        this.nomParticipant=nomParticipant;
        this.prenomParticipant=prenomParticipant;
        this.dateInscription=dateInscription;
        this.nombrePlaceReservation=nombrePlaceReservation;
        this.telephone=telephone;
        this.email=email;
        this.etat=etat;
    }
     public Formulaire_Inscription(int id,Date dateInscription){
         
        this.id=id;
        this.dateInscription=dateInscription; 
        
    }
    
    public Formulaire_Inscription(int id_formation,int id_user,String nomParticipant,String prenomParticipant,Date dateInscription,int nombrePlaceReservation,int telephone,String email,String etat){
        
        this.id_formation=id_formation;
        this.id_user=id_user;
        this.nomParticipant=nomParticipant;
        this.prenomParticipant=prenomParticipant;
        this.dateInscription=dateInscription;
        this.nombrePlaceReservation=nombrePlaceReservation;
        this.telephone=telephone;
        this.email=email;
        this.etat=etat;
    }

    public Formulaire_Inscription(int id_formation, int id_user, Date dateInscription, int nombrePlaceReservation, String etat) {
        this.id_formation = id_formation;
        this.id_user = id_user;
        this.dateInscription = dateInscription;
        this.nombrePlaceReservation = nombrePlaceReservation;
        this.etat = etat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_formation() {
        return id_formation;
    }

    public void setId_formation(int id_formation) {
        this.id_formation = id_formation;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getNomParticipant() {
        return nomParticipant;
    }

    public void setNomParticipant(String nomParticipant) {
        this.nomParticipant = nomParticipant;
    }

    public String getPrenomParticipant() {
        return prenomParticipant;
    }

    public void setPrenomParticipant(String prenomParticipant) {
        this.prenomParticipant = prenomParticipant;
    }

    public Date getDateInscription() {
        return dateInscription;
    }

    public void setDateInscription(Date dateInscription) {
        this.dateInscription = dateInscription;
    }

    public int getNombrePlaceReservation() {
        return nombrePlaceReservation;
    }

    public void setNombrePlaceReservation(int nombrePlaceReservation) {
        this.nombrePlaceReservation = nombrePlaceReservation;
    }

    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }
    
}
