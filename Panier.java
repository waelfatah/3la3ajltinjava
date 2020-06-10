/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.la3ajltin.entities;

import java.util.ArrayList;
import java.util.Vector;

/**
 *
 * @author Mega-PC
 */
public class Panier {

    private int quantite;
    public ArrayList<Formation> Article;
    

    public Panier(int quantite) {
        this.Article = new ArrayList<Formation>();
        this.quantite = quantite;
    }


    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public void ajouterQuantite(int qte) {
        this.quantite += qte;
    }
    
    public void ajouterProduit(Formation p){
        Article.add(p);
    }
}
