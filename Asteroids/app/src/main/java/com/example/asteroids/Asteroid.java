package com.example.asteroids;

import android.graphics.Point;

public class Asteroid {
    private AsteroidGenerator aGen = new AsteroidGenerator();
    public Point dVect;
    public long  time;
    public long startTime;
    public long curTime;
    public Point offSet;
    public int asteroidType;
    private int maxVertices = 10;
    public Point[] image;
    public Point[] newPos = new Point[maxVertices];

    public void setAsteroid(int asteroidType){
        this.asteroidType = asteroidType;
        this.image = aGen.which(this.asteroidType);
    }

}
