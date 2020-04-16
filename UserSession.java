/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.time.LocalDate;
import java.util.Set;

/**
 *
 * @author NacimGastli
 */
public final  class UserSession {
    private static UserSession instance;
    private String nom;
    private String prenom;
    private int id;
    private LocalDate last_login;
    private static int idUser;

    private UserSession(String nom, String prenom, int id) {
    this.nom=nom;
    this.prenom=prenom;
    this.id=id;
    }

    @Override
    public String toString() {
        return "UserSession{" + "nom=" + nom + ", prenom=" + prenom + ", id=" + id + '}';
    }

   
    public static int getCurrentSession(){
        idUser = getInstace().getId();
        return idUser;
    }
    
    public static UserSession getInstace(String nom, String prenom,int id) {
        if(instance == null) {
            instance = new UserSession(nom,prenom,id);
        }
        return instance;
    }
      public static UserSession getInstace() {
        if(instance == null) {
            instance = new UserSession();
        }
        return instance;
    }
    

    public UserSession() {
          nom = "";
        prenom = "";
        id=0;
    }

    public static UserSession UserSessionLogOut() {
        instance.nom="";
        instance.prenom="";
        instance.id=0;
              
        return instance;
    }

    public void UserSessionSignIn(String nom, String prenom, int id) {
        this.nom = nom;
        this.prenom = prenom;
        this.id =id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public LocalDate getLast_login() {
        return last_login;
    }

    public void setLast_login(LocalDate last_login) {
        this.last_login = last_login;
    }

}
