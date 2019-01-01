import java.applet.Applet;
import java.awt.*;
import java.applet.AudioClip;
import java.io.*;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Comparator;

public class GameManagement {
    public boolean runMenu, runScore, played, played2, getName, addNewScore, sort;
    private Color grey1;
    private Color grey2;
    private Polygon playButton, quitButton, endQuitButton,scoreButton, backButton1;
    private ArrayList<String>scores, tempScores;
    private int numberOfLines, removeIndex;
    private String[] scoresToDisplay, tempScoresArray;
    private CustomComparator compare;
    private FileManager fileManager= new FileManager();
    
    public GameManagement(){
        scores = new ArrayList();
        runMenu = true;
        played = false;
        grey1 = new Color(168,162,162);
        grey2 = new Color(222,218,218);
        playButton = new Polygon();
        scoreButton = new Polygon();
        quitButton = new Polygon();
        endQuitButton = new Polygon();
        backButton1 = new Polygon();
        played2 = false;
        getName = false;
        numberOfLines = 0;
        tempScores = new ArrayList();
        addNewScore = false;
        scoresToDisplay = new String[5];
        tempScoresArray = new String[5]; 
        compare = new CustomComparator();
        
        playButton.addPoint(Asteroids.mWidth/2-175,300);
        playButton.addPoint(Asteroids.mWidth/2+175,300);
        playButton.addPoint(Asteroids.mWidth/2+200,350);
        playButton.addPoint(Asteroids.mWidth/2-200,350);
        
        scoreButton.addPoint(Asteroids.mWidth/2-175,375);
        scoreButton.addPoint(Asteroids.mWidth/2+175,375);
        scoreButton.addPoint(Asteroids.mWidth/2+200,425);
        scoreButton.addPoint(Asteroids.mWidth/2-200,425);
        
        quitButton.addPoint(Asteroids.mWidth/2-175,450);
        quitButton.addPoint(Asteroids.mWidth/2+175,450);
        quitButton.addPoint(Asteroids.mWidth/2+200,500);
        quitButton.addPoint(Asteroids.mWidth/2-200,500);
        
        endQuitButton.addPoint(Asteroids.mWidth/2-175,450);
        endQuitButton.addPoint(Asteroids.mWidth/2+175,450);
        endQuitButton.addPoint(Asteroids.mWidth/2+200,500);
        endQuitButton.addPoint(Asteroids.mWidth/2-200,500);
        
        backButton1.addPoint(Asteroids.mWidth/2-175,600);
        backButton1.addPoint(Asteroids.mWidth/2+175,600);
        backButton1.addPoint(Asteroids.mWidth/2+200,650);
        backButton1.addPoint(Asteroids.mWidth/2-200,650);
        
    }
    
    public void endGame(Graphics g){
        
        getName = true;
        Asteroids.levelDelay = 100;
        
        g.setColor(Color.BLACK);
        g.fillRect(0,0, Asteroids.mWidth, Asteroids.mHeight);
        g.setColor(Color.YELLOW);
        g.setFont(new Font("Arial", Font.PLAIN,30));
        g.drawString("GAME OVER",Asteroids.mWidth/2-95,200);
        g.drawString("SCORE: "+Asteroids.score,Asteroids.mWidth/2-80,300);
        g.setColor(grey1);
        g.fillPolygon(endQuitButton);
        g.setColor(Color.BLACK);
        g.drawString("Quit",Asteroids.mWidth/2-25,485);
        g.setColor(Color.WHITE);
        g.drawString("ENTER NAME:",350,400);
        resetVars();
        if(!mouseCollision(Asteroids.mx, Asteroids.my, endQuitButton)){
            played = false;
        }
        
        if(mouseCollision(Asteroids.mx, Asteroids.my, endQuitButton)){
            changeColor(endQuitButton, g,"Quit",Asteroids.mWidth/2-25,485);
            if(Asteroids.mousePressed){
                if(Asteroids.name.trim().equals("")){
                    Asteroids.name = "NoName";
                }
                recordScore(true);
                runMenu = true;
                Asteroids.ship.lives = 10;
                Asteroids.score = 0;
                getName = false;
                Asteroids.mousePressed = false;
            }
            if(mouseCollision(Asteroids.mx, Asteroids.my, endQuitButton) && !played){
                played = true;
            }
        }
    }
    
