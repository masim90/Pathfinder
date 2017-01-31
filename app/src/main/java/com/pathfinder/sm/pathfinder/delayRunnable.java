package com.pathfinder.sm.pathfinder;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by dell2 on 31.01.2017.
 */
public class delayRunnable implements Runnable {

    XandY customVariable;
    private int i;
    Button[][] buttonArray = new Button[11][8];
    List<Button> pathArray = new ArrayList<>();

    public delayRunnable(XandY customVariable, int i, Button[][] buttonArray, List pathArray) {
        this.customVariable = customVariable;
        this.i = i;
        this.buttonArray = buttonArray;
        this.pathArray = pathArray;
    }

    @Override
    public void run() {
        System.out.println("Test");
        Random rnd = new Random();
        boolean stop = false;
        while(!stop) {
            int direction = rnd.nextInt(4) + 1;
            switch (direction) {
                case 1:
                    if(customVariable.getX()+1 <= 11){
                        if (((ColorDrawable)buttonArray[customVariable.getX()+1][customVariable.getY()].getBackground()).getColor() != Color.GREEN){
                            buttonArray[customVariable.getX()+1][customVariable.getY()].setBackgroundColor(Color.GREEN);
                            customVariable.setX(customVariable.getX()+1);
                            stop = true;
                        }
                    }
                    break;
                /*case 2:
                    if(customVariable.getX()-1 >= 0){
                        if(((ColorDrawable)buttonArray[customVariable.getX()-1][customVariable.getY()].getBackground()).getColor() != Color.GREEN) {
                            buttonArray[customVariable.getX() - 1][customVariable.getY()].setBackgroundColor(Color.GREEN);
                            customVariable.setX(customVariable.getX()-1);
                            stop = true;
                        }
                    }
                    break;*/
                case 3:
                    if(customVariable.getY()+1 <= 7) {
                        if (((ColorDrawable) buttonArray[customVariable.getX()][customVariable.getY() + 1].getBackground()).getColor() != Color.GREEN) {
                            buttonArray[customVariable.getX()][customVariable.getY() + 1].setBackgroundColor(Color.GREEN);
                            customVariable.setY(customVariable.getY()+1);
                            stop = true;
                        }
                    }
                    break;
                case 4:
                    if(customVariable.getY()-1 >= 0) {
                        if (((ColorDrawable) buttonArray[customVariable.getX()][customVariable.getY() - 1].getBackground()).getColor() != Color.GREEN) {
                            buttonArray[customVariable.getX()][customVariable.getY() - 1].setBackgroundColor(Color.GREEN);
                            customVariable.setY(customVariable.getY()-1);
                            stop = true;
                        }
                    }
                    break;
                default:
                    break;
            }
        }
        pathArray.add(i+1, buttonArray[customVariable.getX()][customVariable.getY()]);
        System.out.println(customVariable.getX() + " " + customVariable.getY());
    }
}

