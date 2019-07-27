package com.example.asteroids;

import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.PointF;

public class UFOManBuilder {
    int maxUFO;
    int wantActive;
    long timeOut;
    long gapTime;
    Resources resources;
    Point resolution;
    ProjectileManager projectileManager;
    SFXManager sfxManager;
    PointF bSize;


    public UFOManBuilder(Point resolution){
        this.resolution = resolution;
    }

    public UFOManBuilder setMaxUFO(int maxUFO){
        this.maxUFO = maxUFO;
        return this;
    }

    public UFOManBuilder wantActive(int wantActive){
        this.wantActive = wantActive;
        return this;
    }

    public  UFOManBuilder setTimeOut(long timeOut){
        this.timeOut = timeOut;
        return this;
    }

    public UFOManBuilder setSpawnGap(long gapTime){
        this.gapTime = gapTime;
        return this;
    }
    public UFOManBuilder setResources(Resources resources){
        this.resources = resources;
        return this;
    }

    public UFOManBuilder setProjectileManager(ProjectileManager projectileManager){
        this.projectileManager = projectileManager;
        return this;
    }

    public UFOManBuilder setSFXManager(SFXManager sfxManager){
        this.sfxManager = sfxManager;
        return this;
    }

    public UFOManBuilder setBlockSize(PointF blockSize){
        this.bSize = blockSize;
        return this;
    }

    public UFOManager build(){
        return new UFOManager(resolution, bSize, resources, projectileManager,
                sfxManager, maxUFO, wantActive, timeOut, gapTime);
    }

}