package com.example.asteroids;

// Martin Petrov

import android.graphics.BlurMaskFilter;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.Log;


public class Spaceship extends MovableObject {
    ///////////////////////////
    //      VARIABLES
    ///////////////////////////
    public int numLives = 3;

    //Variables dealing with ship's controls
    protected PointF thrust;
    private float steeringInput;
    public boolean firing;

    //Testing new PAINT
    Paint normalPaint;
    Paint blurPaint;

    public ProjectileManager projectileManager;

    public PowerState currPowerState;
    private long powerUpTime;



    ///////////////////////////
    //      CONSTRUCTOR
    ///////////////////////////

    public Spaceship(PointF blockSize, ProjectileManager projectileManager) {
        // posX, posY, mass, maxVelocity, minVelocity, drctnVector are the parameters
        super(blockSize);
        this.projectileManager = projectileManager;
        projectileOwner = 1;
        mass = 10;
        shapeCoords = new PointF[5];
        setPaint();
        thrust = new PointF(0,0);
        genShape();

        //TODO: set to default when finished debugging
        setPowerUp(new DefaultPowerState());

        normalPaint = new Paint();
        normalPaint.setAntiAlias(true);
       // normalPaint.setDither(true);
        normalPaint.setColor(Color.argb(248, 255, 255, 255));
        normalPaint.setStyle(Paint.Style.STROKE);
        //normalPaint.setStrokeJoin(Paint.Join.ROUND);
        //normalPaint.setStrokeCap(Paint.Cap.ROUND);
        //normalPaint.setStrokeWidth(20f);

        blurPaint = new Paint();
        blurPaint.set(normalPaint);
        blurPaint.setStyle(Paint.Style.FILL);
        blurPaint.setColor(Color.argb(255,255,255,255));
        blurPaint.setStrokeWidth(10f);
        blurPaint.setMaskFilter(new BlurMaskFilter(10, BlurMaskFilter.Blur.NORMAL));


    }

    ///////////////////////////
    //      METHODS
    ///////////////////////////
    public void checkPowerUpTime() {
        if(System.currentTimeMillis() - powerUpTime >= 15000)
            currPowerState = new DefaultPowerState();
    }


    public void checkBounds() {
        for(PointF i: shapeCoords) {
            if (i.x > 105) {
                for (PointF j : shapeCoords)
                    j.x -= 100;
            }
            if (i.x < -5) {
                for (PointF j : shapeCoords)
                    j.x += 100;
            }
            if (i.y > 105) {
                for (PointF j : shapeCoords)
                    j.y -= 100;
            }
            if (i.y < -5) {
                for (PointF j : shapeCoords)
                    j.y += 100;
            }
        }
    }


    private void genShape() {
        shapeCoords[0] = new PointF(50, 50);
        shapeCoords[1] = new PointF(51, 53);
        shapeCoords[2] = new PointF(50, 52);
        shapeCoords[3] = new PointF(49, 53);
        shapeCoords[4] = new PointF(50, 50);
    }


    private void rotateShip(PointF joyStick) {

        if (joyStick.x == 0 && joyStick.y == 0)
            return;

            //Unit circle
        else if (joyStick.x > 0) {
            steeringInput = (float) Math.toDegrees(Math.asin(joyStick.y / 100));
            steeringInput -= 90;
        } else if (joyStick.x < 0) {
            steeringInput =  (float) Math.toDegrees(-1 * Math.asin(joyStick.y/100));
            steeringInput -= 270;
        }
        steeringInput *= -1;

        //Accounting from movement from 360-0 && 0-360
        rotation = rotation % 360;
        if (rotation < 0) {
            rotation = 360 + rotation;
        }

        if (rotation > 270 && steeringInput < 90) {
            rotation += 5;
        } else if (rotation < 90 && steeringInput > 270) {
            rotation -= 5;
        } else {
            if (rotation < steeringInput)
                rotation += 10;
            else
                rotation -= 10;

        }
    }


    public void setPaint(){
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(3);
        paint.setColor(Color.WHITE);
    }


    public void setPowerUp(PowerState powerUp) {
        currPowerState = powerUp;
        powerUpTime = System.currentTimeMillis();
    }


    public void update(long fps, HUD hud) {
        rotateShip(hud.joyStick.getScaledStickPosition());
        updatePhysics(fps, hud.joyStick.getScaledStickPosition());
        checkBounds();

        if(firing)
            currPowerState.fire(this);

        currPowerState.update(this);
        checkPowerUpTime();

        //Check if the ship was hit, and reset ship if necessary
        hud.numOfLives = numLives;
        if(isHit) {
            --numLives;
            isHit = false;
            genShape();
            currVelocity = new PointF(0,0);
            rotation = 0;
            setPowerUp(new ShieldPowerState());
            powerUpTime = System.currentTimeMillis() - 13000;
        }
    }


}