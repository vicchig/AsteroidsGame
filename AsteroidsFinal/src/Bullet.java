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
public class Bullet extends VectorSprite{
    public Bullet(double xPos, double yPos, double ang){
        
        int thrust = 10;
 
        //background shape
        shape = new Polygon();
        shape.addPoint(0,0);
        shape.addPoint(0,1);
        shape.addPoint(1,0);
        shape.addPoint(1,1);

        //shape that is actually drawn onto the screen after being rotated
        rotateShape = new Polygon();
        rotateShape.addPoint(0,0);
        rotateShape.addPoint(0,1);
        rotateShape.addPoint(1,0);
        rotateShape.addPoint(1,1);
        
        xPosition = xPos;
        yPosition = yPos;
        
        angle = ang;
        
        //changes the angle of the bullet according to where the ship is facing
        xSpeed+=Math.cos(angle)*thrust;
        ySpeed+=Math.sin(angle)*thrust;
        
        //defaults active to true so that bullets are drawn, if it is false, it willbe removed as soon as it is created
        active = true;
    }
}
