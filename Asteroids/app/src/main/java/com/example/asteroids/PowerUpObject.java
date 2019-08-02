package com.example.asteroids;

//Martin Petrov

/*
    This class contains a context variable for the powerUpState. The PowerUpManager is in charge
    of creating a new instance of this class. The object's paint color depends on the powerUpType,
 */

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

        createPowerUp(powerUpType);
        projectileOwner = 5;
        this.position = position;
        shapeCoords = new PointF[5];
        genShape();

        //Make Powerups' paint render to screen with rounded corners
        paint.setDither(true);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setPathEffect(new CornerPathEffect(50) );
        paint.setAntiAlias(true);
    }


    /*Creates a different type of powerup based on the int that is passed to it.*/
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
