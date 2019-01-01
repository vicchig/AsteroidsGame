
import net.java.games.input.*;

public class XboxController {

    public boolean upKey;
    public boolean mBackwardsKey;
    public boolean mTurnRightKey;
    public boolean mTurnLeftKey;

    public XboxController() {
        /*button 0 = a / x;
         button 1 = b / square;
         button 2 = x / triangle;
         button 3 = y / circle;
         button 4 is l1;
         button 5 is r1;
         button 6 is back / select;
         button 7 is start;
         button 8 is l2;
         button 9 is r2;
         down is hat switch 0.75;
         right is hat switch 0.5;
         left is hat switch 1.0;
         up is hat switch 0.25;
         LeftL is X Axis	<0
         LeftR is X Axis	>0
         LeftU is Y Axis	<0
         LeftD is Y Axis	>0
         RightL is X Rotation <0
         RightR is X Rotation >0
         RightU is Y Rotation <0
         RightD is Y Rotation >0*/
    }

    public void update(Component[] pButtons) {
        double pXAxis = 0; 
        double pYAxis = 0;
        for (int i = 0; i < pButtons.length; i++) {
            switch (pButtons[i].getName()) {
                case "Button 0":
                    if (pButtons[i].getPollData() == 1) {
                        Asteroids.upKey = true;
                    } else {
                        Asteroids.upKey = false;
                    }
                    break;
                case "X Axis":
                    //change the 0.1 in order to make the ship rotate slower or faster
                    if (pButtons[i].getPollData()>=0.15 || pButtons[i].getPollData()<= -0.02) {
                        pXAxis = pButtons[i].getPollData();
                    }
                    break;
                case "Y Axis":
                    if (pButtons[i].getPollData()>=0.05 || pButtons[i].getPollData()<= -0.02) {
                        pYAxis = pButtons[i].getPollData();
                    }
                    break;
                case "Button 5":
                    if (pButtons[i].getPollData() == 1) {
                        Asteroids.spaceKey = true;
                    } else {
                        Asteroids.spaceKey = false;
                    }
                    break;
                case "Button 6":
                    break;
                case "Button 7":
                    break;
                case "Button 8":
                    break;
                case "Button 9":
                    break;
            }
        }
        if (pXAxis!=0 || pYAxis!=0) {
            double pAngle = Math.atan2(pYAxis, pXAxis);
            Asteroids.ship.angle = pAngle;
        }
    }
}