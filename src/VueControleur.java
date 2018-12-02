
import javafx.scene.image.Image;
import  Model.*;

import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class VueControleur extends Application {
    public static void main (String[] args){
        Application.launch(VueControleur.class,args);
    }




    private Grille grille=new Grille();
    int width = 15,height = 15;
    int tailleRow = grille.getN() * width;
    int tailleCol = grille.getM() * height;


      @Override
    public void start(Stage primaryStage) {
          ImageView[][] grid=new ImageView[grille.getN()][grille.getM()];
          
          Image ghost = new Image("file:./img/ghost.png");
          Image wall = new Image("file:./img/blue.png");
          Image coul = new Image("file:./img/black.png");
          Image pacman1 = new Image("pacman1.png");
          ImageView pp = new ImageView(pacman1);
          
          
          GridPane gridpane = new GridPane();
          
          
          afficherInit(width,height,grille.getN(),grille.getM(),pp,grille,gridpane,grid);
          HashMap<Creature,Point> cmap = grille.getCreaturesMap();

          Observer o = new Observer() {
              @Override
              public void update(Observable observable, Object o) {
                  afficher(width,height,grille.getN(),grille.getM(),grid,grille,gridpane,pacman1,ghost,wall,coul);
              }
          };
          
          grille.addObserver(o);
          grille.start();
          
          StackPane root = new StackPane();
          root.getChildren().add(gridpane);
          
          Scene scene = new Scene(root,(width/*+sw*/)*grille.getM(),(height/*+sw*/)*grille.getN(), Color.CADETBLUE);
          
          primaryStage.setTitle("Pac Man");
          primaryStage.setScene(scene);
          primaryStage.show();

          
          
          root.setOnKeyPressed(new EventHandler<javafx.scene.input.KeyEvent>() { 
              @Override
              public void handle(javafx.scene.input.KeyEvent event) {
                  switch(event.getCode()){
                      case DOWN:
                          grille.getPacman().setCurrentDirection(Direction.DOWN);
                          System.out.println(grille.getPacman().getCurrentDirection() +" : " + grille.getCreatureCoord(grille.getPacman()));
                          break;
                      case UP:
                          grille.getPacman().setCurrentDirection(Direction.UP);
                          System.out.println(grille.getPacman().getCurrentDirection() +" : " + grille.getCreatureCoord(grille.getPacman()));
                          ;break;
                      case RIGHT:
                          grille.getPacman().setCurrentDirection(Direction.RIGHT);
                          System.out.println(grille.getPacman().getCurrentDirection() +" : " + grille.getCreatureCoord(grille.getPacman()));
                          break;
                      case LEFT:
                          grille.getPacman().setCurrentDirection(Direction.LEFT);
                          System.out.println(grille.getPacman().getCurrentDirection() +" : " + grille.getCreatureCoord(grille.getPacman()));
                          break;
                  }
              }
          });
          
          gridpane.requestFocus();
      
        }
    public static void afficherInit(int width,int height,int rowNb, int colNb,ImageView pp ,Grille grille,GridPane gridpane,ImageView[][] grid){
        for(int i=0;i<rowNb;i++){
            for(int j=0;j<colNb;j++){
                ImageView img = new ImageView();
                grid[i][j]=img;
                gridpane.add(img, i, j);
            }
        }
    }

    public static void afficher(int width,int height,int rowNb, int colNb,ImageView[][] grid,Grille grille, GridPane gridpane,Image pp,Image ghost,Image wall,Image coul){
        for(int i=0;i<rowNb;i++){
            for(int j=0;j<colNb;j++){
                if((grille.getCaseGrille(i,j)instanceof Model.Mur)) {
                    grid[i][j].setImage(wall);
                }
                else if((grille.getCaseGrille(i ,j)instanceof Model.Couloir)) {
                    grid[i][j].setImage(coul);
                }
                if((grille.getCaseCreature(i,j)instanceof Model.PacMan)) {
                    grid[i][j].setImage(pp);
                }
                else if((grille.getCaseCreature(i,j)instanceof Model.Fantom)) {
                    grid[i][j].setImage(ghost);
                }
            }
        }
    }

}