package com.example.asteroids;

// Brian Coe

import android.graphics.Path;
import android.graphics.Point;
import java.util.Random;
import android.graphics.PointF;

public class Asteroid extends MovableObject {

    ///////////////////////////
    //      VARIABLES
    ///////////////////////////
    private int scalar = 15;
    private int xOffSet = 400;
    private int yOffSet = 600;
    Random random = new Random();
    private Point[] ab1 = new Point[7];
    private Point[] ab2 = new Point[8];
    private Point[] ab3 = new Point[5];
    private Point[] am1 = new Point[7];
    private Point[] am2 = new Point[5];
    private Point[] am3 = new Point[5];
    private Point[] as1 = new Point[4];
    private Point[] as2 = new Point[5];
    private Point[] as3 = new Point[6];
    private Point[] currentAsteroid;
    private int numHits;
    private int asteroidType;

    ///////////////////////////
    //      CONSTRUCTOR
    ///////////////////////////

    public Asteroid( PointF blockSize) {
        // posX, posY, mass, maxVelocity, minVelocity, drctnVector, shape
        super(blockSize);
        populate();
        asteroidType = random.nextInt(9) + 1;
        randOffset(blockSize);
        currentAsteroid = which();
        // posX, posY, mass, maxVelocity, minVelocity, drctnVector are the parameters


    }

    ///////////////////////////
    //      METHODS
    ///////////////////////////

    public Path draw() {
        shape.rewind();
        if(currentAsteroid != null) {
            shape.moveTo(currentAsteroid[0].x * scalar + xOffSet, currentAsteroid[0].y * scalar + yOffSet);
            for (int i = 1; i < currentAsteroid.length; i++) {
                shape.lineTo(currentAsteroid[i].x * scalar + xOffSet, currentAsteroid[i].y * scalar + yOffSet);
            }
        }
        return shape;
    }

    private void randOffset(PointF blockSize){
        // pick where to generate
        if(random.nextInt(2) == 1){
            xOffSet = random.nextInt((int) (100 * blockSize.y));
            if(random.nextInt(2) == 1){
                yOffSet = scalar;
            }else{
                yOffSet = (int) (100 * blockSize.y - scalar);
            }
        }else{
            yOffSet = random.nextInt((int) (100 * blockSize.x));
            if(random.nextInt(2) == 1){
                xOffSet = scalar;
            }else{
                xOffSet = (int)  (100 * blockSize.x - scalar);
            }
        }
    }


    private Point[] which(){
        switch(asteroidType){
            case 1:
                return ab1;
            case 2:
                return ab2;
            case 3:
                return ab3;
            case 4:
                return am1;
            case 5:
                return am2;
            case 6:
                return am3;
            case 7:
                return as1;
            case 8:
                return as2;
            default:
                return as3;
    }
}

    private void populate() {
        ab1[0] = new Point(0,0);
        ab1[1] = new Point(5,1);
        ab1[2] = new Point(7,6);
        ab1[3] = new Point(4,5);
        ab1[4] = new Point(5,8);
        ab1[5] = new Point(1,7);
        ab1[6] = new Point(2,3);

        ab2[0] = new Point(2,0);
        ab2[1] = new Point(4,1);
        ab2[2] = new Point(6,0);
        ab2[3] = new Point(8,2);
        ab2[4] = new Point(7,6);
        ab2[5] = new Point(4,5);
        ab2[6] = new Point(1,6);
        ab2[7] = new Point(0,2);

        ab3[0] = new Point(4,0);
        ab3[1] = new Point(8,1);
        ab3[2] = new Point(7,7);
        ab3[3] = new Point(3,3);
        ab3[4] = new Point(0,4);

        am1[0] = new Point(0,1);
        am1[1] = new Point(2,2);
        am1[2] = new Point(1,0);
        am1[3] = new Point(4,1);
        am1[4] = new Point(4,3);
        am1[5] = new Point(3,4);
        am1[6] = new Point(1,4);

        am2[0] = new Point(0,0);
        am2[1] = new Point(3,1);
        am2[2] = new Point(2,2);
        am2[3] = new Point(4,4);
        am2[4] = new Point(1,5);

        am3[0] = new Point(0,2);
        am3[1] = new Point(2,0);
        am3[2] = new Point(4,1);
        am3[3] = new Point(3,4);
        am3[4] = new Point(1,4);

        as1[0] = new Point(0,0);
        as1[1] = new Point(3,1);
        as1[2] = new Point(2,2);
        as1[3] = new Point(0,2);

        as2[0] = new Point(0,0);
        as2[1] = new Point(2,0);
        as2[2] = new Point(3,3);
        as2[3] = new Point(0,2);
        as2[4] = new Point(1,1);

        as3[0] = new Point(0,1);
        as3[1] = new Point(1,0);
        as3[2] = new Point(3,2);
        as3[3] = new Point(2,2);
        as3[4] = new Point(1,3);
        as3[5] = new Point(1,2);
    }
}
