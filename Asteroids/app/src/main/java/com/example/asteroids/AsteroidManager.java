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
    private Vector<Asteroid> asteroidTracker = new Vector<Asteroid>();
//    private Asteroid currentAsteroid;
    private int numAsteroids = 10;
 //   private int numHits;
 //   private int asteroidType;
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
        for(int i = 0; i < numAsteroids; i++){
            asteroidTracker.add(new Asteroid());
        }
        for(Asteroid ast:asteroidTracker){
            for(int i = 0; i < ast.newPos.length; i++){
                ast.newPos[i] = new Point();
            }
//            ast.newPos = new Point[numAsteroids];
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
            ast.setNewPos();
                shape.moveTo(ast.newPos[0].x, ast.newPos[0].y);
                for (int i = 1; i < ast.image.length; i++) {
                    shape.lineTo(ast.newPos[i].x, ast.newPos[i].y);
                }
            }
        return shape;
    }

    private void genAsteroid(){
        for(Asteroid ast: asteroidTracker){
            if(ast.reDraw)
                ast.initAsteroid(resX, resY);
        }
    }

}
