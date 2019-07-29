package com.example.asteroids;

//Martin Petrov

import android.graphics.PointF;

import java.util.Random;

public class PowerUpManager {

    public PowerUpObject powerUpObject;
    private Random rand;
    private PointF blockSize;

    public PowerUpManager(PointF blockSize) {
        rand = new Random();
        this.blockSize = blockSize;
        genNewPowerUp();
    }

    private void genNewPowerUp() {
        PointF newPos = new PointF(rand.nextFloat() * 100, rand.nextFloat() * 100);
        powerUpObject = new PowerUpObject(newPos, rand.nextInt(3), blockSize);


    }

    public void update() {

        if(powerUpObject.isHit)
            genNewPowerUp();


    }
}
