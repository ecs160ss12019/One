package com.example.asteroids;

import android.graphics.PointF;

public class Menu {

    protected Button[] buttons;
    protected PointF blockSize;


    public Menu(PointF blockSize, String menuSelect) {
        if (menuSelect == "mainMenu") {
            int numOfButtons = 1;
            buttons = new Button[numOfButtons];
            // Point buttonPos, PointF blockSize, String text

            /*
            addRect(float left, float top, float right, float bottom, Path.Direction dir)
             */

            Path

            buttons[0] = new Button(_, blockSize, "Start game")


        } else if (menuSelect == "pauseMenu") {


        }

    }

}
