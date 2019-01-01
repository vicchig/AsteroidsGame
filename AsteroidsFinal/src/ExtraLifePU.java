import java.awt.*;
import java.awt.Polygon.*;


public class ExtraLifePU extends PowerUp{
    public ExtraLifePU(){
        ID = 2;
    }
    public void addLife(SpaceCraft ship){
        if(this.active){
            ship.lives++;
            this.active = false;
        }
        
    }
    public void managePower(){
        if(dropDuration >= 0){
            dropDuration--;
        }
        else if(dropDuration <= 0){
            this.visible = false;
        }
    }
}
