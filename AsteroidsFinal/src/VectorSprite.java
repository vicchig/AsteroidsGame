
import java.awt.*;

public class VectorSprite {
    //the variables that will hold the position of the ship
    double xPosition = 450;
    double yPosition = 300;
    double xSpeed;
    double ySpeed;
    double angle;
    int x,y, counter;
    double angleR;
    //boolean that determines if the object should stop or continue moving during a collision
    boolean active;
    
    Polygon shape, rotateShape;
    
    public VectorSprite(){//whenever this method is called in asteroids the variables in it will be initialized
    
        shape = new Polygon();
        shape.addPoint(25,0);
        shape.addPoint(-10,-15);
        shape.addPoint(-10,15);
        
        rotateShape = new Polygon();
        rotateShape.addPoint(25,0);
        rotateShape.addPoint(-10,-15);
        rotateShape.addPoint(-10,15);

        xSpeed  = 0;
        ySpeed = 0;
        angle = 0;
        x = 0;
        y = 0;
        
        counter = 0;
    }
    
    public void paint (Graphics g){
        g.drawPolygon(rotateShape);
    }  
    public void updatePosition(){
        xPosition += xSpeed;
        yPosition += ySpeed;
        //counter is updated each tick (when update position is called)
        counter++;
        //rotates the ship
        for(int i = 0;i<shape.npoints;i++){
           x = (int)Math.round(shape.xpoints[i]*Math.cos(angle)-shape.ypoints[i]*Math.sin(angle));//updating the x coordinates based on angle
           y = (int)Math.round(shape.xpoints[i]*Math.sin(angle)+shape.ypoints[i]*Math.cos(angle));//updating the y coordinates based on angle
           rotateShape.xpoints[i] = x;
           rotateShape.ypoints[i] = y;
        }
        //every time the position of the rotateShape is updated inconsistent results may appear and the invalidate method will reset it to the same time frame
        rotateShape.invalidate();
        rotateShape.translate((int)Math.round(xPosition), (int)Math.round(yPosition));
        boundaryDetection();
        //counter for invulnerability after dying
        
    }
    public void boundaryDetection(){
        if (xPosition<-25){
            xPosition = 1224;
        }
        if(xPosition>1225){
            xPosition = -24;
        }
        if (yPosition>900){
            yPosition = 0-25;
        }
        if (yPosition<-25){
            yPosition = 800+25;
        } 
    } 
}

