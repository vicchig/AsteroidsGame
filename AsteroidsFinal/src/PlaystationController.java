

import net.java.games.input.Component;

public class PlaystationController {

    

    public String buttonS, buttonX, buttonC, buttonT, buttonLB, buttonRB, buttonShare, buttonOptions, dpad;
    public float valButton, dpadUp, dpadDown, dpadLeft, dpadRight;
    public String LeftL, LeftR, LeftU, LeftD, RightL, RightR, RightU, RightD;
    public float valAnalog;
    private int menuTimer;
    private float opp, adj;
    private float angle;

    public PlaystationController() {
        buttonS = "Button 0";
        buttonX = "Button 1";
        buttonC = "Button 2";
        buttonT = "Button 3";
        buttonLB = "Button 4";
        buttonRB = "Button 5";
        buttonShare = "Button 8";
        buttonOptions = "Button 9";
        dpad = "Hat Switch";
        LeftL = "X Axis";
        LeftR = "X Axis";
        LeftU = "Y Axis";
        LeftD = "Y Axis";
        RightL = "Z Axis";
        RightR = "Z Axis";
        RightU = "Z Rotation";
        RightD = "Z Rotation";

        valButton = 1;
        valAnalog = 0.5f;
        dpadUp = 0.25f;
        dpadDown = 0.75f;
        dpadLeft = 1f;
        dpadRight = 0.5f;

    }

    public void update(Component[] controllerButtons) {
        opp = 0;
        adj = 0;
        for (int i = 0; i < controllerButtons.length; i++) {
            if (controllerButtons[i].getName().equals(buttonX)
                    && controllerButtons[i].getPollData() == valButton) {
                System.out.println("X button hit");

                
            }
            if (controllerButtons[i].getName().equals(buttonS)
                    && controllerButtons[i].getPollData() == valButton) {
                System.out.println("S button hit");
            }
            if (controllerButtons[i].getName().equals(buttonC)
                    && controllerButtons[i].getPollData() == valButton) {
                System.out.println("C button hit");
            }
            if (controllerButtons[i].getName().equals(buttonT)
                    && controllerButtons[i].getPollData() == valButton) {
                System.out.println("T button hit");
            }
            if (controllerButtons[i].getName().equals(buttonLB)
                    && controllerButtons[i].getPollData() == valButton) {
                System.out.println("LB button hit");
                Asteroids.ship.accelerate();

            }
            if (controllerButtons[i].getName().equals(buttonRB)
                    && controllerButtons[i].getPollData() == valButton) {
                System.out.println("RB button hit");
                Asteroids.spaceKey = true;
            }
            if (controllerButtons[i].getName().equals(buttonOptions)
                    && controllerButtons[i].getPollData() == valButton) {
                System.out.println("Options button hit");

            }
            if (controllerButtons[i].getName().equals(buttonShare)
                    && controllerButtons[i].getPollData() == valButton) {
                System.out.println("Share button hit");
            }
            if (controllerButtons[i].getName().equals(dpad)
                    && controllerButtons[i].getPollData() == dpadDown) {
                System.out.println("DOWN button hit");
                menuTimer++;

            }

            if (controllerButtons[i].getName().equals(dpad)
                    && controllerButtons[i].getPollData() == dpadUp) {
                System.out.println("UP button hit");
                menuTimer++;

            }
            if (controllerButtons[i].getName().equals(dpad)
                    && controllerButtons[i].getPollData() == dpadLeft) {
                System.out.println("LEFT button hit");
            }
            if (controllerButtons[i].getName().equals(dpad)
                    && controllerButtons[i].getPollData() == dpadRight) {
                System.out.println("RIGHT button hit");
            }
            if (controllerButtons[i].getName().equals(LeftL)
                    && Math.round(controllerButtons[i].getPollData()) <= -valAnalog) {
                System.out.println("Left Analog Left");
            }
            if (controllerButtons[i].getName().equals(LeftR)
                    && Math.round(controllerButtons[i].getPollData()) >= valAnalog) {
                System.out.println("Left Analog Right");
            }
            if (controllerButtons[i].getName().equals(LeftU)
                    && Math.round(controllerButtons[i].getPollData()) <= -valAnalog) {
                System.out.println("Left Analog Up");
                menuTimer++;

            }
            if (controllerButtons[i].getName().equals(LeftD)
                    && Math.round(controllerButtons[i].getPollData()) >= valAnalog) {
                System.out.println("Left Analog Down");
                menuTimer++;


            }

            /*__________________________________________*/
            if (controllerButtons[i].getName().equals(LeftL)
                    && controllerButtons[i].getPollData() <= -0.15) {
                adj = controllerButtons[i].getPollData();
            }
            if (controllerButtons[i].getName().equals(LeftR)
                    && controllerButtons[i].getPollData() >= 0.15) {
                adj = controllerButtons[i].getPollData();
            }
            if (controllerButtons[i].getName().equals(LeftU)
                    && controllerButtons[i].getPollData() <= -0.15) {
                opp = controllerButtons[i].getPollData();
            }
            if (controllerButtons[i].getName().equals(LeftD)
                    && controllerButtons[i].getPollData() >= 0.15) {
                opp = controllerButtons[i].getPollData();

            }
        }
        if (opp != 0 || adj != 0) {
            angle = (float) Math.atan(opp / adj);
            if (opp >= 0 && adj < 0) {
                angle = (float) (Math.toRadians(180) + angle);
            } else if (opp < 0 && adj < 0) {
                angle = (float) (Math.toRadians(180) + angle);
            } else if (opp < 0 && adj >= 0) {
                angle = (float) (Math.toRadians(360) + angle);
            }
            Asteroids.ship.angle = Math.toDegrees(angle);
        }

    }
}