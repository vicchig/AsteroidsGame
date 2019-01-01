
import java.awt.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Administrator
 */
public class SpaceCraft extends VectorSprite{

    double thrust;
    double angleR = 0.1;
    boolean invulnerable;
    int invulnCounter;
    int bulletAmount = 15;
    int lives = 10;
    //double angle;
    
    public SpaceCraft(){
        //default shape
        shape = new Polygon();
        shape.addPoint(25, 0);
        shape.addPoint(-10, 15);    
        shape.addPoint(-10, -15);
        //shape that is drawn onto the screen
        rotateShape = new Polygon();
        rotateShape.addPoint(25, 0);
        rotateShape.addPoint(-10, 15);
        rotateShape.addPoint(-10, -15);
        //start position
        xPosition = 450;
        yPosition = 300;
        //
        angle = 0.15;
        thrust = 0.5;
        
        active = true;
        
        invulnerable = true;
        
        invulnCounter = 0;
    }
//a more specific extension of the VectorSprite
    //the method that makes the ship accelerate based on the direcction it is facing in (angle)
    public void accelerate(){
        xSpeed+=Math.cos(angle)*thrust;
        ySpeed+=Math.sin(angle)*thrust;
    }
    public void slowDown(){
            xSpeed/=1.05;
            ySpeed/=1.05;
    }
    //methods that perform the rotation, called in the Asteroids class
    public void rotateRight(){
        angle += angleR; //can also say this.angle (which would refer to the variable inside their own class)
    }
    public void rotateLeft(){
        angle -= angleR;
    }
    //method for checking collision so that variables are not changed outside of the class
    public void hit(){
        active =  false;
        counter = 0;
        invulnerable = true;
        lives--;
    }

    public void reset(){
        xPosition = 450;
        yPosition = 300;
        xSpeed = 0;
        ySpeed = 0;
        angle = -Math.PI/2;
        active = true;
        counter = 0;
    }
}
