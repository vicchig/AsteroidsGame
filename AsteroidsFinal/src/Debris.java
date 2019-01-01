
import java.awt.Polygon;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Administrator
 */
public class Debris extends VectorSprite {
    
    //angle of the debris
    double angle, thrust;
    
    public Debris(double xPos, double yPos){
 
        //background shape
        shape = new Polygon();
        shape.addPoint(0,0);
        shape.addPoint(0,4);
        shape.addPoint(4,4);
        shape.addPoint(4,0);
        
        //shape that is actually drawn onto the screen after being rotated
        rotateShape = new Polygon();
        rotateShape.addPoint(0,0);
        rotateShape.addPoint(0,4);
        rotateShape.addPoint(4,4);
        rotateShape.addPoint(4,0);

        
        //x and y coordinates of the shape
        xPosition = xPos;
        yPosition = yPos;
        
        //multiply the method by the number you want to increase the range by since the method returns a random double from 0 to 1
        angle = Math.random()*2*Math.PI;
        thrust = Math.random()*20;
        
        //changes the angle of the debris  according to where the ship is facing
        xSpeed+=Math.cos(angle)*thrust;
        ySpeed+=Math.sin(angle)*thrust;
        
        //defaults active to true so that debris are drawn, if it is false, it willbe removed as soon as it is created
        active = true;
        
        
    }
}
