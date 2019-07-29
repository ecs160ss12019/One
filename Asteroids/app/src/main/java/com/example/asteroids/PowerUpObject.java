package com.example.asteroids;

//Martin Petrov

import android.graphics.Color;
import android.graphics.PointF;


public class PowerUpObject extends MovableObject {

    public PointF position;
    public PowerState powerType;

    public PowerUpObject(PointF position, int powerUpType, PointF blockSize) {
        super(blockSize);
        createPowerUp(powerUpType);

        this.position = position;
        shapeCoords = new PointF[5];
        genShape();


    }

    private void createPowerUp(int type) {

        switch (type) {
            case 0:
                powerType = new BurstFirePowerState();
                paint.setColor(Color.RED);

            case 1:
                powerType = new ExtraLifePowerState();
                paint.setColor(Color.GREEN);
            case 2:
                powerType = new ShieldPowerState();
                paint.setColor(Color.BLUE);
        }

    }

    private void genShape() {
        shapeCoords[0] = new PointF(position.x,position.y);
        shapeCoords[1] = new PointF(position.x + 3,position.y);
        shapeCoords[2] = new PointF(position.x + 3,position.y + 5);
        shapeCoords[3] = new PointF(position.x,position.y + 5);
        shapeCoords[4] = new PointF(position.x,position.y);
    }

}
