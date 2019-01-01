import java.awt.*;
import java.awt.Polygon.*;
import java.util.ArrayList;

public class SlowPU extends PowerUp{
    
    double randomAngle, randomVector;
    
    public SlowPU(){
        ID = 1;
        duration = 100;
    }
    
    public void managePower(ArrayList<SmartAsteroid> smartA, ArrayList<Asteroid> asteroids,ArrayList<EvasionAsteroid>evasionA, SpaceCraft ship){
        if(duration>=0 && this.active){
            minusDuration();
        }
        else if(duration<=0){
            this.active = false;
            increaseSpeed(smartA, asteroids, evasionA, ship);
            duration = 100;
        }
        if(dropDuration >= 0){
            dropDuration--;
        }
        else if(dropDuration <= 0){
            this.visible = false;
        }
    }
    public void increaseSpeed(ArrayList<SmartAsteroid> smartA, ArrayList<Asteroid> asteroids, ArrayList<EvasionAsteroid>evasionA, SpaceCraft ship){
        
            for(int i = 0;i<smartA.size();i++){
                smartA.get(i).xSpeed = smartA.get(i).xSpeed*4.0;
                smartA.get(i).ySpeed = smartA.get(i).ySpeed*4.0;
            }
           
            for(int o = 0;o<asteroids.size();o++){
                asteroids.get(o).xSpeed = asteroids.get(o).xSpeed*4.0;
                asteroids.get(o).ySpeed = asteroids.get(o).ySpeed*4.0;
            }

            for(int j = 0;j<evasionA.size();j++){
                evasionA.get(j).xSpeed = evasionA.get(j).xSpeed*4.0;
                evasionA.get(j).ySpeed = evasionA.get(j).ySpeed*4.0;  
            }
        
    }
    public void reduceSpeed(ArrayList<SmartAsteroid> smartA, ArrayList<Asteroid> asteroids, ArrayList<EvasionAsteroid>evasionA, SpaceCraft ship
                         ){

            for(int i = 0;i<smartA.size();i++){
                smartA.get(i).xSpeed = smartA.get(i).xSpeed/4.0;
                smartA.get(i).ySpeed = smartA.get(i).ySpeed/4.0;
            }
            
            
            for(int o = 0;o<asteroids.size();o++){
                
                
                asteroids.get(o).xSpeed = asteroids.get(o).xSpeed/4.0;
                asteroids.get(o).ySpeed = asteroids.get(o).ySpeed/4.0;
                
            }
            
            for(int j = 0;j<evasionA.size();j++){
                evasionA.get(j).xSpeed = evasionA.get(j).xSpeed/4.0;
                evasionA.get(j).ySpeed = evasionA.get(j).ySpeed/4.0;  
            }
        
        
    }
}
