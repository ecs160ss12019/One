package com.example.asteroids;

// Brian Coe

import android.graphics.Point;

import java.util.Random;
import java.util.Vector;
import android.graphics.PointF;

public class AsteroidManager {

    ///////////////////////////
    //      VARIABLES
    ///////////////////////////
    public Vector<Asteroid> asteroidTracker = new Vector<Asteroid>();
    private int numAsteroids = 5;
    private PointF blockSize;
    private static int resY;
    private static int resX;
    private boolean remove = false;

    ///////////////////////////
    //      CONSTRUCTOR
    ///////////////////////////

    public AsteroidManager(PointF blockSize) {
        // posX, posY, mass, maxVelocity, minVelocity, drctnVector, shape
        this.blockSize = blockSize;
        resY = (int) (100 * blockSize.y);
        resX = (int) (100 * blockSize.x);
        for(int i = 0; i < numAsteroids; i++){

            asteroidTracker.add(new Asteroid(blockSize));
        }
        updateAsteroids();
    }

    ///////////////////////////
    //      METHODS
    ///////////////////////////

    public void updateAsteroids() {
        Vector<Asteroid> temp = new Vector<Asteroid>();
        for(Asteroid ast:asteroidTracker){
            if(ast.curTime -ast.startTime < 250)
                ast.isHit = false;
            if(ast.isHit){
                destroyAsteroid(ast, temp);
            }
            if(ast.reDraw)
                ast.initAsteroid(resX, resY);
            if(!remove){
                temp.addElement(ast);
                ast.setNewPos();
            }
            remove = false;
        }
        asteroidTracker = temp;
    }

    public void destroyAsteroid(Asteroid ast, Vector<Asteroid> temp){
        if(ast.asteroidType <= 3){
            temp.add(new Asteroid(blockSize));
            temp.lastElement().setAsteroid(new Random().nextInt(3)+4);
            temp.lastElement().offSet = ast.offSet;
//            temp.lastElement().randDirection(temp.lastElement().offSet);
            temp.add(new Asteroid(blockSize));
            temp.lastElement().setAsteroid(new Random().nextInt(3)+4);
            temp.lastElement().offSet = ast.offSet;
 //           temp.lastElement().randDirection(temp.lastElement().offSet);
        }
        else if(ast.asteroidType <= 6){
            temp.add(new Asteroid(blockSize));
            temp.lastElement().setAsteroid(new Random().nextInt(3)+7);
            temp.lastElement().offSet = ast.offSet;
//            temp.lastElement().randDirection(temp.lastElement().offSet);
            temp.add(new Asteroid(blockSize));
            temp.lastElement().setAsteroid(new Random().nextInt(3)+7);
            temp.lastElement().offSet = ast.offSet;
//            temp.lastElement().randDirection(temp.lastElement().offSet);
        }
        remove = true;
    }

    public void updateScore(){

    }

    public void increaseDifficulty(){

    }

}