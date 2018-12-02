
//import java.scene.image.Image;
import javafx.scene.image.Image;
import  Model.*;

import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
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
          int continue_ = 1;
          Scanner sc = new Scanner(System.in);
          int jii = 50000;
          Group root = new Group();
          Image ghost = new Image("file:./img/ghost.png");
          Image pacman1 = new Image("pacman1.png");
          ImageView pp = new ImageView(pacman1);
          GridPane gridpane = new GridPane();
          Rectangle r = new Rectangle(width,height);
          double sw = r.getStrokeWidth();
          Scene scene = new Scene(root,(width+sw)*grille.getM(),(height+sw)*grille.getN(), Color.CADETBLUE);

          afficherInit(width,height,grille.getN(),grille.getM(),primaryStage,grille,root,pp,gridpane,ghost);
          HashMap<Creature,Point> cmap = grille.getCreaturesMap();
          System.out.println(cmap);
          //Point po=new Point(1,2);
          /*do{
              cmap.forEach((key,value)->{grille.deplacerCreature(key,key.getCurrentDirection());});
              System.out.println("continue ?");
              continue_ = sc.nextInt();
              afficher(width,height,rowNb,colNb,plateau,primaryStage,grille);
        }while(continue_ == 1);*/

          Observer o = new Observer() {
              @Override
              public void update(Observable observable, Object o) {
                  afficher(width,height,grille.getN(),grille.getM(),primaryStage,grille,root,pp,gridpane,ghost);
              }
          };
          grille.addObserver(o);
          grille.start();
          root.getChildren().add(gridpane);



          primaryStage.setScene(scene);
          primaryStage.show();

          root.setOnKeyPressed(new EventHandler<javafx.scene.input.KeyEvent>() { // on écoute le clavier


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
    public static void afficherInit(int width,int height,int rowNb, int colNb, Stage primaryStage,Grille grille, Group root,ImageView pp, GridPane gridpane,Image ghost){
        /*Point pou=null;
        getP(plateau,pou);
        System.out.println(po);*/
        primaryStage.setTitle("Pac Man");
        Rectangle r = new Rectangle(width,height);
        for(int i=0;i<rowNb;i++){
            for(int j=0;j<colNb;j++){
                r = new Rectangle(width,height);
                if((grille.getCaseGrille(i,j)instanceof Model.Mur)) {
                    r.setFill(Color.PURPLE);
                    gridpane.add(r,j,i);
                }
                else if((grille.getCaseGrille(i,j)instanceof Model.Couloir)) {
                    r.setFill(Color.BLACK);
                    gridpane.add(r,j,i);
                }
                if((grille.getCaseCreature(i,j)instanceof Model.PacMan)) {
                    gridpane.add(pp,j,i);
                }
                else if((grille.getCaseCreature(i,j)instanceof Model.Fantom)) {
                    gridpane.add(new ImageView(ghost),j,i);
                }
                r.setStroke(Color.GREY);
            }
        }


        //PacMan

    }

    public static void afficher(int width,int height,int rowNb, int colNb, Stage primaryStage,Grille grille, Group root,ImageView pp, GridPane gridpane,Image ghost){
        /*Point pou=null;
        getP(plateau,pou);
        System.out.println(po);*/
        primaryStage.setTitle("Pac Man");
        Rectangle r = new Rectangle(width,height);
        for(int i=0;i<rowNb;i++){
            for(int j=0;j<colNb;j++){
                r = new Rectangle(width,height);
                if((grille.getCaseGrille(i,j)instanceof Model.Mur)) {
                    r.setFill(Color.PURPLE);
                    gridpane.add(r,j,i);
                }
                else if((grille.getCaseGrille(i,j)instanceof Model.Couloir)) {
                    r.setFill(Color.BLACK);
                    gridpane.add(r,j,i);
                }
                if((grille.getCaseCreature(i,j)instanceof Model.PacMan)) {
                    gridpane.add(pp,j,i);
                }
                else if((grille.getCaseCreature(i,j)instanceof Model.Fantom)) {
                    gridpane.add(new ImageView(ghost),j,i);
                }
                r.setStroke(Color.GREY);
            }
        }


        //PacMan

    }

}