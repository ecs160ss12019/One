package com.example.asteroids;

//Martin Petrov

import android.graphics.PointF;
import android.util.Log;

import java.util.Random;

public class PowerUpManager {

    public PowerUpObject powerUpObject;
    private PowerUpObject nullPowerUp;
    private Random rand;
    private PointF blockSize;
    private long lastPowerUpTime;

    private Spaceship spaceship;

    public PowerUpManager(PointF blockSize, Spaceship spaceship) {
        this.spaceship = spaceship;
        rand = new Random();
        this.blockSize = blockSize;
        nullPowerUp = new PowerUpObject(new PointF(130,130),1, blockSize);
        genNewPowerUp();
    }

    private boolean checkLastPowerUpTime() {
        return System.currentTimeMillis() - lastPowerUpTime >= 15000;
    }

    private void genNewPowerUp() {
        lastPowerUpTime = System.currentTimeMillis();
        PointF newPos = new PointF(rand.nextFloat() * 100, rand.nextFloat() * 100);
        rand = new Random();
        this.powerUpObject = new PowerUpObject(newPos, rand.nextInt(3), blockSize);



    }

    public void update() {
        if(powerUpObject.isHit) {
            spaceship.setPowerUp(powerUpObject.powerType);
            powerUpObject = nullPowerUp;
        }

        if (checkLastPowerUpTime())
            genNewPowerUp();
    }
}
