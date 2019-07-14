package com.example.asteroids;

import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PointF;

public class JoyStick {

    ///////////////////////////
    //      VARIABLES
    ///////////////////////////
    private PointF baseCenter = new PointF();
    private PointF hatCenter = new PointF();
    private PointF stickPosition = new PointF();
    private Point resolution;
    private PointF blockSize;
    private float baseRadius;
    private float hatRadius;
    private boolean beingTouched;
    private Path base = new Path();
    private Path hat = new Path();

    ///////////////////////////
    //      CONSTRUCTOR
    ///////////////////////////

    public JoyStick(PointF blockSize) {
        this.blockSize = blockSize;
        this.baseCenter.x = (50 * blockSize.x);
        this.baseCenter.y = (50 * blockSize.y);
        this.hatCenter.x = (50 * blockSize.x);
        this.hatCenter.y = (50 * blockSize.y);
        this.resolution.x = (int) blockSize.x * 100;
        this.resolution.y = (int) blockSize.y * 100;
        this.baseRadius = (int) (20 * blockSize.x);
        this.hatRadius = (int) (8 * blockSize.x);
        this.stickPosition.x = 0;
        this.stickPosition.y = 0;
        this.beingTouched = false;
        this.base.addCircle(baseCenter.x, baseCenter.y, baseRadius, Path.Direction.CW);
        this.hat.addCircle(hatCenter.x, hatCenter.y, hatRadius, Path.Direction.CW);
    }

    ///////////////////////////
    //      METHODS
    ///////////////////////////

    // TODO: make mapped stick functionality.
    public PointF getScaledStickPosition() {
        // this method returns the percentage of how far the stick is engaged in x-y directions
        // bounds are [-100f - +100f], where -100f means -100% engaged on the axis and +100f means
        // %100% engaged on the axis.

        float xEngaged = 100 * (stickPosition.x / baseRadius);
        float yEngaged = 100 * (stickPosition.y / baseRadius);
        PointF scaledOutput = new PointF(xEngaged, yEngaged);
        return scaledOutput;
    }

    public void setTouchStatus(boolean temp) {
        this.beingTouched = temp;
    }


    public void updateStick(float x, float y) {
        if (beingTouched) {
            // read the stick's current position
            stickPosition.x = x;
            stickPosition.y = y;

            // find magnitude of displacement from center of base circle
            float stickDisplacement = (float) Math.sqrt(Math.pow((stickPosition.x - baseCenter.x), 2) + Math.pow((stickPosition.y - baseCenter.y), 2));

            if (stickDisplacement < baseRadius) {
                // when hat does't need constraining to base radius
                hatCenter = stickPosition;
            } else {
                // when hat needs constraining to base radius
                float ratio = baseRadius / stickDisplacement;
                float constrainedX = baseCenter.x + (stickPosition.x - baseCenter.x) * ratio;
                float constrainedY = baseCenter.y + (stickPosition.y - baseCenter.y) * ratio;
                stickPosition.x = constrainedX;
                stickPosition.y = constrainedY;
            }
        }
    }


    public Path[] draw() {
        //get current position of hat, then move it

        if (beingTouched) {
            hat.moveTo(baseCenter.x, baseCenter.y);
        } else {
            hat.moveTo(stickPosition.x, stickPosition.y);
        }

        Path[] joyStick = new Path[2];
        joyStick[0] = base;
        joyStick[1] = hat;
        return joyStick;
    }


}
