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
    private boolean firstRun = true;
    private Point[] dVect;
    private Point[] newPos;
    private long[]  time;
    private long[] startTime;
    private long[] curTime;
    ///////////////////////////
    //      CONSTRUCTOR
    ///////////////////////////

    public Asteroid( PointF blockSize) {
        // posX, posY, mass, maxVelocity, minVelocity, drctnVector, shape
        super(blockSize);
        aGen = new AsteroidGenerator();
        newPos = new Point[numAsteroids];
        for(int i = 0; i < numAsteroids; i++){
                newPos[i] = new Point();
        }
        asteroidTracker = new int[numAsteroids];
        offSet = new Point[numAsteroids];
        dVect = new Point[numAsteroids];
        time = new long[numAsteroids];
        startTime = new long[numAsteroids];
        curTime = new long[numAsteroids];
        genAsteroid();

        // posX, posY, mass, maxVelocity, minVelocity, drctnVector are the parameters
    }

    ///////////////////////////
    //      METHODS
    ///////////////////////////

    public Path draw() {
        shape.rewind();
        genAsteroid();
        for(int j = 0; j < numAsteroids; j++){
            curTime[j] = System.nanoTime() / 1000000;
            time[j] = curTime[j] - startTime[j];
            currentAsteroid = aGen.which(asteroidTracker[j]);
            if(currentAsteroid != null) {
                newPos[0].x = (int) (currentAsteroid[0].x * scalar + offSet[j].x + dVect[j].x *time[j]/100);
                newPos[0].y = (int) (currentAsteroid[0].y * scalar + offSet[j].y + dVect[j].y *time[j]/100);
                shape.moveTo(newPos[0].x, newPos[0].y);
                for (int i = 1; i < currentAsteroid.length; i++) {
                    newPos[i].x = (int) (currentAsteroid[i].x * scalar + offSet[j].x + dVect[j].x *time[j]/100);
                    newPos[i].y = (int) (currentAsteroid[i].y * scalar + offSet[j].y + dVect[j].y *time[j]/100);
                    shape.lineTo(newPos[i].x, newPos[i].y);
                }
            }
        }
        return shape;
    }

    private void genAsteroid(){
        for(int j = 0; j < numAsteroids; j++){
            boolean reDraw = false;
            if(!firstRun || currentAsteroid != null) {
                currentAsteroid = aGen.which(asteroidTracker[j]);
                for (int i = 0; i < currentAsteroid.length; i++) {
                    if(
                            (int) (currentAsteroid[i].x * scalar + offSet[j].x + dVect[j].x *time[j]/100) < 0
                            || (int) (currentAsteroid[i].y * scalar + offSet[j].y + dVect[j].y *time[j]/100) < 0
                            || (int) (currentAsteroid[i].x * scalar + offSet[j].x + dVect[j].x *time[j]/100) > 100 * blockSize.x
                            || (int) (currentAsteroid[i].y * scalar + offSet[j].y + dVect[j].y *time[j]/100) > 100*blockSize.y)
                        reDraw = true;
                }
            }
            if(firstRun || reDraw){
                startTime[j] = System.nanoTime() / 1000000;
                curTime[j] = startTime[j];
                asteroidTracker[j] = random.nextInt(9) + 1;
                offSet[j] = randOffset(blockSize);
                dVect[j] = randDirection(offSet[j]);
                time[j] = 0;
            }
        }
        firstRun = false;
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
        if(oSet.x == (int)  (100 * blockSize.x - scalar) || oSet.x == 0){
            if( oSet.x == (int)  (100 * blockSize.x - scalar)){
                tempVect.x = -random.nextInt(scalar) - 1;
            }else{
                tempVect.x = random.nextInt(scalar) + 1;
            }
            if(oSet.y > (100 * blockSize.y - scalar)/2){
                tempVect.y = -random.nextInt(scalar) - 1;
            }else{
                tempVect.y = random.nextInt(scalar) + 1;
            }
        }else{
            if( oSet.y == (int)  (100 * blockSize.y - scalar)){
                tempVect.y = -random.nextInt(scalar) - 1;
            }else{
                tempVect.y = random.nextInt(scalar) + 1;
            }
            if(oSet.x > (100 * blockSize.x - scalar)/2){
                tempVect.x = -random.nextInt(scalar) - 1;
            }else {
                tempVect.x = random.nextInt(scalar) + 1;
            }
        }
        return tempVect;
    }

}
