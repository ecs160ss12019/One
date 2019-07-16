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
    private Point[] offSet;
    Random random = new Random();
    private AsteroidGenerator aGen;
    private int[] asteroidTracker;
    private Point[] currentAsteroid;
    private int numAsteroids = 10;
    private int numHits;
    private int asteroidType;
    private Point[] dVect;
    private long[]  time;
    private long startTime;
    private long curTime;
    ///////////////////////////
    //      CONSTRUCTOR
    ///////////////////////////

    public Asteroid( PointF blockSize) {
        // posX, posY, mass, maxVelocity, minVelocity, drctnVector, shape
        super(blockSize);
        aGen = new AsteroidGenerator();
        asteroidTracker = new int[numAsteroids];
        offSet = new Point[numAsteroids];
        dVect = new Point[numAsteroids];
        time = new long[numAsteroids];
        startTime = System.nanoTime() / 1000000;
        curTime = startTime;
        for(int i = 0; i < numAsteroids; i++){
            asteroidTracker[i] = random.nextInt(9) + 1;
            offSet[i] = randOffset(blockSize);
            dVect[i] = randDirection(offSet[i]);
            time[i] = curTime - startTime;
        }
        // posX, posY, mass, maxVelocity, minVelocity, drctnVector are the parameters
    }

    ///////////////////////////
    //      METHODS
    ///////////////////////////

    public Path draw() {
        shape.rewind();
        curTime = System.nanoTime() / 1000000;
        for(int j = 0; j < numAsteroids; j++){
            time[j] = curTime - startTime;
            currentAsteroid = aGen.which(asteroidTracker[j]);
            if(currentAsteroid != null) {
                shape.moveTo(currentAsteroid[0].x * scalar + offSet[j].x + dVect[j].x *time[j], currentAsteroid[0].y * scalar + offSet[j].y + dVect[j].y *time[j]);
                for (int i = 1; i < currentAsteroid.length; i++) {
                    shape.lineTo(currentAsteroid[i].x * scalar + offSet[j].x + dVect[j].x *time[j], currentAsteroid[i].y * scalar + offSet[j].y + dVect[j].x *time[j]);
                }
            }
        }
        return shape;
    }

    private Point randOffset(PointF blockSize){
        // pick where to generate
        Point tempOffSet = new Point();
        if(random.nextInt(2) == 1){
            tempOffSet.x = random.nextInt((int) (100 * blockSize.y));
            if(random.nextInt(2) == 1){
                tempOffSet.y = scalar;
            }else{
                tempOffSet.y = (int) (100 * blockSize.y - scalar);
            }
        }else{
            tempOffSet.y = random.nextInt((int) (100 * blockSize.x));
            if(random.nextInt(2) == 1){
                tempOffSet.x = scalar;
            }else{
                tempOffSet.x = (int)  (100 * blockSize.x - scalar);
            }
        }
        return tempOffSet;
    }

    private Point randDirection(Point oSet){
        // pick where to generate
        Point tempVect = new Point();
        if(oSet.x >= (int)  (100 * blockSize.x - scalar) || oSet.y >= (int) (100 * blockSize.y - scalar)){
            if( oSet.x >= (int)  (100 * blockSize.x - scalar)){
                tempVect.x = -random.nextInt(scalar);
            }else{
                tempVect.x = random.nextInt(scalar);
            }
            if( oSet.x >= (int)  (100 * blockSize.x - scalar)){
                tempVect.y = -random.nextInt(scalar);
            }else{
                tempVect.y = random.nextInt(scalar);
            }
        }else{
            tempVect.x = random.nextInt(scalar);
            tempVect.y = random.nextInt(scalar);
        }
        return tempVect;
    }

}
