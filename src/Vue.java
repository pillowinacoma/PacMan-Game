
//import java.scene.image.Image;
import javafx.scene.image.Image;
import  Model.*;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

import Model.PacMan;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Vue extends Application {
    public static void main (String[] args){   
        //File f = new File("./img/pacman1.png");
        //System.out.println(f.exists());
        Application.launch(Vue.class,args);
    }
    //PacMan pacman=new PacMan();
    
  //28
  //31
    int[][] plateau={
    		{1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,1,0,1,0,0,0,1,1,1,1,1,1,1,1,1,1},
    		{1,0,0,0,0,1,1,0,0,0,0,1,0,0,0,1,0,1,0,0,0,1,0,0,0,0,0,0,0,0,1},
    		{1,0,1,1,0,1,1,0,1,1,0,1,0,0,0,1,0,1,0,0,0,1,0,1,1,0,1,1,1,0,1},
    		{1,0,1,1,0,0,0,0,1,1,0,1,0,0,0,1,0,1,0,0,0,1,0,1,1,0,1,1,1,0,1},
    		{1,0,1,1,0,1,1,1,1,1,0,1,0,0,0,1,0,1,0,0,0,1,0,1,1,0,1,1,1,0,1},
    		{1,0,1,1,0,1,1,1,1,1,0,1,1,1,1,1,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
    		{1,0,1,1,0,0,0,0,0,0,0,0,3,0,0,0,0,0,0,0,0,0,0,0,0,3,0,0,0,0,1},
    		{1,0,1,1,1,1,1,0,1,1,0,1,1,1,1,1,0,1,1,1,1,1,1,1,1,0,1,1,1,0,1},
    		{1,0,1,1,1,1,1,0,1,1,0,1,1,1,1,1,0,1,1,1,1,1,1,1,1,0,1,1,1,0,1},
    		{1,0,1,1,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,1,1,1,0,1},
    		{1,0,1,1,0,1,1,0,1,1,0,1,1,0,1,1,1,1,1,1,1,1,0,1,1,0,1,1,1,0,1},
    		{1,0,1,1,0,1,1,0,1,1,1,1,0,0,1,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,0,0,0,0,1,1,0,0,0,1,1,0,0,1,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1},
    		{1,0,1,1,1,1,1,0,1,1,1,1,0,0,1,0,0,0,0,1,0,1,1,1,1,1,1,1,1,1,1},
    		{1,0,1,1,1,1,1,3,1,1,1,1,0,0,1,1,0,0,1,1,0,1,1,1,1,1,1,1,1,1,1},
    		{1,0,0,0,0,1,1,0,1,1,1,1,0,0,0,1,0,1,0,0,0,1,1,1,1,1,1,1,1,1,1},
    		{1,0,1,1,0,1,1,0,1,1,1,1,0,0,0,1,0,1,0,0,0,1,1,1,1,1,1,1,1,1,1},
    		{1,0,1,1,0,1,1,0,1,1,1,1,0,0,0,1,0,1,0,0,0,1,1,1,1,1,1,1,1,1,1},
    		{1,0,1,1,0,0,0,0,1,1,1,1,0,0,0,1,0,1,0,0,0,1,1,1,1,1,1,1,1,1,1},
    		{1,0,1,1,0,0,0,0,1,1,1,1,0,0,0,1,0,1,0,0,0,1,1,1,1,1,1,1,1,1,1},
    		{1,0,1,1,1,1,1,0,1,1,1,1,0,0,0,1,0,1,0,0,0,1,1,1,1,1,1,1,1,1,1},
    		{1,0,1,1,1,1,1,0,1,1,1,1,0,0,0,1,0,1,0,0,0,1,1,1,1,1,1,1,1,1,1},
    		{1,0,1,1,0,0,0,0,1,1,1,1,0,0,0,1,0,1,0,0,0,1,1,1,1,1,1,1,1,1,1},
    		{1,0,1,1,0,1,1,0,1,1,1,1,0,0,0,1,0,1,0,0,0,1,1,1,1,1,1,1,1,1,1},
    		{1,0,0,0,0,1,1,0,0,0,0,0,0,0,0,1,0,1,0,0,0,1,1,1,1,1,1,1,1,1,1},
    		{1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,1,0,1,0,0,0,1,1,1,1,1,1,1,1,1,1}
    };
    int width = 15,height = 15;
    int rowNb = plateau.length,colNb = plateau[0].length;
    int tailleRow = plateau.length * width;
    int tailleCol = plateau[0].length * height;
    //private class Tile

    
    
    
      @Override
    public void start(Stage primaryStage) {
          int continue_ = 1;
          Scanner sc = new Scanner(System.in);
          int jii = 50000;
          Model.Grille grille=new Model.Grille(rowNb,colNb);
          for(int i = 0 ; i < rowNb ; i++){
              for(int j = 0 ; j < colNb ; j++){
                  switch(plateau[i][j]){
                      case 0:break;
                      case 1:grille.setCaseGrille(i, j, new Model.Mur());break;
                      case 2:grille.setCaseCreature(i, j, new Model.PacMan());break;
                      case 3:grille.setCaseCreature(i, j, new Model.Fantom());break;
                  }
              }
          }
          afficher(width,height,rowNb,colNb,plateau,primaryStage,grille);
          HashMap<Creature,Point> cmap = grille.getCreaturesMap();
          /*do{
              cmap.forEach((key,value)->{grille.deplacerCreature(key,key.getCurrentDirection());});
              System.out.println("continue ?");
              continue_ = sc.nextInt();
              afficher(width,height,rowNb,colNb,plateau,primaryStage,grille);
        }while(continue_ == 1);*/
    }
    public static void afficher(int width,int height,int rowNb, int colNb,int [][] plateau, Stage primaryStage,Grille grille){
        Image pacman1 = new Image("pacman1.png");
        Image ghost = new Image("file:./img/ghost.png");
        ImageView pp = new ImageView(pacman1);
        primaryStage.setTitle("Pac Man");
        GridPane gridpane = new GridPane();
        Group root = new Group();
        Rectangle r = new Rectangle(width,height);
        double sw = r.getStrokeWidth();
        Scene scene = new Scene(root,(width+sw)*rowNb,(height+sw)*colNb, Color.CADETBLUE);
        for(int i=0;i<rowNb;i++){
            for(int j=0;j<colNb;j++){
                r = new Rectangle(width,height);
                if((grille.getCaseGrille(i,j)instanceof Model.Mur)) {
                    r.setFill(Color.PURPLE);
                    gridpane.add(r,i,j);
                }
                else if((grille.getCaseGrille(i,j)instanceof Model.Couloir)) {
                    r.setFill(Color.BLACK);
                    gridpane.add(r,i,j);
                }
                if((grille.getCaseCreature(i,j)instanceof Model.PacMan)) {
                    gridpane.add(pp,i,j);
                }
                else if((grille.getCaseCreature(i,j)instanceof Model.Fantom)) {
                    gridpane.add(new ImageView(ghost),i,j);
                }
                r.setStroke(Color.GREY);
            }
        }

        root.getChildren().add(gridpane);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
}