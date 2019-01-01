import java.awt.*;
import java.awt.Polygon.*;

public class InvincibilityPU extends PowerUp{

    public InvincibilityPU(){
        ID = 0;
        duration = 120;
    }
    
    public void managePower(SpaceCraft ship){
        
        if(this.active && duration > 0){
            ship.invulnerable = true;
            minusDuration();
        }
        
        if(duration <= 0){
            ship.invulnerable = false;
            this.active = false;
            duration = 120;
        }
        
        if(dropDuration >= 0){
            dropDuration--;
        }
        else if(dropDuration <= 0){
            this.visible = false;
        }
    }
}
