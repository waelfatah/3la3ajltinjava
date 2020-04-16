/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.la3ajltin.services;

import edu.la3ajltin.entities.Panier;
import edu.la3ajltin.entities.Produit;
import edu.la3ajltin.tools.ConnectionDB;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author Mega-PC
 */
public class PanierService {
    ConnectionDB db = ConnectionDB.getInstance();
    Connection cn = db.getCnx();
    ProduitService Ps = new ProduitService();
    

    

}
