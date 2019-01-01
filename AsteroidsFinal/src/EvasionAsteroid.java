import java.awt.*;
import java.util.ArrayList;


public class EvasionAsteroid extends Asteroid{
    double newDistance, followX, followY, xVector, yVector, hypo, angle, xBullet ,yBullet;
    int points;
    boolean inRange;
    
    Bullet bulletInRange;
    
    public EvasionAsteroid(){
        points  = 20;
        inRange = false;
        newDistance = 201;
        
        xSpeed = Math.cos(randomAngle)*4;
        ySpeed = Math.sin(randomAngle)*4;
    }
    public boolean radiusDetection(ArrayList<Bullet> bulletList){
        
        inRange = false;
        
        for (int i= 0;i<bulletList.size();i++){

            xBullet = bulletList.get(i).xPosition;
            yBullet = bulletList.get(i).yPosition;
            
            angle = bulletList.get(i).angle;
            
            xVector = (this.xPosition - xBullet);
            yVector = (this.yPosition - yBullet);
            
            if(xVector > 450){
                xVector-=450;
            }
            else if(xVector<-450){
                xVector+=450;
            }
            if(yVector > 350){
                yVector-=350;
            }
            else if(yVector < -350){
                yVector+=350;
            }
            hypo = Math.sqrt(xVector*xVector + yVector*yVector);
            
            if (hypo < 200){
                if(hypo < newDistance){
                    bulletInRange = new Bullet(xBullet, yBullet, angle);
                    newDistance = hypo;
                    inRange = true;
                }
            }  
        }
        return inRange;
    }
    
    public void updatePosition(ArrayList<Bullet> bulletList){
        
        inRange = radiusDetection(bulletList);
        newDistance = 201;
        
        if(inRange){
            projectDistance(bulletInRange);
            
            xVector = this.xPosition - bulletInRange.xPosition; 
            yVector = this.yPosition - bulletInRange.yPosition;
            
            hypo = Math.sqrt(xVector*xVector + yVector*yVector);
            
            
            
            xSpeed += xVector/hypo * 2;
            ySpeed += yVector/hypo * 2;
        
            hypo = Math.sqrt(xSpeed*xSpeed + ySpeed*ySpeed);
            
            xSpeed = xSpeed/hypo;
            ySpeed = ySpeed/hypo;

            xPosition += xSpeed*5;
            yPosition += ySpeed*5;
            
            super.updatePosition();
        }
        
        else{
            super.updatePosition(); 
        }
        
        
        
    }
    public void projectDistance(Bullet bullet){
        bullet.xPosition += (xVector/hypo)*bullet.xSpeed;
        bullet.yPosition += (yVector/hypo)*bullet.ySpeed; 
    }
  
    public void paint(Graphics g){
        g.setColor(Color.blue);
        g.drawPolygon(rotateShape);
    }
}
