import java.util.ArrayList;
import net.java.games.input.*;

public class ControllerInput {
    XboxController mController = new XboxController(); 
    //PlaystationController mController = new PlaystationController();
    ArrayList<Controller> mControllers = new ArrayList();
    public ControllerInput() {
        ControllerEnvironment pCE = ControllerEnvironment.getDefaultEnvironment();
        Controller[] pControllers = pCE.getControllers();
        
        for (int i = 0; i < pControllers.length; i++) {
       if (pControllers[i].getType() == Controller.Type.GAMEPAD || pControllers[i].getType() == Controller.Type.STICK) {
                mControllers.add(pControllers[i]);
            }
        }
        System.out.print(pControllers.length);
    }

    public void update() {
        for (int i = 0; i < mControllers.size(); i++){
            mControllers.get(i).poll();

            Component[] pControllerButtons = mControllers.get(i).getComponents();
            mController.update(pControllerButtons);
        }
    }
}
