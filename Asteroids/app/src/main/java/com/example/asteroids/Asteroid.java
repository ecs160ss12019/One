package com.example.asteroids;

import android.graphics.Point;
import java.util.Random;

public class Asteroid {
    private AsteroidGenerator aGen = new AsteroidGenerator();
    public Point dVect;
    public long  time;
    public long startTime;
    public long curTime;
    public Point offSet;
    public int asteroidType;
    public boolean reDraw = true;
    private int maxVertices = 10;
    private int scalar = 15;
    private int resX;
    private int resY;
    public Point[] image;
    public Point[] newPos = new Point[maxVertices];

    public void setAsteroid(int asteroidType){
        this.asteroidType = asteroidType;
        this.image = aGen.which(this.asteroidType);
    }
    public void checkReDraw(){
        for (int i = 0; i < image.length; i++) {
            if(newPos[i].x < -resX/4 || newPos[i].y < -resY/4
                    || newPos[i].x > resX + resX/4 || newPos[i].y > resY + resY/4)
                reDraw = true;
        }
    }
    
    public void setNewPos(){
        checkReDraw();
        curTime = System.nanoTime() / 1000000;
        time = curTime - startTime;
        if(image != null) {
            newPos[0].x = (int) (image[0].x * scalar + offSet.x + dVect.x *time/100);
            newPos[0].y = (int) (image[0].y * scalar + offSet.y + dVect.y *time/100);
            for (int i = 1; i < image.length; i++) {
                newPos[i].x = (int) (image[i].x * scalar + offSet.x + dVect.x *time/100);
                newPos[i].y = (int) (image[i].y * scalar + offSet.y + dVect.y *time/100);
            }
        }
    }

    public void initAsteroid(int resX, int resY){
        reDraw = false;
        this.resX = resX;
        this.resY = resY;
        startTime = System.nanoTime() / 1000000;
        curTime = startTime;
        setAsteroid(new Random().nextInt(9) + 1);
        offSet = randOffset();
        dVect = randDirection(offSet);
        time = 0;
    }
    private Point randOffset(){
        // pick where to generate
        Point tempOffSet = new Point();
        switch(new Random().nextInt(4)) {
            case 0:
                tempOffSet.x = new Random().nextInt(resX);
                tempOffSet.y = 0;
                break;
            case 1:
                tempOffSet.x = new Random().nextInt(resX);
                tempOffSet.y = resY;
                break;
            case 2:
                tempOffSet.x = 0;
                tempOffSet.y = new Random().nextInt(resY);
                break;
            case 3:
                tempOffSet.x = resX;
                tempOffSet.y = new Random().nextInt(resY);
                break;
        }
        return tempOffSet;
    }

    private Point randDirection(Point oSet){
        // pick where to generate
        Point tempVect = new Point();
        if(oSet.x <= resX/2){
            tempVect.x = new Random().nextInt(resY / (scalar*4));
        }else if(oSet.x > resX/2) {
            tempVect.x = -new Random().nextInt(resX / (scalar*4));
        }
        if(oSet.y <= resY/2) {
            tempVect.y = new Random().nextInt(resY / (scalar*5));
        }else if(oSet.y > resY/2) {
            tempVect.y = -new Random().nextInt(resX / (scalar*5));
        }
        return tempVect;
    }
}
