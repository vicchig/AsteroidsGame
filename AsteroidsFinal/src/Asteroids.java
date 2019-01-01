import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import javax.swing.Timer;
import java.util.ArrayList;
import java.applet.AudioClip;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.File;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JComponent;

public class Asteroids extends JFrame implements KeyListener, ActionListener, MouseListener, MouseMotionListener {
    
    public static SpaceCraft ship;
    //ControllerInput mController = new ControllerInput(); 
    Timer time;
    Image offscreen;//the offscreen image where everything is drawn
    Graphics offg, backg;//the image that is seen by the user
    public static String name;
    public static boolean bckSpcPressed,mousePressed, upKey, rightKey, leftKey, spaceKey, gameOver, changeLevel,lifeAdded;
    public static int nameX, nameY, mx, my, score, mWidth, mHeight, enemyAmount, asteroidAmount, smartAmount, evAmount, level, levelDelay;
    AudioClip bulletSound, movementS, asteroidHitS, shipHitS;
    GameManagement gameM;
    
    public static InvincibilityPU invinciblePU;
    public static SlowPU slowingPU;
    public static ExtraLifePU extraLife;
       
    //asteroids
    ArrayList<Asteroid> asteroidList;
    //bullets
    ArrayList<Bullet> bulletList;
    //debris
    ArrayList<Debris>debrisList;
    //
    ArrayList<EvasionAsteroid>eAsteroids;
    //
    ArrayList<SmartAsteroid> smartAsteroids;
    //
    ArrayList<Enemy>enemies;
    public Asteroids() {
        // TODO start asynchronous download of heavy resources
        StartGame();
        this.setSize(this.mWidth,this.mHeight);
        this.addKeyListener(this);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        //sounds
        /*
        bulletSound = Applet.newAudioClip(Asteroids.class.getResource("laser80.wav"));
        movementS = Applet.newAudioClip(Asteroids.class.getResource("thruster.wav"));
        asteroidHitS = Applet.newAudioClip(Asteroids.class.getResource("explode0.wav"));
        shipHitS = Applet.newAudioClip(Asteroids.class.getResource("explode1.wav"));
        */
    }
    
