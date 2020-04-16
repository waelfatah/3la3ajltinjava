package Models; 
import java.sql.Date;

public class Article {
    
    private int idArticle;
    private String Titre;
    private String Description;
    private Date Date;
    private int id_user;
    private String image;
    private String updated_at;
    private String etat;
    private int pnbLikes=0;
    private int nbDislike=0;

    public int getPnbLikes() {
        return pnbLikes;
    }

    public void setPnbLikes(int pnbLikes) {
        this.pnbLikes = pnbLikes;
    }

    public int getNbDislike() {
        return nbDislike;
    }

    public void setNbDislike(int nbDislike) {
        this.nbDislike = nbDislike;
    }
    

    public int getIdArticle() {
        return idArticle;
    }

    public void setIdArticle(int idArticle) {
        this.idArticle = idArticle;
    }

    public String getTitre() {
        return Titre;
    }

    public void setTitre(String Titre) {
        this.Titre = Titre;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public Date getDate() {
        return Date;
    }

    public void setDate(Date Date) {
        this.Date = Date;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public Article(int idArticle, String Titre, String Description, Date Date, int id_user, String image, String updated_at) {
        this.idArticle = idArticle;
        this.Titre = Titre;
        this.Description = Description;
        this.Date = Date;
        this.id_user = id_user;
        this.image = image;
        this.updated_at = updated_at;
    }
    
    public Article ()
    {
       
    }
  
 
       public Article(int idArticle, String Titre, String Description, String etat,int id_user, Date Date,int pnbLikes,int nbDislikes) {
        this.idArticle = idArticle;
        this.Titre = Titre;
        this.Description = Description;
    
        this.id_user = id_user;
        this.image = image;
  
            this.Date = Date;
            this.pnbLikes=pnbLikes;
            this.nbDislike=nbDislike;
    }
       
       
       public Article(int idArticle, String Titre, String Description,String image) {
        this.idArticle = idArticle;
        this.Titre = Titre;
        this.Description = Description;
    
        this.image = image;
  
          
    }
       
       
         public Article(int idArticle, String Titre, String Description,int pnbLikes,int nbDislikes) {
        this.idArticle = idArticle;
        this.Titre = Titre;
        this.Description = Description;
      this.pnbLikes=pnbLikes;
            this.nbDislike=nbDislike;
    }
     public Article(int idArticle, String Titre, String Description, String etat,int id_user, Date Date) {
        this.idArticle = idArticle;
        this.Titre = Titre;
        this.Description = Description;
        this.etat=etat;
        this.id_user = id_user;
        this.image = image;
  
            this.Date = Date;
    }
        public Article(int idArticle, String Titre,String Description, String etat,String image,Date date,int id_user) {
        this.idArticle = idArticle;
        this.Titre = Titre;
        this.Description = Description;
        this.etat=etat;
        this.image=image;
        this.Date = Date;
        this.id_user = id_user;
        
        }
     

    @Override
    public String toString() {
        return "Article{" + "idArticle=" + idArticle + ", Titre=" + Titre + ", Description=" + Description + ", Date=" + Date + ", id_user=" + id_user + ", image=" + image + ", updated_at=" + updated_at + ", etat=" + etat + '}';
    }
    
    
    
    
}
