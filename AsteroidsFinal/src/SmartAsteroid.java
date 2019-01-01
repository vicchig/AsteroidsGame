import java.awt.*;

public class SmartAsteroid extends Asteroid {
    
    double followX, followY, xVector, yVector, hypo, angle;
    int points;
    
    public SmartAsteroid(){
        points  = 10;
        
    }
    
    public void updatePosition(SpaceCraft ship){
        
        //position of the ship that the asteroid shoul move to
        //constantly updated
        followX = ship.xPosition;
        followY = ship.yPosition;
        
        //finds the x and y displacement to the ship's position
        xVector = followX - xPosition;
        yVector = followY - yPosition;
        
        //if the ship crosses the left border, the asteroid will continue following the ship instead of switching directions and taking the longer path
        if(xVector > 450){
            followX -= 900;
            xVector = followX - xPosition;
        }
        else if(xVector < -450){
            followX += 900;
            xVector = followX - xPosition;
        }
        //bottom border
        if(yVector > 300){
            followX-=600;
            yVector = followY - yPosition;
        }
        else if(yVector < -300){
            followX += 600;
            yVector = followY - yPosition;
        }
        
        //hypotenus (resultant vector for displacement)
        hypo  = Math.sqrt(xVector*xVector + yVector*yVector);
        
        //finding the change in position necessary to follow the ship by using the angle of movement and the distance
        xSpeed = xVector/hypo * 10;
        ySpeed = yVector/hypo * 10;
        
        //updates position of ship based on speed 
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
    }
    public void paint(Graphics g){
        g.setColor(Color.YELLOW);
        g.drawPolygon(rotateShape);
    }

}
