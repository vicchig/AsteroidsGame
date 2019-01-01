import java.awt.*;
import java.awt.Polygon.*;


public class PowerUp extends VectorSprite{
    
    double duration;
    int dropDuration;
    boolean visible;
    int ID;
    double random;
    
    public PowerUp(){

        shape = new Polygon();
        shape.addPoint(0,0);
        shape.addPoint(0,10);
        shape.addPoint(10,10);
        shape.addPoint(10,0);
        
        rotateShape = new Polygon();
        rotateShape.addPoint(0,0);
        rotateShape.addPoint(0,10);
        rotateShape.addPoint(10,10);
        rotateShape.addPoint(10,0);
     
        active = false;
        visible = false;
        
        
        dropDuration = 160;
        /* despite active being set to false, the power up spawns at the 
        same position as the ship and it does not disappear when the ship
        picks it up. This way the ship is always invincible unless it moves from its
        original position. The power up also is not drawn. Temporarily fixed it by
        moving the position of the power up outside of the boundaries of the game
        */
        
        xPosition = -1000;
        yPosition = -1000;
    }
    
    public void minusDuration(){
        if(duration >= 0){
            duration -- ;
        }
    }
    
    public void activatePU(){ 
        active = true;
    }
    
    //determines when to a power up has "dropped"
    public void spawnPU(double x, double y, int chance){
        
        double spawnChance = chance;
        
        if(!visible){
            random = Math.random()*100;
            
            if(random <= spawnChance){
                this.visible = true;
                xPosition = x;
                yPosition = y;
                dropDuration = 160;
                if(xPosition >= Asteroids.mWidth || xPosition<=0 || yPosition >= Asteroids.mHeight || yPosition <=0){
                    xPosition = Asteroids.mWidth/2;
                    yPosition = Asteroids.mHeight/2;
                }
               
            }    
        }
    }

    public void paint(Graphics g) {
        if(this.visible){
            //g.drawPolygon(rotateShape);
            g.fillPolygon(rotateShape);
        }
    }
}
