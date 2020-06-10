/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.la3ajltin.entities;


import static edu.la3ajltin.entities.Session.getPanier;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Iterator;

/**
 *
 * @author pc
 */
public class Formation {
    
     private int id;
    private String titreF;
    private Date dateDebutFormation ;
    private Date dateFinFormation;
    private int nbrDePlaceF;
    private String lieuFormation;

   
    private String descriptionFormation;
    private String image;
    private int qteApresReservation;
   private int nbReservation;
    private int qteFormationReservation;
    
    public Formation() {
    }
     public Formation(int id, String titreF) {
        this.id = id;
        this.titreF = titreF; }
    
     public Formation(int id, String titreF, Date dateDebutFormation,Date dateFinFormation, int nbrDePlaceF,String lieuFormation,String descriptionFormation, String image) {
        this.id = id;
        this.titreF = titreF;
        this.dateDebutFormation = dateFinFormation;
        this.dateFinFormation= dateFinFormation;
        this.nbrDePlaceF= nbrDePlaceF;
        this.lieuFormation = lieuFormation;
        this.descriptionFormation= descriptionFormation;
        this.image=image;
     }
     
     public Formation( String titreF, Date dateDebutFormation,Date dateFinFormation, int nbrDePlaceF,String lieuFormation,String descriptionFormation, String image) {
        this.titreF = titreF;
        this.dateDebutFormation = dateFinFormation;
        this.dateFinFormation= dateFinFormation;
        this.nbrDePlaceF= nbrDePlaceF;
        this.lieuFormation = lieuFormation;
        this.descriptionFormation= descriptionFormation;
        this.image=image;
     }
     public Formation( String titreF, Date dateDebutFormation,Date dateFinFormation, int nbrDePlaceF,String lieuFormation,String descriptionFormation, String image,int qteApresReservation,int nbReservation,int qteFormationReservation){
        this.dateFinFormation= dateFinFormation;
        this.nbrDePlaceF= nbrDePlaceF;
        this.lieuFormation = lieuFormation;
        this.descriptionFormation= descriptionFormation;
        this.image=image;
        this.qteApresReservation=qteApresReservation;
        this.nbReservation=nbReservation;
        this.qteFormationReservation=qteFormationReservation;
     }
   
  

  
   public int getQteFormationReservation() {
        return qteFormationReservation;
    }

    public void setQteFormationReservation(int qteFormationReservation) {
        this.qteFormationReservation = qteFormationReservation;
    }
     
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitreF() {
        return titreF;
    }

    public void setTitreF(String titreF) {
        this.titreF = titreF;
    }

    public Date getDateDebutFormation() {
        return dateDebutFormation;
    }

    public void setDateDebutFormation(Date dateDebutFormation) {
        this.dateDebutFormation = dateDebutFormation;
    }

    public Date getDateFinFormation() {
        return dateFinFormation;
    }

    public void setDateFinFormation(Date dateFinFormation) {
        this.dateFinFormation = dateFinFormation;
    }


    public int getNbrDePlaceF() {
        return nbrDePlaceF;
    }

    public void setNbrDePlaceF(int nbrDePlaceF) {
        this.nbrDePlaceF = nbrDePlaceF;
    }

    public String getLieuFormation() {
        return lieuFormation;
    }

    public void setLieuFormation(String lieuFormation) {
        this.lieuFormation = lieuFormation;
    }

    public String getDescriptionFormation() {
        return descriptionFormation;
    }

    public void setDescriptionFormation(String descriptionFormation) {
        this.descriptionFormation = descriptionFormation;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getQteApresReservation() {
        return qteApresReservation;
    }

    public void setQteApresReservation(int qteApresReservation) {
        this.qteApresReservation = qteApresReservation;
    }

    public int getNbReservation() {
        this.nbReservation = this.getNbrDePlaceF()-this.qteApresReservation;  
        
        return nbReservation;
    }

    public void setNbReservation(int nbReservation) {
        this.nbReservation = nbReservation;
    }
    public void ajouterQuantite(int qte) {
        this.qteFormationReservation += qte;
    }
    
     public static boolean getProduit(int id) {
        Formation produit;
        Iterator<Formation> ip = getPanier().iterator();
        while (ip.hasNext()) {
            produit = ip.next();
            if (produit.getId() == id) {
                return true;

            }
        }
        return false;
    }
     
      @Override
    public String toString() {
        return "Formation{" + "id_formation=" + id + ", title=" + titreF + ", DateDebutFormation=" + dateDebutFormation + ", DateFinFormation=" + dateFinFormation + ", nbrDePlace=" + nbrDePlaceF +  ",lieuFormation=" + lieuFormation +", descriptionFormation=" + descriptionFormation +", image=" +image+ '}';
    }


}
