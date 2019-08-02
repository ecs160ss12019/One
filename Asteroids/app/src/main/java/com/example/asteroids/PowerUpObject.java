package com.example.asteroids;

//Martin Petrov

import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.Log;


public class PowerUpObject extends MovableObject {

    public PointF position;
    public PowerState powerType;

    public PowerUpObject(PointF position, int powerUpType, PointF blockSize) {
        super(blockSize);
        Log.d("Rand", "powerup: " + powerUpType);

        createPowerUp(powerUpType);
        projectileOwner = 5;

        this.position = position;
        shapeCoords = new PointF[5];
        genShape();

        //Make Powerups more rounded
        paint.setDither(true);                    // set the dither to true
        paint.setStrokeJoin(Paint.Join.ROUND);    // set the join to round you want
        paint.setStrokeCap(Paint.Cap.ROUND);      // set the paint cap to round too
        paint.setPathEffect(new CornerPathEffect(50) );   // set the path effect when they join.
        paint.setAntiAlias(true);

    }

    private void createPowerUp(int type) {

        switch (type) {
            case 0:
                powerType = new BurstFirePowerState();
                paint.setColor(Color.argb(255,245,34,73));
                break;

            case 1:
                powerType = new ExtraLifePowerState();
                paint.setColor(Color.argb(255,0,242,44));
                break;

            case 2:
                powerType = new ShieldPowerState();
                paint.setColor(Color.argb(255,47,247,250));
                break;
        }

    }

    private void genShape() {
        shapeCoords[0] = new PointF(position.x,position.y);
        shapeCoords[1] = new PointF(position.x + 3,position.y);
        shapeCoords[2] = new PointF(position.x + 3,position.y + 5);
        shapeCoords[3] = new PointF(position.x,position.y + 5);
        shapeCoords[4] = new PointF(position.x,position.y);
    }


}
