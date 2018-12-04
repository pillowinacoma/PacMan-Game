
import javafx.scene.image.Image;
import  Model.*;
import java.awt.Label;

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
import javafx.scene.text.Text;
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
          Image PacGum = new Image("file:./img/pacgum.png");
          Image SuperPacGum = new Image("file:./img/SuperPacGum.png");
          Image wall = new Image("file:./img/blue.png");
          Image coul = new Image("file:./img/black.png");
          Image pacman_right = new Image("pacman1.png");
          Image pacman_left = new Image("file:./img/pacman_left.png");
          Image pacman_up = new Image("file:./img/pacman_up.png");
          Image pacman_down = new Image("file:./img/pacman_down.png");
          Image g_right = new Image("file:./img/ghostRight.png");
          Image g_left = new Image("file:./img/ghostLeft.png");
          Image g_up = new Image("file:./img/ghostUp.png");
          Image g_down = new Image("file:./img/ghostDown.png");
          ImageView pr = new ImageView(pacman_right);
          
          
          GridPane gridpane = new GridPane();
          

          afficherInit(width,height,grille.getN(),grille.getM(),pr,grille,gridpane,grid);
          HashMap<Creature,Point> cmap = grille.getCreaturesMap();

          Observer o = new Observer() {
              @Override
              public void update(Observable observable, Object o) {
                  afficher(width,height,grille.getN(),grille.getM(),grid,grille,gridpane,pacman_right,pacman_down,pacman_left,pacman_up,g_up,g_down,g_left,g_right,wall,coul);
                  //System.out.println(grille);
              }
          };
          
          grille.addObserver(o);
          grille.start();
          
          StackPane root = new StackPane();
          root.getChildren().add(gridpane);     
          
         
          String text = "chaoui dz viva lalgerie";
          Text Tex = new Text();
          Tex.setText(text);
          Tex.setX(400);
          Tex.setY(400);
          root.getChildren().add(Tex);
          
          Scene scene = new Scene(root,(width)*grille.getM(),(height)*grille.getN(), Color.WHEAT);
          
          primaryStage.setTitle("Pac Man");
          primaryStage.setScene(scene);
          primaryStage.show();

          
          
          root.setOnKeyPressed(new EventHandler<javafx.scene.input.KeyEvent>() { 
              @Override
              public void handle(javafx.scene.input.KeyEvent event) {
                  if(grille.getPacman() != null) {
                      switch (event.getCode()) {
                          case DOWN:
                              grille.getPacman().setCurrentDirection(Direction.DOWN);
                              //System.out.println(grille.getPacman().getCurrentDirection() +" : " + grille.getCreatureCoord(grille.getPacman()));
                              break;
                          case UP:
                              grille.getPacman().setCurrentDirection(Direction.UP);
                              //System.out.println(grille.getPacman().getCurrentDirection() +" : " + grille.getCreatureCoord(grille.getPacman()));
                              ;
                              break;
                          case RIGHT:
                              grille.getPacman().setCurrentDirection(Direction.RIGHT);
                              //System.out.println(grille.getPacman().getCurrentDirection() +" : " + grille.getCreatureCoord(grille.getPacman()));
                              break;
                          case LEFT:
                              grille.getPacman().setCurrentDirection(Direction.LEFT);
                              //System.out.println(grille.getPacman().getCurrentDirection() +" : " + grille.getCreatureCoord(grille.getPacman()));
                              break;
                      }
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

    public static void afficher(int width,int height,int rowNb, int colNb,ImageView[][] grid,Grille grille, GridPane gridpane,Image pr,Image pd,Image pl,Image pu,Image g_up,Image g_down,Image g_left,Image g_right,Image wall,Image coul){
        for(int i=0;i<rowNb;i++){
            for(int j=0;j<colNb;j++){
                if((grille.getCaseGrille(i,j)instanceof Model.Mur)) {
                    grid[i][j].setImage(wall);
                }
                else if((grille.getCaseGrille(i ,j)instanceof Model.Couloir)) {
                    grid[i][j].setImage(coul);
                }
                if((grille.getCaseCreature(i,j)instanceof PacMan)) {
                   Creature chaoui = (grille.getCaseCreature(i,j));
                   Direction d = chaoui.getCurrentDirection();
                   switch(d){
                       case UP:grid[i][j].setImage(pu);break;
                       case DOWN:grid[i][j].setImage(pd);break;
                       case RIGHT:grid[i][j].setImage(pr);break;
                       case LEFT:grid[i][j].setImage(pl);break;
                   }
                }
                else if((grille.getCaseCreature(i,j)instanceof Model.Fantom)) {
                    Creature chaoui = (grille.getCaseCreature(i,j));
                    Direction d = chaoui.getCurrentDirection();
                    switch(d){
                        case UP:grid[i][j].setImage(g_up);break;
                        case DOWN:grid[i][j].setImage(g_down);break;
                        case RIGHT:grid[i][j].setImage(g_right);break;
                        case LEFT:grid[i][j].setImage(g_left);break;
                    }
                }
                else if(grille.getCaseGrille(i,j)instanceof Model.Couloir){
                        if(((Couloir)grille.getCaseGrille(i,j)).isPacGum()){
                        grid[i][j].setImage(PacGum);
                        }
                        else if(((Couloir)grille.getCaseGrille(i,j)).isSuperPacGum()){
                        grid[i][j].setImage(SuperPacGum);
                        }
                }
            }
        }
    }

}