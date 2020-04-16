/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Nour ghidaoui
 */
public class ArticleJ {
   
 
    
     private  StringProperty pTitre;

	private StringProperty pDescription;

	
	private StringProperty pPosition;
        
	private  IntegerProperty pID;
        private String pImage;
        private int pnbLikes=0;
         private int nbDislike=0;

   

    public int getNbDislike() {
        return nbDislike;
    }

    public void setNbDislike(int nbDislike) {
        this.nbDislike = nbDislike;
    }


    ArticleJ() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
 
        
        public void setpTitre(StringProperty pTitre)
        {
            this.pTitre=pTitre;
        }
        
        public void setpDescription(StringProperty pDescription)
        {
            this.pDescription=pDescription;
        }
        
        public void setpPosition(StringProperty pPosition)
        {
            this.pPosition=pPosition;
        }
        
        public void setpID(IntegerProperty pID)
        {
            this.pID=pID;
        }
       
        
    public int getpNbLikes() {
        return pnbLikes;
    }

    public void setpNbLikes(int pnbLikes) {
        this.pnbLikes = pnbLikes;
    }
	
	public ArticleJ(int pID,String pTitre, String pDescription,String pPosition,String pImage,int pnbLikes,int nbDislike) {
            this.pID = new SimpleIntegerProperty(pID);
		this.pTitre = new SimpleStringProperty(pTitre);
		this.pDescription = new SimpleStringProperty(pDescription);
		
		this.pPosition = new SimpleStringProperty(pPosition);
		this.pImage = new String(pImage);
                this.pnbLikes=pnbLikes;
                this.nbDislike=nbDislike;
   
                
          
	}
        
        
        public ArticleJ(int pID,String pTitre, String pDescription,String pPosition) {
            this.pID = new SimpleIntegerProperty(pID);
		this.pTitre = new SimpleStringProperty(pTitre);
		this.pDescription = new SimpleStringProperty(pDescription);
		
		this.pPosition = new SimpleStringProperty(pPosition);
		
         
	}

   
	
	public StringProperty getpTitre() {
		return pTitre;
	}
	
	public StringProperty getpDescription() {
		return pDescription;
	}
	
	
	public StringProperty getpPosition() {
		return pPosition;
	}
	public IntegerProperty getpID() {
		return pID;
	}

    public String getpImage() {
        return pImage;
    }

    public void setpImage(String pImage) {
        this.pImage = pImage;
    }

   
    @Override
    public String toString() {
        return "ArticleJ{" + "pTitre=" + pTitre + ", pDescription=" + pDescription + ", pPosition=" + pPosition + ", pID=" + pID + ", pImage=" + pImage +",nbDislike="+nbDislike+ ",pnbLikes="+pnbLikes+ '}';
    }
     
        
}
