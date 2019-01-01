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
public class Asteroid extends VectorSprite{
    
    double randomAngle, randomVector;
    int size = 4;
    int points;
    public Asteroid(){
        
        shape = new Polygon();
        shape.addPoint(30,3);
        shape.addPoint(5,35);
        shape.addPoint(-25,10);
        shape.addPoint(-17,-15);
        shape.addPoint(20,-35);
        
        rotateShape = new Polygon();
        rotateShape.addPoint(30,3);
        rotateShape.addPoint(5,35);
        rotateShape.addPoint(-25,10);
        rotateShape.addPoint(-17,-15);
        rotateShape.addPoint(20,-35);
        
        randomAngle = Math.random()*2*Math.PI;
        randomVector = Math.random()*400+100;
        
        xPosition = Math.cos(randomAngle)*randomVector+450;
        yPosition = Math.sin(randomAngle)*randomVector+300;
        angleR  = 0.1;
        
        randomAngle = Math.random()*2*Math.PI;
        randomVector = Math.random()+10;//used to be 40
        
        xSpeed  = Math.cos(randomAngle)*randomVector;
        ySpeed  = Math.sin(randomAngle)*randomVector;
        
        active = true;
        
        points = 3;
        //variable for controlling the asteroid size
        
    }
    public Asteroid(double positionX, double positionY, int asteroidSize){
        
        size = asteroidSize;
        
        xPosition = positionX;
        yPosition = positionY;
        
        shape = new Polygon();
        shape.addPoint(10*size,1*size);
        shape.addPoint(2*size,11*size);
        shape.addPoint(-8*size,3*size);
        shape.addPoint(-5*size,-5*size);
        shape.addPoint(6*size,-11*size);
        
        rotateShape = new Polygon();
        rotateShape.addPoint(10*size,1*size);
        rotateShape.addPoint(2*size,11*size);
        rotateShape.addPoint(-8*size,3*size);
        rotateShape.addPoint(-5*size,-5*size);
        rotateShape.addPoint(6*size,-11*size);
        
        randomAngle = Math.random()*2*Math.PI;
        randomVector = Math.random()*400+100;
        
        angleR  = 0.1*size;
        
        randomAngle = Math.random()*2*Math.PI;
        randomVector = Math.random()+15; //used to be 40
        
        xSpeed  = Math.cos(randomAngle)*randomVector;
        ySpeed  = Math.sin(randomAngle)*randomVector;
        
        active = true;
        
        if(size == 2){
            points = 4;
        }
        else if(size == 1){
            points = 5;
        }
        
    }
    public void updatePosition(){
        angle-=angleR;
        super.updatePosition();//calls the method for the parent class
    }
    public void paint (Graphics g){
        g.setColor(Color.WHITE);
        g.drawPolygon(rotateShape);
    }  
    
}
