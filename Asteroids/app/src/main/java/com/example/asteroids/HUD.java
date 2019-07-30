package com.example.asteroids;

// AUTHOR NAME HERE

import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PointF;

public class HUD {
    ///////////////////////////
    //      VARIABLES
    ///////////////////////////
    protected JoyStick joyStick;
    protected Button pauseButton;
    protected Button shootButton;
    protected Path shipIcon;
    protected int numOfLives;
    PointF[] shapeCoords;
    public int score;
    int startX;
    int startY;
    PointF blockSize;

    ///////////////////////////
    //      CONSTRUCTOR
    ///////////////////////////

    public HUD(PointF blockSize, int numOfLives) {
        this.blockSize = blockSize;

        // Setting JoyStick to 50% of screen, with baseRad of 20% of screen, and hatRadius of 8% of screen
        Point pos = new Point(20,70);
        joyStick = new JoyStick(pos, 15, 4, blockSize);

        // Setting location of fire button
        pos = new Point(85 * (int) blockSize.x, 85 * (int) blockSize.y);
        shootButton = new Button(pos, 15, 15, blockSize, "Fire");

        // allocate coord array to hold ship icon for displaying lives
        shapeCoords = new PointF[5];

        // Setting the number of lives
        this.numOfLives = numOfLives;

        // Generating ship icon for HUD
        startX = 5 * (int) blockSize.x;
        startY = 5 * (int) blockSize.y;
        this.shipIcon = generateShipIcon(blockSize);


/*
        //Pause button is in the top right corner
        PointF pausePosition = new PointF(85,5);
        pauseButton = new Button(pausePosition, blockSize);


        PointF shootPosition = new PointF(85, 95);
        shootButton = new Button(shootPosition, blockSize);
*/
        // Holds shapes to return in draw method.
       // shapes = new Path[2];
    }

    ///////////////////////////
    //      METHODS
    ///////////////////////////
    public Path generateShipIcon(PointF blockSize) {
        shapeCoords[0] = new PointF(startX, startY);
        shapeCoords[1] = new PointF(startX + (1 * blockSize.x), startY + (3 * blockSize.y));
        shapeCoords[2] = new PointF(startX, startY + (2 * blockSize.y));
        shapeCoords[3] = new PointF(startX - (1 * blockSize.x), startY + (3 * blockSize.y));
        shapeCoords[4] = new PointF(startX, startY);

        Path ship = new Path();


        ///////
        int xOffset = 4 * (int) blockSize.x;

        for (int i = 0; i < this.numOfLives; i++) {
            ship.moveTo(startX + (i * xOffset), startY);

            for (int j = 1; j < shapeCoords.length; j++) {
                ship.lineTo(shapeCoords[j].x, shapeCoords[j].y);
                shapeCoords[j].x += xOffset;
            }
        }

        return ship;
    }


    void updateLives(int numofLives) {
       this.numOfLives = numofLives;
        shipIcon = generateShipIcon(this.blockSize);
    }

}
