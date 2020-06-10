/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.la3ajltin.entities;
import java.sql.Date;
import java.time.LocalDate;
/**
 *
 * @author pc
 */
public class Event {
    
    private int id;
    private int id_user;
    private String titre;
    private Date dateDebutEvent ;
    private Date dateFinEvent;
    private int nbrDePlace;
    private String lieuEvent;
    private String descriptionEvent;
    private String image;


   
    
    public Event() {
    }
    
     public Event(int id, String titre, Date dateDebutEvent,Date dateFinEvent, int nbrDePlace,String lieuEvent,String descriptionEvent, String image) {
        this.id = id;
        this.titre = titre;
        this.dateDebutEvent = dateFinEvent;
        this.dateFinEvent= dateFinEvent;
        this.nbrDePlace= nbrDePlace;
        this.lieuEvent = lieuEvent;
        this.descriptionEvent= descriptionEvent;
        this.image=image;
     }
      public Event( String titre, Date dateDebutEvent,Date dateFinEvent, int nbrDePlace,String lieuEvent,String descriptionEvent, String image) {
        
        this.titre = titre;
        this.dateDebutEvent = dateFinEvent;
        this.dateFinEvent= dateFinEvent;
        this.nbrDePlace= nbrDePlace;
        this.lieuEvent = lieuEvent;
        this.descriptionEvent= descriptionEvent;
        this.image=image;
     }
    
    
    
  
     
      @Override
    public String toString() {
        return "Event{" + "id_event=" + id + ", title=" + titre + ", DateDebutEvent=" + dateDebutEvent + ", DateFinEvent=" + dateFinEvent + ", nbrDePlace=" + nbrDePlace +  ",lieuEvent=" + lieuEvent +", descriptionEvent=" + descriptionEvent +", image=" +image+ '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Date getDateDebutEvent() {
        return dateDebutEvent;
    }

    public void setDateDebutEvent(Date dateDebutEvent) {
        this.dateDebutEvent = dateDebutEvent;
    }

    public Date getDateFinEvent() {
        return dateFinEvent;
    }

    public void setDateFinEvent(Date dateFinEvent) {
        this.dateFinEvent = dateFinEvent;
    }

  

    public int getNbrDePlace() {
        return nbrDePlace;
    }

    public void setNbrDePlace(int nbrDePlace) {
        this.nbrDePlace = nbrDePlace;
    }

    public String getLieuEvent() {
        return lieuEvent;
    }

    public void setLieuEvent(String lieuEvent) {
        this.lieuEvent = lieuEvent;
    }

    public String getDescriptionEvent() {
        return descriptionEvent;
    }

    public void setDescriptionEvent(String descriptionEvent) {
        this.descriptionEvent = descriptionEvent;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
