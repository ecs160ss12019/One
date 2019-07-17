package com.example.asteroids;

import android.graphics.Point;

public class Asteroid {
    private AsteroidGenerator aGen;
    public Point dVect;
    public long  time;
    public long startTime;
    public long curTime;
    public Point offSet;
    public int asteroidType;
    public Point[] image;
    public Point[] newPos;

    public void setAsteroid(int asteroidType){
        this.asteroidType = asteroidType;
        this.image = aGen.which(this.asteroidType);
    }

}
