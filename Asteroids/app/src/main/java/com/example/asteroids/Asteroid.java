package com.example.asteroids;

import android.graphics.Point;
import android.graphics.PointF;

import java.util.Random;

public class Asteroid extends MovableObject {
    private AsteroidGenerator aGen = new AsteroidGenerator();
    public Point dVect;
    public long  time;
    public long startTime;
    public long curTime;
    public Point offSet;
    public int asteroidType;
    public boolean reDraw = true;
    private int scalar = 15;
    private int resX;
    private int resY;
    public Point[] image;


    public Asteroid(PointF blockSize){
        super(blockSize);
        offSet = new Point();
        dVect = new Point();
        time = 0;
        startTime = 0;
        curTime = 0;
        projectileOwner = 3;

    }

    public void setAsteroid(int asteroidType){
        this.asteroidType = asteroidType;
        image = aGen.which(this.asteroidType);
        shapeCoords = new PointF[image.length];
        for(int i = 0; i < shapeCoords.length; i++){
            shapeCoords[i] = new PointF();
        }
    }
    private void checkReDraw(){
        for (int i = 0; i < image.length; i++) {
            if(shapeCoords[i].x*blockSize.x < -resX/4 || shapeCoords[i].y*blockSize.y < -resY/4
                    || shapeCoords[i].x*blockSize.x > resX + resX/4 || shapeCoords[i].y*blockSize.y > resY + resY/4)
                reDraw = true;
        }
    }

    public void setNewPos(){

        checkReDraw();
        curTime = System.nanoTime() / 1000000;
        time = curTime - startTime;
        if(image != null) {
            shapeCoords[0].x = (image[0].x * scalar + offSet.x + dVect.x *time/100)/blockSize.x;
            shapeCoords[0].y = (image[0].y * scalar + offSet.y + dVect.y *time/100)/blockSize.y;
            for (int i = 1; i < image.length; i++) {
                shapeCoords[i].x = (image[i].x * scalar + offSet.x + dVect.x *time/100)/blockSize.x;
                shapeCoords[i].y = (image[i].y * scalar + offSet.y + dVect.y *time/100)/blockSize.y;
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
                tempOffSet.set(new Random().nextInt(resX), 0);
                break;
            case 1:
                tempOffSet.set(new Random().nextInt(resX), resY);
                break;
            case 2:
                tempOffSet.set(0, new Random().nextInt(resY));
                break;
            case 3:
                tempOffSet.set(resX, new Random().nextInt(resY));
                break;
        }
        return tempOffSet;
    }

    public Point randDirection(Point oSet){
        // pick where to generate
        Point tempVect = new Point();
        if(oSet.x <= resX/2){
            tempVect.x = new Random().nextInt(Math.abs(resY) / (scalar*5));
        }else if(oSet.x > resX/2) {
            tempVect.x = -new Random().nextInt(Math.abs(resX) / (scalar*5));
        }
        if(oSet.y <= resY/2) {
            tempVect.y = new Random().nextInt(Math.abs(resY) / (scalar*6));
        }else if(oSet.y > resY/2) {
            tempVect.y = -new Random().nextInt(Math.abs(resX) / (scalar*6));
        }
        return tempVect;
    }


}