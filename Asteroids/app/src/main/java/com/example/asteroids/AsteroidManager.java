package com.example.asteroids;

// Brian Coe

import android.graphics.Point;
import java.util.Vector;
import android.graphics.Path;
import android.graphics.PointF;

public class AsteroidManager {

    ///////////////////////////
    //      VARIABLES
    ///////////////////////////
   public Vector<Asteroid> asteroidTracker = new Vector<Asteroid>();
    private int numAsteroids = 10;
    private static int resY;
    private static int resX;

    ///////////////////////////
    //      CONSTRUCTOR
    ///////////////////////////

    public AsteroidManager(PointF blockSize) {
        // posX, posY, mass, maxVelocity, minVelocity, drctnVector, shape
        resY = (int) (100 * blockSize.y);
        resX = (int) (100 * blockSize.x);
        for(int i = 0; i < numAsteroids; i++){

            asteroidTracker.add(new Asteroid(blockSize));
        }
        for(Asteroid ast:asteroidTracker){
            ast.offSet = new Point();
            ast.dVect = new Point();
            ast.time = 0;
            ast.startTime = 0;
            ast.curTime = 0;
        }
        updateAsteroids();
        // posX, posY, mass, maxVelocity, minVelocity, drctnVector are the parameters
    }

    ///////////////////////////
    //      METHODS
    ///////////////////////////

    public void updateAsteroids() {
             for(Asteroid ast:asteroidTracker){
                 if(ast.reDraw)
                     ast.initAsteroid(resX, resY);
                    ast.setNewPos();
        }
    }

}
