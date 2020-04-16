/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import Models.Article;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.RowConstraints;

/**
 *
 * @author Nour ghidaoui
 */
public class ArticleListCell extends ListCell<Article> {
   
     private final GridPane gridPane = new GridPane(); 
    private final Label titreLabel = new Label(); 
    private final Label nbLikesLabel = new Label();
    private final Label nbDislikeLabel = new Label();

    private final ImageView articleIcon = new ImageView(); 
    private final AnchorPane content = new AnchorPane();
    
    public ArticleListCell() { 
        articleIcon.setFitWidth(45); 
        articleIcon.setPreserveRatio(true); 
        GridPane.setConstraints(articleIcon, 0, 0, 1, 3); 
        GridPane.setValignment(articleIcon, VPos.TOP); 
        // 
        titreLabel.setStyle("-fx-opacity: 0.75;"); 
        GridPane.setConstraints(titreLabel, 1, 0); 
     
        //
        nbLikesLabel.setStyle("-fx-opacity: 0.75;"); 
        GridPane.setConstraints(nbLikesLabel, 2, 0); 
        //                
        
        nbDislikeLabel.setStyle("-fx-opacity: 0.75;"); 
        GridPane.setConstraints(nbDislikeLabel, 2, 1); 
        //                
        gridPane.getColumnConstraints().add(new ColumnConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.NEVER, HPos.LEFT, true)); 
        gridPane.getColumnConstraints().add(new ColumnConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.ALWAYS, HPos.LEFT, true)); 
        gridPane.getColumnConstraints().add(new ColumnConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.NEVER, HPos.LEFT, true)); 
        gridPane.getColumnConstraints().add(new ColumnConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.NEVER, HPos.LEFT, true)); 
        gridPane.getRowConstraints().add(new RowConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.NEVER, VPos.CENTER, true)); 
        gridPane.getRowConstraints().add(new RowConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.NEVER, VPos.CENTER, true)); 
        gridPane.getRowConstraints().add(new RowConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.ALWAYS, VPos.CENTER, true)); 
        gridPane.setHgap(6); 
        gridPane.setVgap(6); 
        gridPane.getChildren().setAll(articleIcon, titreLabel,  nbLikesLabel,nbDislikeLabel); 
        AnchorPane.setTopAnchor(gridPane, 0d); 
        AnchorPane.setLeftAnchor(gridPane, 0d); 
        AnchorPane.setBottomAnchor(gridPane, 0d); 
        AnchorPane.setRightAnchor(gridPane, 0d); 
        content.getChildren().add(gridPane);
    }
    
    @Override
    protected void updateItem(Article item, boolean empty) { 
        super.updateItem(item, empty); 
        setGraphic(null); 
        setText(null); 
        setContentDisplay(ContentDisplay.LEFT); 
        if (!empty && item != null) { 
            titreLabel.setText(item.getTitre()); 
            nbLikesLabel.setText(item.getPnbLikes()+" Likes"); 
            nbDislikeLabel.setText(item.getNbDislike()+" Dislikes"); 

            String path = "";
            path = "C:\\wamp64\\www\\PI\\web\\Uploads\\article\\photo\\"+item.getImage();
            
            //Image image = new Image(getClass().getResource(path).toExternalForm());
            //System.out.println(image);
            
            Image imageURL= new Image("file:///C:/wamp64/www/PI/web/Uploads/article/photo/" + item.getImage());
            System.out.println(item);
            articleIcon.setImage(imageURL);
            //if(imageURL != null){
            //    articleIcon.setImage(imageURL);
            //    System.out.println("Non null");
            //}else{
            //   System.out.println("null");
            //}
            setText(null);
            setGraphic(content); 
            setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        }
    }
}

    

