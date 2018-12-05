
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
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
import javafx.scene.text.*;
import javafx.stage.Stage;

public class VueControleur extends Application {
    public static void main (String[] args){
        Application.launch(VueControleur.class,args);
    }




    private Grille grille=new Grille();
    int width = 15,height = 15;
    int tailleRow = grille.getN() * width;
    int tailleCol = grille.getM() * height;
    int supercounter = 0;

    Image ghostUp1 = new Image("file:./img/ghostUp.png");
    Image ghostDown1 = new Image("file:./img/ghostDown.png");
    Image ghostLeft1 = new Image("file:./img/ghostLeft.png");
    Image ghostRight1 = new Image("file:./img/ghostRight.png");
    Image ghostUp2 = new Image("file:./img/ghostUp1.png");
    Image ghostDown2 = new Image("file:./img/ghostDown1.png");
    Image ghostLeft2 = new Image("file:./img/ghostLeft1.png");
    Image ghostRight2 = new Image("file:./img/ghostRight1.png");
    Image ghostUp1s = new Image("file:./img/ghostUps.png");
    Image ghostDown1s = new Image("file:./img/ghostDowns.png");
    Image ghostLeft1s = new Image("file:./img/ghostLefts.png");
    Image ghostRight1s = new Image("file:./img/ghostRights.png");
    Image ghostUp2s = new Image("file:./img/ghostUp1s.png");
    Image ghostDown2s = new Image("file:./img/ghostDown1s.png");
    Image ghostLeft2s = new Image("file:./img/ghostLeft1s.png");
    Image ghostRight2s = new Image("file:./img/ghostRight1s.png");
    Image PacGum = new Image("file:./img/PacGum.png");
    Image SuperPacGum = new Image("file:./img/superpacgum.png");
    Image wall = new Image("file:./img/blue.png");
    Image coul = new Image("file:./img/black.png");
    Image pacman_right = new Image("pacman1.png");
    Image pacman_left = new Image("file:./img/pacman_left.png");
    Image pacman_up = new Image("file:./img/pacman_up.png");
    Image pacman_down = new Image("file:./img/pacman_down.png");
    ImageView pr = new ImageView(pacman_right);
    int score = 0;
    Text text = new Text("Score :" + score);
    String s = "";
      @Override
    public void start(Stage primaryStage) {
          ImageView[][] grid=new ImageView[grille.getN()][grille.getM()];


          
          
          GridPane gridpane = new GridPane();
          text.setTranslateX(10);
          text.setTranslateY(170);
          text.setFill(Color.WHITE);
          
          
          afficherInit(width,height,grille.getN(),grille.getM(),pr,grille,gridpane,grid);
          HashMap<Creature,Point> cmap = grille.getCreaturesMap();
          StackPane root = new StackPane();
          root.getChildren().add(gridpane);



          root.getChildren().add(text);

          Scene scene = new Scene(root,(width)*grille.getM(),(height)*grille.getN(), Color.BLACK);
          Observer o = new Observer() {
              @Override
              public void update(Observable observable, Object o) {
                  score = grille.getPacman().getScore();
                  s = "Score :" + score;
                  text.setText(s);
                  afficher(width,height,grille.getN(),grille.getM(),grid,grille,gridpane,pacman_right,pacman_down,pacman_left,pacman_up,ghostUp1,ghostDown1,ghostLeft1,ghostRight1,ghostUp2,ghostDown2,ghostLeft2,ghostRight2,wall,coul,PacGum,SuperPacGum);
                  if(score >= 378){
                      Text txt=new Text();
                      txt.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 50));
                      txt.setTextAlignment(TextAlignment.CENTER);
                      txt.setFill(Color.WHITE);
                      txt.setText("YOU WON !!\n ");
                      txt.setTranslateX(30);
                      txt.setTranslateY(100);
                      VBox v = new VBox();
                      v.getChildren().addAll(txt);
                      scene.setRoot(v);
                  }
                  //System.out.println(grille);
              }
          };
          
          grille.addObserver(o);
          grille.start();
          

          
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
                      case SPACE:
                          Fantom [] f = grille.getFantoms();
                          grille.deplacerCreature(f[0],f[0].getCurrentDirection());
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

    public void afficher(int width,int height,int rowNb, int colNb,ImageView[][] grid,Grille grille, GridPane gridpane,Image pr,Image pd,Image pl,Image pu,Image g_up,Image g_down,Image g_left,Image g_right,Image g_up1,Image g_down1,Image g_left1,Image g_right1,Image wall,Image coul,Image PacGum, Image SuperPacGum){
        for(int i=0;i<rowNb;i++){
            for(int j=0;j<colNb;j++){
                if((grille.getCaseGrille(i,j)instanceof Model.Mur)) {
                    grid[i][j].setImage(wall);
                }
                else if((grille.getCaseGrille(i ,j)instanceof Model.Couloir)) {
                    grid[i][j].setImage(coul);
                }
                if((grille.getCaseCreature(i,j)instanceof Model.PacMan)) {
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
                        case UP:
                            if(chaoui.getMode() == PacMode.REGULAR) {
                                if ((i + j) % 2 == 0)
                                    grid[i][j].setImage(g_up);
                                else
                                    grid[i][j].setImage(g_up1);
                            }else if (chaoui.getMode() == PacMode.SUPER){
                                if ((i + j) % 2 == 0)
                                grid[i][j].setImage(ghostUp1s);
                            else
                                grid[i][j].setImage(ghostUp2s);
                            }
                            break;
                        case DOWN:
                            if(chaoui.getMode() == PacMode.REGULAR) {
                                if ((i + j) % 2 == 0)
                                    grid[i][j].setImage(ghostDown1);
                                else
                                    grid[i][j].setImage(ghostDown2);
                                }else if (chaoui.getMode() == PacMode.SUPER){
                                if ((i + j) % 2 == 0)
                                    grid[i][j].setImage(ghostDown1s);
                                else
                                    grid[i][j].setImage(ghostDown2s);
                                }
                        ;break;
                        case RIGHT:
                            if(chaoui.getMode() == PacMode.REGULAR) {
                                if ((i + j) % 2 == 0)
                                    grid[i][j].setImage(ghostRight1);
                                else
                                    grid[i][j].setImage(ghostRight2);
                            }else if (chaoui.getMode() == PacMode.SUPER){
                                if ((i + j) % 2 == 0)
                                    grid[i][j].setImage(ghostRight1s);
                                else
                                    grid[i][j].setImage(ghostRight2s);
                            }
                        break;
                        case LEFT:
                            if(chaoui.getMode() == PacMode.REGULAR) {
                                if ((i + j) % 2 == 0)
                                    grid[i][j].setImage(ghostLeft1);
                                else
                                    grid[i][j].setImage(ghostLeft2);
                            }else if (chaoui.getMode() == PacMode.SUPER){
                                if ((i + j) % 2 == 0)
                                    grid[i][j].setImage(ghostLeft1s);
                                else
                                    grid[i][j].setImage(ghostLeft2s);
                            }
                        break;
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