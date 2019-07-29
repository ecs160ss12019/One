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

    public int score;

    ///////////////////////////
    //      CONSTRUCTOR
    ///////////////////////////

    public HUD(PointF blockSize, int numOfLives) {

        // Setting JoyStick to 50% of screen, with baseRad of 20% of screen, and hatRadius of 8% of screen
        Point pos = new Point(20,70);
        joyStick = new JoyStick(pos, 15, 4, blockSize);

        // Setting location of fire button
        pos = new Point(80, 70);
        shootButton = new Button(pos, blockSize, "Fire");

        // Setting the number of lives
        this.numOfLives = numOfLives;

        // Generating ship icon for HUD
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
        PointF[] shapeCoords = new PointF[5];
        shapeCoords[0] = new PointF(50, 50);
        shapeCoords[1] = new PointF(51, 53);
        shapeCoords[2] = new PointF(50, 52);
        shapeCoords[3] = new PointF(49, 53);
        shapeCoords[4] = new PointF(50, 50);

        Path ship = new Path();

        for(int i = 1; i < shapeCoords.length; ++i)
            ship.lineTo(shapeCoords[i].x * blockSize.x, shapeCoords[i].y * blockSize.y);

        return ship;
    }

}
