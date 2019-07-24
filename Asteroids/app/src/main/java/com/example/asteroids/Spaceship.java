package com.example.asteroids;

// AUTHOR NAME HERE

import android.graphics.PointF;
import android.util.Log;

import java.util.Random;


public class Spaceship extends MovableObject {
    ///////////////////////////
    //      VARIABLES
    ///////////////////////////

    protected PointF thrust;
    private float steeringInput;
    public ProjectileManager projectileManager;

    ///////////////////////////
    //      CONSTRUCTOR
    ///////////////////////////

    public Spaceship(PointF blockSize, ProjectileManager projectileManager) {
        // posX, posY, mass, maxVelocity, minVelocity, drctnVector are the parameters
        super(blockSize);
        this.projectileManager = projectileManager;
        mass = 10;
        shapeCoords = new PointF[5];
        thrust = new PointF(0,0);
        genShape();

    }

    ///////////////////////////
    //      METHODS
    ///////////////////////////
    public void calcRotation(PointF joyStickPos, long fps) {

        rotation += ROTATION_SCALAR * joyStickPos.x/fps;
    }



    public void genShape() {
        shapeCoords[0] = new PointF(50, 50);
        shapeCoords[1] = new PointF(51, 53);
        shapeCoords[2] = new PointF(50, 52);
        shapeCoords[3] = new PointF(49, 53);
        shapeCoords[4] = new PointF(50, 50);

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
        Log.d("rotation", "rotation: " +rotation);
        Log.d("rotation", "steering input: " + steeringInput);

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
                rotation += 5;
            else
                rotation -= 5;

        }





    }


    private void setThrust(PointF joystickPos) {
        thrust.x = VELOCITY_SCALAR * (float) (joystickPos.x * Math.sin(Math.toRadians(rotation)));
        thrust.y = VELOCITY_SCALAR * (float) (joystickPos.y * Math.cos(Math.toRadians(rotation)));
        Log.d("force", "thrust: " + thrust);
    }

    public void update(long fps, PointF joyStickPos) {


        rotateShip(joyStickPos);
        updatePhysics(fps, joyStickPos);
        checkBounds();
        if(new Random().nextInt(20) == 1){
            projectileManager.fire(currVelocity,
                    new PointF(currVelocity.x/fps,currVelocity.y/fps),
                    shapeCoords[1],
                    shapeCoords[3],
                    rotation,
                    fps);
        }
    }

}
