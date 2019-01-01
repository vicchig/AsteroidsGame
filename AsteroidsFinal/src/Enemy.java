
import java.util.ArrayList;
public class Enemy extends SpaceCraft{
    int points;
    int counter;
    int radius, delay;
    double followX, followY, xVector, yVector, hypo;
    double temp1, temp2;
    double randomNum, randomNum2;
    ArrayList<Bullet> bulletList;
    
    public Enemy(){
        xSpeed = 1;
        ySpeed = 1;
        points = 50;
        //xPosition = Math.random() * 900;
        yPosition = Math.random() * 600;
        xPosition = 850;
        //yPosition = 300;
        radius = 300;
        counter = 0;
        angle = Math.random()*2*Math.PI;
        bulletList = new ArrayList();  
    }
   
    public void updatePosition(SpaceCraft ship){
        //add a timer for the enemy to cahnge directions and increase radius detection based on another timer
        
        counter = 0;
        followX = ship.xPosition;
        followY = ship.yPosition;
        //finds the x and y displacement to the ship's position
        xVector = followX - xPosition;
        yVector = followY - yPosition;
        xSpeed=Math.cos(angle)*2;
        ySpeed=Math.sin(angle)*2;
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
        
        if(hypo < radius){
            temp1 = Math.atan(yVector/xVector);

            xSpeed = xVector/hypo *2;
            ySpeed = yVector/hypo * 2;
            
            this.angle = Math.atan(yVector/xVector);
            
            if(xVector < 0){
                if(yVector < 0){
                    angle = (-Math.PI/2)- (Math.PI/2 - Math.abs(angle));
                }
                else{
                    angle = Math.PI/2 + Math.PI/2 - Math.abs(angle);
                }
            }
        }
        

        super.updatePosition();
    }
    
    //}
    
    public boolean inRange(double angle1, double angle2){
        double temp1 ,temp2;
        temp1 = angle1%(2*Math.PI);
        temp2 = angle2%(2*Math.PI);
        if ((temp1 < temp2 + 0.5) && (temp1 > temp2-0.5)){
            return true;
        }
        return false;
    }
    
    
    public void shootBullet(){

        randomNum = (int)(Math.random()*10);
        randomNum2 = (int)(Math.random()*5)+1;
        
        if(this.active && randomNum == 5 && hypo < radius){
            for(int i = 0;i<randomNum2;i++){
                bulletList.add(new Bullet(this.rotateShape.xpoints[0], this.rotateShape.ypoints[0], this.angle)); 
            }
        }  
    }

}

