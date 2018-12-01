
//import java.scene.image.Image;
import javafx.scene.image.Image;
import  Model.*;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

import Model.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
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
    
    
  
    static int[][] plateau={
        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
        {1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1},
        {1, 2, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 2, 1},
        {1, 3, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 3, 1},
        {1, 2, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 2, 1},
        {1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1},
        {1, 2, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 2, 1},
        {1, 2, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 2, 1},
        {1, 2, 2, 2, 2, 2, 2, 1, 1, 2, 2, 2, 2, 1, 1, 2, 2, 2, 2, 1, 1, 2, 2, 2, 2, 2, 2, 1},
        {1, 2, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 2, 1},
        {1, 2, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 2, 1},
        {1, 2, 1, 1, 1, 1, 2, 1, 1, 0, 0, 5, 0, 0, 0, 0, 5, 0, 0, 1, 1, 2, 1, 1, 1, 1, 2, 1},
        {1, 2, 1, 1, 1, 1, 2, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 2, 1, 1, 1, 1, 2, 1},
        {1, 2, 1, 1, 1, 1, 2, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 2, 1, 1, 1, 1, 2, 1},
        {1, 2, 0, 0, 0, 0, 2, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 2, 0, 0, 0, 0, 2, 1},
        {1, 2, 1, 1, 1, 1, 2, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 2, 1, 1, 1, 1, 2, 1},
        {1, 2, 1, 1, 1, 1, 2, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 2, 1, 1, 1, 1, 2, 1},
        {1, 2, 1, 1, 1, 1, 2, 1, 1, 0, 0, 0, 0, 0, 4, 0, 0, 0, 0, 1, 1, 2, 1, 1, 1, 1, 2, 1},
        {1, 2, 1, 1, 1, 1, 2, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 2, 1, 1, 1, 1, 2, 1},
        {1, 2, 1, 1, 1, 1, 2, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 2, 1, 1, 1, 1, 2, 1},
        {1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1},
        {1, 2, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 2, 1},
        {1, 2, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 2, 1},
        {1, 2, 2, 2, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 1, 2, 2, 2, 1},
        {1, 1, 1, 2, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 2, 1, 1, 1},
        {1, 1, 1, 2, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 2, 1, 1, 1},
        {1, 2, 2, 2, 2, 2, 2, 1, 1, 2, 2, 2, 2, 1, 1, 2, 2, 2, 2, 1, 1, 2, 2, 2, 2, 2, 2, 1},
        {1, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3, 1},
        {1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1},
        {1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1},
        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
    };
    int width = 15,height = 15;
    int rowNb = plateau.length,colNb = plateau[0].length;
    int tailleRow = plateau.length * width;
    int tailleCol = plateau[0].length * height;
    
    static void getP(int[][] p,Point point){
        for(int i=0;i<plateau.length;i++){
            for(int j=0;j<plateau[0].length;j++){
                if(p[i][j]==4){
                    System.out.println("ca arrive");
                    point.setI(i);
                    point.setJ(j);
                }
            }
        }
    }
    

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
                      case 2:break;//normalement PacGum
                      case 3:break;//normalement SuperPacGum
                      case 4:grille.setCaseCreature(i, j, new Model.PacMan());break;
                      case 5:grille.setCaseCreature(i, j, new Model.Fantom());break;
                  }
              }
          }
          afficher(width,height,rowNb,colNb,plateau,primaryStage,grille);
          HashMap<Creature,Point> cmap = grille.getCreaturesMap();
          System.out.println(cmap);
          Point po=new Point(1,2);
          getP(plateau,po);
          /*do{
              cmap.forEach((key,value)->{grille.deplacerCreature(key,key.getCurrentDirection());});
              System.out.println("continue ?");
              continue_ = sc.nextInt();
              afficher(width,height,rowNb,colNb,plateau,primaryStage,grille);
        }while(continue_ == 1);*/
      }
    public static void afficher(int width,int height,int rowNb, int colNb,int [][] plateau, Stage primaryStage,Grille grille){
        Image pacman1 = new Image("pacman1.png");
        /*Point pou=null;
        getP(plateau,pou);
        System.out.println(po);*/
        Image ghost = new Image("file:./img/ghost.png");
         HashMap<Creature,Point> map = grille.getCreaturesMap();
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
        
        //PacMan
        
        root.setOnKeyPressed(new EventHandler<javafx.scene.input.KeyEvent>() { // on écoute le clavier
            

            @Override
            public void handle(javafx.scene.input.KeyEvent event) {
                Model.PacMan ptr = null;
                
                switch(event.getCode()){
                    case DOWN: System.out.println("down");
                        map.forEach((c,p)->{
                            if(c instanceof Model.PacMan){
                                //TODO : chercher le pacman a partir de la map ..
                                System.out.println(map.get(c));
                                grille.deplacerCreature(c, Direction.DOWN);
                                System.out.println(map.get(c));
                                System.out.println(map);
                            }
                        });
                    break; // si on clique sur shift, on remet spm en haut à gauche
                    case UP: System.out.println("up");
                        map.forEach((c,p)->{
                        if(c instanceof Model.PacMan){
                            //TODO : chercher le pacman a partir de la map ..
                            System.out.println(map.get(c));
                            grille.deplacerCreature(c, Direction.UP);
                            System.out.println(map.get(c));
                            System.out.println(map);
                        }
                    });break;
                    case RIGHT: System.out.println("right");                    
                        map.forEach((c,p)->{
                            if(c instanceof Model.PacMan){
                                //TODO : chercher le pacman a partir de la map ..
                                System.out.println(map.get(c));
                                grille.deplacerCreature(c, Direction.RIGHT);
                                System.out.println(map.get(c));
                                System.out.println(map);
                            }
                        });
                    break; 
                    case LEFT: System.out.println("left");
                        map.forEach((c,p)->{
                            if(c instanceof Model.PacMan){
                                //TODO : chercher le pacman a partir de la map ..
                                System.out.println(map.get(c));
                                grille.deplacerCreature(c, Direction.LEFT);
                                System.out.println(map.get(c));
                                System.out.println(map);
                            }
                        });
                    break; 
                }
            }
        });
        
        gridpane.requestFocus();
        
    }
    
}