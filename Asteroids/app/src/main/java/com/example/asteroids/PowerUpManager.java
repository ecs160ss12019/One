package com.example.asteroids;

//Martin Petrov

/* This class is in charge of spawning a power up object every 20 seconds.
    Unlike the other manager classes, it only contains a single instance of an object
    as only 1 object ever needs to appear on the screen.

    All methods are private except for update() which just continously checks if the object
    was detected as hit by collision detection.

* */

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

    //Checks the last time the powerup was created. If it is greater than 20 seconds, it returns true
    private boolean checkLastPowerUpTime() {
        return System.currentTimeMillis() - lastPowerUpTime >= 20000;
    }

    private void genNewPowerUp() {
        lastPowerUpTime = System.currentTimeMillis();
        PointF newPos = new PointF(rand.nextFloat() * 90, rand.nextFloat() * 90);
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
