package com.example.asteroids;

// Brian Coe

import android.graphics.Path;
import android.graphics.Point;
import java.util.Random;
import java.util.Vector;

import android.graphics.PointF;

public class AsteroidManager extends MovableObject {

    ///////////////////////////
    //      VARIABLES
    ///////////////////////////
    private int scalar = 15;
    Random random = new Random();
    private AsteroidGenerator aGen;
    private Vector<Asteroid> asteroidTracker;
    private Point[] currentAsteroid;
    private int numAsteroids = 10;
    private int numHits;
    private int asteroidType;
    private static int resY;
    private static int resX;
    private boolean firstRun = true;

    ///////////////////////////
    //      CONSTRUCTOR
    ///////////////////////////

    public AsteroidManager(PointF blockSize) {
        // posX, posY, mass, maxVelocity, minVelocity, drctnVector, shape
        super(blockSize);
        resY = (int) (100 * blockSize.y);
        resX = (int) (100 * blockSize.x);
        aGen = new AsteroidGenerator();
        for(int i = 0; i < numAsteroids; i++){
                newPos[i] = new Point();
            asteroidTracker.add(new Asteroid());
        }
        for(Asteroid ast:asteroidTracker){
            for(int i = 0; i < numAsteroids; i++){
                newPos[i] = new Point();}
        }
            ast.newPos = new Point[numAsteroids];
            ast.offSet = new Point();
            ast.dVect = new Point();
            ast.time = 0;
            ast.startTime = 0;
            ast.curTime = 0;
        }
        genAsteroid();
        // posX, posY, mass, maxVelocity, minVelocity, drctnVector are the parameters
    }

    ///////////////////////////
    //      METHODS
    ///////////////////////////

    public Path draw() {
        shape.rewind();
        genAsteroid();
        for(Asteroid ast:asteroidTracker){
            ast.curTime = System.nanoTime() / 1000000;
            ast.time = ast.curTime - ast.startTime;
            currentAsteroid = aGen.which(ast.asteroidType);
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
                            (int) (currentAsteroid[i].x * scalar + offSet[j].x + dVect[j].x *time[j]/100) < -resX/4
                            || (int) (currentAsteroid[i].y * scalar + offSet[j].y + dVect[j].y *time[j]/100) < -resY/4
                            || (int) (currentAsteroid[i].x * scalar + offSet[j].x + dVect[j].x *time[j]/100) > resX + resX/4
                            || (int) (currentAsteroid[i].y * scalar + offSet[j].y + dVect[j].y *time[j]/100) > resY + resY/4)
                        reDraw = true;
                }
            }
            if(firstRun || reDraw){
                startTime[j] = System.nanoTime() / 1000000;
                curTime[j] = startTime[j];
                asteroidTracker[j] = random.nextInt(9) + 1;
                offSet[j] = randOffset();
                dVect[j] = randDirection(offSet[j]);
                time[j] = 0;
            }
        }
        firstRun = false;
    }

    private Point randOffset(){
        // pick where to generate
        Point tempOffSet = new Point();
        int rand = random.nextInt(4);
        switch(rand) {
            case 0:
                tempOffSet.x = random.nextInt(resX);
                tempOffSet.y = 0;
                break;
            case 1:
                tempOffSet.x = random.nextInt(resX);
                tempOffSet.y = resY;
                break;
            case 2:
                tempOffSet.x = 0;
                tempOffSet.y = random.nextInt(resY);
                break;
            case 3:
                tempOffSet.x = resX;
                tempOffSet.y = random.nextInt(resY);
                break;
        }
        return tempOffSet;
    }

    private Point randDirection(Point oSet){
        // pick where to generate
        Point tempVect = new Point();
        if(oSet.x <= resX/2){
            tempVect.x = random.nextInt(resY / (scalar*4));
        }else if(oSet.x > resX/2) {
            tempVect.x = -random.nextInt(resX / (scalar*4));
        }
        if(oSet.y <= resY/2) {
            tempVect.y = random.nextInt(resY / (scalar*5));
        }else if(oSet.y > resY/2) {
            tempVect.y = -random.nextInt(resX / (scalar*5));
        }

        return tempVect;
    }

}
