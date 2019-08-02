package com.example.asteroids;

import android.graphics.Point;
import android.graphics.PointF;

public class Menu {

    protected Button[] buttons;
    protected PointF blockSize;
    protected String menuType;
    protected int numOfButtons;
    protected Point buttonPos;
    int xSize, ySize;
    String textField;


    public Menu(PointF blockSize, String menuSelect) {
        if (menuSelect == "mainMenu") {
            menuType = menuSelect;
            numOfButtons = 1;
            buttons = new Button[numOfButtons];
            buttonPos = new Point((int) (blockSize.x * 10), (int) (blockSize.y * 20));
            xSize = 40;
            ySize = 60;
            textField = "Enter  the  Arena";
            buttons[0] = new Button(buttonPos, xSize, ySize, blockSize, textField);

        } else if (menuSelect == "pauseMenu") {
            menuType = menuSelect;
            numOfButtons = 3;
            buttons = new Button[numOfButtons];

            // BUTTON 1 - Resume
            buttonPos = new Point((int) (blockSize.x * 30), (int) (blockSize.y * 25));
            xSize = 40;
            ySize = 15;
            textField = "Resume";
            buttons[0] = new Button(buttonPos, xSize, ySize, blockSize, textField);

            // BUTTON 2 - Restart
            buttonPos = new Point((int) (blockSize.x * 30), (int) (blockSize.y * 45));
            xSize = 40;
            ySize = 15;
            textField = "Restart";
            buttons[1] = new Button(buttonPos, xSize, ySize, blockSize, textField);

            // Button 3 - Main Menu
            buttonPos = new Point((int) (blockSize.x * 30), (int) (blockSize.y * 65));
            xSize = 40;
            ySize = 15;
            textField = "Main Menu";
            buttons[2] = new Button(buttonPos, xSize, ySize, blockSize, textField);


        }
    }

    public void draw(String menuType) {

    }


}
