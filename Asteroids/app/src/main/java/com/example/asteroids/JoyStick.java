package com.example.asteroids;

import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PointF;
import android.util.Log;

public class JoyStick {

    ///////////////////////////
    //      VARIABLES
    ///////////////////////////

    private PointF blockSize;
    private PointF baseCenter;
    private PointF stickPosition;
    private PointF scaledOutput;

    private float baseRadius;
    private float hatRadius;

    private Path base;
    private Path hat;
    private Path[] joyStick = new Path[2];

    ///////////////////////////
    //      CONSTRUCTOR
    ///////////////////////////

    public JoyStick(Point position, int baseRadius, int hatRadius, PointF blockSize) {

        //NOTE: Just trust that base center must have (blockSize.y / blockSize.x). Long story. . .
        baseCenter = new PointF(position.x, position.y * blockSize.y / blockSize.x);
        stickPosition = new PointF(baseCenter.x, baseCenter.y);
        scaledOutput = new PointF(baseCenter.x, baseCenter.y);


        base = new Path();
        hat = new Path();

        this.blockSize = blockSize;
        this.baseRadius = baseRadius;
        this.hatRadius = hatRadius;

        base.addCircle(baseCenter.x * blockSize.x, baseCenter.y * blockSize.x, baseRadius * blockSize.x, Path.Direction.CW);
        hat.addCircle(baseCenter.x * blockSize.x, baseCenter.y * blockSize.x, hatRadius * blockSize.x, Path.Direction.CW);

    }

    ///////////////////////////
    //      METHODS
    ///////////////////////////

    public PointF getScaledStickPosition() {
        // this method returns the percentage of how far the stick is engaged in x-y directions
        // bounds are [-100f - +100f], where -100f means -100% engaged on the axis and +100f means
        // %100% engaged on the axis.

        scaledOutput.x = 100 * (stickPosition.x - baseCenter.x ) / (baseRadius);
        scaledOutput.y = -100 * (stickPosition.y - baseCenter.y ) / (baseRadius);

        return scaledOutput;
    }

    public void resetJoyStick() {
        hat.reset();
        hat.addCircle(baseCenter.x * blockSize.x, baseCenter.y * blockSize.x, hatRadius * blockSize.x, Path.Direction.CW);
        stickPosition.x = baseCenter.x;
        stickPosition.y = baseCenter.y;
    }

    //X and Y have values of 0-100 mapped to domain of screen
    public void updateStick(float x, float y) {

        //distance of touch from joystick
        float xCentered = x - baseCenter.x;
        float yCentered = y - baseCenter.y;

        //TODO: add bound on where you can touch joystick from w/ simple if statement
        //distance from the center of joystick
        float distanceFromCenter = (float) Math.sqrt(Math.pow(xCentered, 2 ) + Math.pow(yCentered, 2));

        if (distanceFromCenter < baseRadius) { //If the touch is in bounds of joystick
            stickPosition.x = x;
            stickPosition.y = y;
        } else { // when hat needs constraining to base radius
            float ratio = baseRadius / distanceFromCenter ;
            float constrainedX = baseCenter.x + (xCentered) * ratio;
            float constrainedY = baseCenter.y + (yCentered) * ratio;
            stickPosition.x = constrainedX;
            stickPosition.y = constrainedY;
        }

        //Reset path, add a new circle with the new coordinates
        hat.rewind();
        hat.addCircle(stickPosition.x * blockSize.x ,stickPosition.y * blockSize.x, hatRadius * blockSize.x, Path.Direction.CW);

    }


    public Path[] draw() {
        joyStick[0] = base;
        joyStick[1] = hat;
        return joyStick;
    }

}