    public static void main(String args[]) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new Asteroids();
                } catch (Exception e) {
                    System.out.print(e.toString());
                }
            }
        });
    }
    public final void StartGame() {
        try{
            this.pack();
            this.setFocusable(true);
            this.setVisible(true);
            this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            this.setExtendedState(JFrame.MAXIMIZED_BOTH);
            mWidth = 1200;
            mHeight = 900;
            name = "";
            nameX = 565;
            nameY = 400;
            bckSpcPressed = false;
            //mouse
            mx = 0;
            my = 0;
            mousePressed = false;
            //bullets
            bulletList = new ArrayList();
            //power ups
            invinciblePU = new InvincibilityPU();
            slowingPU = new SlowPU();
            extraLife = new ExtraLifePU();
            lifeAdded = false;
            gameM = new GameManagement();
            //asteroids
            asteroidList = new ArrayList();
            //asteroid amounts, get amount you want and subtract 1, then use this number for initialization
            evAmount = 0;
            enemyAmount = 1;
            smartAmount = 0;
            asteroidAmount = 0;
            enemies = new ArrayList();
            //debris
            debrisList = new ArrayList();
            eAsteroids = new ArrayList();
            level = 0;
            changeLevel = false;
            levelDelay = 100;
            //smart asteroid 
            smartAsteroids = new ArrayList();
            gameOver =false;
            ship = new SpaceCraft();//creates a new SpaceCraft object
            //change the 
            time = new Timer(30,this);// creates a new Timer object

            offscreen = createImage (this.getWidth(),this.getHeight());//double buffering
            offg = offscreen.getGraphics(); 
            backg = offscreen.getGraphics();

            //score
            score = 0;

            start();
        }
        catch(NullPointerException e){} 
      }
    
    public void update(Graphics g){
        paint(g) ;
    }
    
    public void paint(Graphics g) {
        offg.setColor(Color.BLACK);
        offg.fillRect(0,0,mWidth,mHeight);
        
        if(gameM.runMenu == true){
            gameM.menu(offg);
        }
        if(gameM.runScore){
            gameM.displayHighscores(offg);
        }
        if(!gameM.runScore && !gameM.runMenu){
            gameM.infoDisplay(offg);
        }
        if(ship.active == true && !ship.invulnerable){
            offg.setColor(Color.GREEN);
            ship.paint(offg);
        }
        else if(ship.active == true && ship.invulnerable){
            offg.setColor(Color.WHITE);
            ship.paint(offg);
        }
        //painting asteroids
        for(int i = 0;i<asteroidList.size();i++){
            //gets the asteroid from the array and paints it
            asteroidList.get(i).paint(offg); 
        }
        //painting bullets
        for(int i = 0;i<bulletList.size();i++){
            //gets the bullet from the array and paints it
            offg.setColor(Color.CYAN);
            bulletList.get(i).paint(offg);  
        }
        //drawing the debris
        for(int i = 0;i<debrisList.size();i++){
            offg.setColor(Color.WHITE);
            debrisList.get(i).paint(offg);
        }
        //drawing enemy asteroids 
        for(int i = 0;i<enemies.size();i++){
            offg.setColor(Color.RED);
            enemies.get(i).paint(offg);
        }
        
        
        //displays the amount of bullets the player has
        /*
        offg.setFont(new Font("Arial", Font.PLAIN,10));
        offg.drawString(""+ship.bulletAmount+"/"+bulletList.size(),100,100);
        //score
        offg.drawString("Score: "+score,2 00,100);
        */
        
        asteroidCount();
        
        //smart asteroid
        for(int i = 0;i<smartAsteroids.size();i++){
            smartAsteroids.get(i).paint(offg);
        }
        
        for(int i = 0;i<eAsteroids.size();i++){
            offg.setColor(Color.BLUE);
            eAsteroids.get(i).paint(offg);
        }
        
        for(int i = 0;i<enemies.size();i++ ){
            for(int j  = 0;j<enemies.get(i).bulletList.size();j++){
                offg.setColor(Color.YELLOW);
                enemies.get(i).bulletList.get(j).paint(offg);
            }
        } 
        //draws power ups
        offg.setColor(Color.GREEN);
        invinciblePU.paint(offg);
        offg.setColor(Color.BLUE);
        slowingPU.paint(offg);
        offg.setColor(Color.MAGENTA);
        extraLife.paint(offg);
        
        if(ship.lives <=0){
            gameM.endGame(offg);
            offg.setColor(Color.WHITE);
            offg.drawString(""+name,560,400);
        }

        g.drawImage(offscreen,0,0,this);//draws the new image with the updated object positions
 
        repaint();
    }
    //checks which key is being pressed and performs the according action, enables two keys to be processed at the same time
    public void keyChecked(){
        if (rightKey){
            ship.rotateRight();
        }
        if (upKey){
            ship.accelerate();  
        }
        else{
            ship.slowDown();
        }
        if (leftKey){
            ship.rotateLeft();
        }
        if(spaceKey && ship.active == true){
            countBullets(); 
            //bulletSound.play();
        }
        //backspace deleting the last letter in the array
        if(bckSpcPressed && name.length()>=1){
            name = name.substring(0,name.length()-1);
        }
    }
    public void keyPressed(KeyEvent e) {
 
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) { 
            rightKey = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP){
            upKey = true;
            //movementS.loop();
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT){
            leftKey = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_SPACE){
            spaceKey = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE){
            bckSpcPressed = true;
        }
        repaint();
    }  
    
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) { 
            rightKey = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP){
            upKey = false;
            //movementS.stop();
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT){
            leftKey = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_SPACE){
            spaceKey = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE){
            bckSpcPressed = false;
        }
    }   
    public void keyTyped(KeyEvent e) {
 
        char c = e.getKeyChar();
        
        if(gameM.getName == true){
            
            if(c != KeyEvent.CHAR_UNDEFINED && c!= KeyEvent.VK_BACK_SPACE
                    && name.length()<20){
                name = name+c;
            }
        }
    } 
    
    public void mouseMoved(MouseEvent e){
        mx = e.getX();
        my = e.getY();
    }
    public void mouseDragged(MouseEvent e){}
    public void mouseClicked(MouseEvent e){}
    public void mousePressed(MouseEvent e){
        mousePressed = true;
    }
    public void mouseReleased(MouseEvent e){
        mousePressed = false;
    }
    public void mouseExited(MouseEvent e){}
    public void mouseEntered(MouseEvent e){}
    
    public void actionPerformed(ActionEvent e){//called everytime an action is performed 

        if(gameM.runMenu == false && gameM.runScore == false){
            if(asteroidList.size() == 0 && eAsteroids.size() == 0 && smartAsteroids.size() == 0 && enemies.size() == 0){
                levelSetUp();
            }
            //mController.update();
            keyChecked();
            respawnShip();
            bulletRegen();
            checkBulletCollision();
            checkAsteroidDestruction();
            ship.updatePosition();
            invinciblePU.updatePosition();
            invinciblePU.managePower(ship);
            slowingPU.updatePosition();
            slowingPU.managePower(smartAsteroids,asteroidList, eAsteroids, ship);
            extraLife.managePower();
            extraLife.updatePosition();
            checkPuCollision();
            
            for(int i = 0;i<enemies.size();i++){
                enemies.get(i).updatePosition(ship);
                enemies.get(i).shootBullet();
            }

            for(int i = 0;i<smartAsteroids.size();i++){
                smartAsteroids.get(i).updatePosition(ship);
            }

            //updating asteroid position
            for(int i = 0;i<asteroidList.size();i++){
                asteroidList.get(i).updatePosition();

            }
            //updating bullet position
            for(int i =0;i<bulletList.size();i++){
                bulletList.get(i).updatePosition();
                //removes bullets after a period of time or if they collided with something
                if (bulletList.get(i).counter > 35 || bulletList.get(i).active == false){
                    bulletList.remove(i);
                }
            }
            //bullet position for enemy
            for(int i = 0;i<enemies.size();i++){
                for(int j = 0;j<enemies.get(i).bulletList.size();j++){
                    enemies.get(i).bulletList.get(j).updatePosition();
                    if (enemies.get(i).bulletList.get(j).counter > 35 || enemies.get(i).bulletList.get(j).active == false){
                        enemies.get(i).bulletList.remove(j);
                    }
                }
            }

            for(int i = 0;i<eAsteroids.size();i++){
                eAsteroids.get(i).updatePosition(bulletList);
            }
            //checks collision between the ship and steroids only if the ship is not invulnerable
            if(!ship.invulnerable){
                checkCollisions();
            } 
            //updating the debris object in a random direction
            for(int  i = 0;i<debrisList.size();i++){
                debrisList.get(i).updatePosition();
                if(debrisList.get(i).counter > 35){
                    debrisList.remove(i);
                }
            }

            if(ship.lives<=0){

                for(int j = 0;j<enemies.size();j++){
                    enemies.remove(j);
                }
                for(int j = 0;j<smartAsteroids.size();j++){
                    smartAsteroids.remove(j);
                }
                for(int j = 0;j<asteroidList.size();j++){
                    asteroidList.remove(j);
                }
                for(int j = 0;j<eAsteroids.size();j++){
                    eAsteroids.remove(j);
                }
                gameOver = true;
            }
        }  
    }
    public void start(){//whenever the file is executed this method is run
        time.start();//the timer keeps track of the ticks
    }
    public void stop(){//called when the game is closed
        time.stop(); // stops the timer when the game is closed
    }
    //returns if vertices of the two objects overlap, determines what collision is defined as
    public boolean collision(VectorSprite object1,VectorSprite object2){
        int x,y;
        //vertices of ship inside the collision object
        for(int i = 0;i<object1.rotateShape.npoints;i++){
            x = object1.rotateShape.xpoints[i];
            y = object1.rotateShape.ypoints[i];
            if(object2.rotateShape.contains(x,y)){
                return (true);
            }  
        }
        
        //put outside of the for loop so that a boolean is always returned
        //otherwise when there is a situation that has no vertices inside the asteroid and nothing would be returned if the else part is in the for loop
        return (false);
    }
    //checks collisions between the ship and the power ups
    public void checkPuCollision(){

        if(collision(invinciblePU, ship) && invinciblePU.visible){
            invinciblePU.activatePU();
            invinciblePU.visible = false;
        }
        else if(collision(slowingPU, ship) && slowingPU.visible){
            slowingPU.visible = false;
            slowingPU.activatePU();
            slowingPU.reduceSpeed(smartAsteroids,asteroidList,eAsteroids, ship);
            slowingPU.xPosition = -1000;
            slowingPU.yPosition = -1000;
            
        }
        if(collision(extraLife, ship) && extraLife.visible){
            extraLife.visible = false;
            extraLife.xPosition = -1000;
            extraLife.yPosition = -1000;
            extraLife.activatePU();
            extraLife.addLife(ship);
            lifeAdded = true;
        }
        else{
            lifeAdded = false;
        }
    }
    //method for checking collision between asteroids and the ship;  
    public void checkCollisions(){
        //for  loop that checks collision with every asteroid object
        for(int i = 0;i<asteroidList.size();i++){
            //ship - asteroid collision
            if(collision(ship,asteroidList.get(i)) == true){
                //method that will change the variable active
                ship.hit();
                //shipHitS.play();
            }
        }
        //smart asteroids 
        for(int i  = 0;i<smartAsteroids.size();i++){
            if(collision(ship,smartAsteroids.get(i)) == true){
                //method that will change the variable active
                ship.hit();
                //shipHitS.play();
            }
        }
        
        for(int i  = 0;i<eAsteroids.size();i++){
            if(collision(ship,eAsteroids.get(i)) == true){
                //method that will change the variable active
                ship.hit();
                //shipHitS.play();
            }
        }  
        //collision between the player and enemy bullets
        for (int i = 0; i < enemies.size(); i++) {
            for (int j = 0; j < enemies.get(i).bulletList.size(); j++) {
                if(collision(enemies.get(i).bulletList.get(j), ship) && ship.active){
                    enemies.get(i).bulletList.remove(j);
                    ship.hit();
                }
            }
        }
    }
    //check bullet collision 
    public void checkBulletCollision(){
        int countRuns = 0;
        //for  loop that runs through the asteroid list
        for(int i = 0;i<asteroidList.size();i++){
            //bullet - asteroid collision
            for(int j = 0;j<bulletList.size();j++){
                if(collision(bulletList.get(j),asteroidList.get(i))==true && asteroidList.get(i).active == true){
      
                   // asteroidHitS.play();
                    bulletList.get(j).active = false;
                    asteroidList.get(i).active = false;
                    score+=asteroidList.get(i).points;
                    slowingPU.spawnPU(asteroidList.get(i).xPosition,asteroidList.get(i).yPosition, 5);
                    invinciblePU.spawnPU(asteroidList.get(i).xPosition,asteroidList.get(i).yPosition, 5);
                    extraLife.spawnPU(asteroidList.get(i).xPosition,asteroidList.get(i).yPosition, 5);
                    //adding debris
                    for(int n = 0;n<5;n++){
                        debrisList.add(new Debris(asteroidList.get(i).xPosition,asteroidList.get(i).yPosition ));
                    }
                }
            }
        }
        for(int i = 0;i<smartAsteroids.size();i++){
            //bullet - asteroid collision
            for(int j = 0;j<bulletList.size();j++){
                if(collision(bulletList.get(j),smartAsteroids.get(i))==true && smartAsteroids.get(i).active == true){
                    
                    //asteroidHitS.play();
                    bulletList.get(j).active = false;
                    smartAsteroids.get(i).active = false;
                    score+=smartAsteroids.get(i).points;
                    for(int n = 0;n<5;n++){
                        debrisList.add(new Debris(smartAsteroids.get(i).xPosition,smartAsteroids.get(i).yPosition ));
                    }
                }
            }
        }
        for(int i = 0;i<eAsteroids.size();i++){
            //bullet - asteroid collision
            for(int j = 0;j<bulletList.size();j++){
                if(collision(bulletList.get(j),eAsteroids.get(i))==true && eAsteroids.get(i).active == true){
                    
                    //asteroidHitS.play();
                    bulletList.get(j).active = false;
                    eAsteroids.get(i).active = false;
                    score+=eAsteroids.get(i).points;
                    for(int n = 0;n<5;n++){
                        debrisList.add(new Debris(eAsteroids.get(i).xPosition,eAsteroids.get(i).yPosition ));
                    }
                }
            }
        }  

        //collision between player bullets and enemies
        for (int i = 0; i < bulletList.size(); i++) {
            for (int j = 0; j < enemies.size(); j++) {
            if(collision(bulletList.get(i), enemies.get(j)) && enemies.get(j).active){
                    score += 15;
                    bulletList.remove(i);
                    enemies.get(j).active = false;
                    enemies.remove(j);
                }
            }
        }
    }
    //respawns the ship after a certain time period if the player has died 
    public void respawnShip(){
        if(ship.active == false && ship.counter>25){
            ship.reset();
        }
        if(ship.counter>100){ //upposedly this converts to 2 seconds 
            ship.invulnerable = false; 
        }   
    }
    //

    //method that shoots bullets and decreases their count, also adds bullets to their list
    public void countBullets(){
        
        if(ship.bulletAmount>0 && ship.active){
            bulletList.add(new Bullet(ship.rotateShape.xpoints[0], ship.rotateShape.ypoints[0], ship.angle));
            ship.bulletAmount--;
        }  
    }
    //method for adding bullets every 10ticks
    public void bulletRegen(){
        if(ship.counter%10 == 0){
            ship.bulletAmount = ship.bulletAmount+200;
        }
    }
    public void checkAsteroidDestruction(){
        //removes inactive asteroids from the list
        for(int i = 0;i<asteroidList.size();i++){
            if(asteroidList.get(i).active == false){
                if(asteroidList.get(i).size>2){
                    asteroidList.add(new Asteroid(asteroidList.get(i).xPosition, asteroidList.get(i).yPosition,asteroidList.get(i).size-1));
                    asteroidList.add(new Asteroid(asteroidList.get(i).xPosition, asteroidList.get(i).yPosition,asteroidList.get(i).size-1));
                }
                asteroidList.remove(i);
            }
        }
        for(int i = 0;i<smartAsteroids.size();i++){
            if(smartAsteroids.get(i).active == false){
                smartAsteroids.remove(i);
            }
        }
        
        for(int i = 0;i<eAsteroids.size();i++){
            if(eAsteroids.get(i).active == false){
                eAsteroids.remove(i);
            }
        }
    }
    public void asteroidCount(){
        int large = 0;
        int medium = 0;
        int small = 0;
        int asteroidNum = 0;
        
        for(int i = 0;i < asteroidList.size();i++){
            if(asteroidList.get(i).size == 3){
                large++;
            }
            else if(asteroidList.get(i).size == 2){
                medium++;
            }
            else if(asteroidList.get(i).size == 1){
               small++;
            }
            asteroidNum = asteroidList.size();
        }
        /*
        offg.setColor(Color.BLACK);
        offg.setFont(new Font("Arial", Font.PLAIN,10));
        offg.drawString("Small: "+small,50,100);
        offg.drawString("Medium: "+medium,50,150);
        offg.drawString("Large: "+large,50,200);
        offg.drawString("Asteroids: "+asteroidNum,50,250);
        */
    }
    public void levelSetUp(){
        //consider updating the amount based on a % function and not every time the method runs
        levelDelay--;
        ship.counter = 0;
        if(levelDelay <=0 ){
            asteroidAmount++;
            evAmount++;
            level++;
            
            if(level%3 == 0){
                smartAmount++;
            }
            if(level%5 == 0){
                enemyAmount++;
            }
            
            for(int i = 0;i<asteroidAmount;i++){
                asteroidList.add(new Asteroid());
            }

            for(int i  = 0; i<smartAmount; i++){
                smartAsteroids.add(new SmartAsteroid());
            }
            //
            for(int i  = 0; i<evAmount; i++){
                eAsteroids.add(new EvasionAsteroid());
            }
            //

            for(int i  = 0; i<enemyAmount; i++){
                enemies.add(new Enemy());
            }
            levelDelay = 100;
        }
    }

    
    // TODO overwrite start(), stop() and destroy() methods
}