    public void menu(Graphics g){
        Asteroids.name = "";
        g.setColor(Color.BLACK);
        g.fillRect(0,0,Asteroids.mWidth, Asteroids.mHeight);
        g.setColor(grey1);
        g.setFont(new Font("Arial", Font.PLAIN,60));
        g.drawString("ASTEROIDS",Asteroids.mWidth/2-170,100);
        g.fillPolygon(playButton);
        g.fillPolygon(scoreButton);
        g.fillPolygon(quitButton);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.PLAIN,30));
        g.drawString("Play",Asteroids.mWidth/2-25,335);
        g.drawString("Highscores",Asteroids.mWidth/2-65,410);
        g.drawString("Quit",Asteroids.mWidth/2-25,485);
        
        if(!mouseCollision(Asteroids.mx, Asteroids.my, playButton) && !mouseCollision(Asteroids.mx, Asteroids.my, scoreButton) && 
                !mouseCollision(Asteroids.mx, Asteroids.my, quitButton)){
            played = false;
        }
        
        if(mouseCollision(Asteroids.mx, Asteroids.my, playButton)){
            changeColor(playButton, g,"Play",Asteroids.mWidth/2-25,335);
            if(Asteroids.mousePressed){
                runMenu = false;
                Asteroids.ship.reset();
                Asteroids.mousePressed = false;
            }
            if(mouseCollision(Asteroids.mx, Asteroids.my, playButton) && !played){
                played = true;
            }
        } 
        else if(mouseCollision(Asteroids.mx, Asteroids.my, scoreButton)){
            changeColor(scoreButton, g,"Highscores",Asteroids.mWidth/2-65,410);
            
            if(Asteroids.mousePressed){
                runMenu = false;
                runScore = true;
            }
            
            if(mouseCollision(Asteroids.mx, Asteroids.my, scoreButton) && !played){
                played = true;
            }
        } 
        else if(mouseCollision(Asteroids.mx, Asteroids.my, quitButton)){
            changeColor(quitButton, g,"Quit",Asteroids.mWidth/2-25,485);
            if(Asteroids.mousePressed){
                System.exit(0);
            }
            if(mouseCollision(Asteroids.mx, Asteroids.my, quitButton) && !played){
                played = true;
            }
        } 
    }
    
    public boolean mouseCollision(int x, int y, Polygon shape){
        Rectangle mouse = new Rectangle(x, y, 1, 1);
        
        if(shape.contains(mouse)){
            return true;
        }
        return false;
    }
    
    public void changeColor(Polygon shape, Graphics gr, String text, int x, int y){
        gr.setColor(grey2);
        gr.fillPolygon(shape);
        gr.setColor(Color.BLACK);
        gr.drawString(text, x, y);
    }
    
    public void displayHighscores(Graphics g){
        int y = 300;
        int place = 1;

        g.setColor(Color.WHITE);
        g.fillRect(0,0,1200,1200);
        g.setColor(grey1);
        g.fillPolygon(backButton1);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.PLAIN,30));
        g.drawString("Back",Asteroids.mWidth/2-20,635);
        g.setFont(new Font("Arial", Font.PLAIN,60));
        g.drawString("HIGHSCORES",Asteroids.mWidth/2-200,100);
        g.setFont(new Font("Arial", Font.PLAIN,45));
        g.setColor(Color.BLACK);
        
        if (scores.isEmpty()) {
            sortScores();
        }
            
        for(int i = 0;i<scores.size();i++){
            y+=50;
            g.drawString(place+"."+" "+scores.get(i),300,y);
            place++;
        }
        g.setFont(new Font("Arial", Font.PLAIN,30));
        if(!mouseCollision(Asteroids.mx, Asteroids.my, backButton1)){
            played = false;
        }
        
        if(mouseCollision(Asteroids.mx, Asteroids.my, backButton1)){
            changeColor(backButton1, g,"Back",Asteroids.mWidth/2-20,635);
            if(Asteroids.mousePressed){
                runMenu = true;
                runScore = false;
            }
            if(mouseCollision(Asteroids.mx, Asteroids.my, backButton1) && !played){
                played = true;
            }
        }  
    }
    
    public void resetVars(){
        Asteroids.evAmount = 0;
        Asteroids.enemyAmount = 0;
        Asteroids.smartAmount = 0;
        Asteroids.asteroidAmount = 2;
        Asteroids.invinciblePU.active = false;
        Asteroids.slowingPU.active = false;
        Asteroids.extraLife.active = false;
        Asteroids.invinciblePU.visible = false;
        Asteroids.slowingPU.visible = false;
        Asteroids.extraLife.visible = false;
        Asteroids.invinciblePU.xPosition = -1000;
        Asteroids.slowingPU.xPosition = -1000;
        Asteroids.extraLife.xPosition = -1000;
        Asteroids.invinciblePU.yPosition = -1000;
        Asteroids.slowingPU.yPosition = -1000;
        Asteroids.extraLife.yPosition = -1000;
        Asteroids.level = 0;
        for (int i = 0; i < scores.size(); i++) {
            scores.remove(i);
        }
    }
    
    public void recordScore(boolean append){
        fileManager.clearFile("Scores.txt");
        for (int i = 0; i < scores.size(); i++) {
            scores.remove(i);
        }
        if (append) {
            readScore();
        }
        append = false;
        determineNewScore(Asteroids.score);
            try{
                File scoreFile = new File("Scores.txt");

                FileWriter writer = new FileWriter(scoreFile, append);
                PrintWriter printLine = new PrintWriter(writer);
                for (int i = 0; i < scores.size(); i++) {
                    printLine.println(scores.get(i));
                }

                printLine.close();
                writer.close();
            }
            catch(IOException e){}
    }
    
    public void determineNewScore(int currentScore){
        if (scores.size() == 5) {
            scores.add(Asteroids.name+":"+Asteroids.score);
            Collections.sort(scores, compare);
            scores.remove(5);
        }
        else{
            scores.add(Asteroids.name+":"+Asteroids.score);
            Collections.sort(scores, compare);
        }
    }
    
    public void readScore(){
        try{
            FileReader fr = new FileReader("Scores.txt");
            BufferedReader textReader = new BufferedReader(fr);
            numberOfLines = fileManager.countLines("Scores.txt");
            for(int i = 0;i<numberOfLines;i++){
                scores.add(textReader.readLine());
            }
            textReader.close();
        }
        catch(IOException e){
            System.out.printf(e.getMessage());

        }
    }
    
    public void sortScores(){
            readScore();
            Collections.sort(scores,compare);
    }
    
    public void infoDisplay(Graphics g){
        g.setColor(Color.CYAN);
        g.setFont(new Font("Arial", Font.PLAIN,12));
        g.drawString("LEVEL:"+Asteroids.level,100,100);
        g.drawString("LIVES:"+Asteroids.ship.lives,100,120);
        g.drawString("SCORE:"+Asteroids.score,100,140);
        g.drawString("SLOW TIMER: ",100,160);
        g.drawString("INVINCIBILITY TIMER: ",100,180);
        g.drawString("LEVEL DELAY: "+Asteroids.levelDelay/20,100,200);
        if(Asteroids.slowingPU.active){
            g.drawString("SLOW TIMER "+(int)Asteroids.slowingPU.duration/20,100,160);
        }
        else if(Asteroids.invinciblePU.active){
            g.drawString("INVINCIBILITY TIMER "+(int)Asteroids.invinciblePU.duration/20,100,180);
        }
    }
}
