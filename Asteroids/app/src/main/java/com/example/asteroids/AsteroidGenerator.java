package com.example.asteroids;

import android.graphics.Point;
import android.graphics.PointF;

public class AsteroidGenerator {
    private Point[] ab1 = new Point[7];
    private Point[] ab2 = new Point[8];
    private Point[] ab3 = new Point[5];
    private Point[] am1 = new Point[7];
    private Point[] am2 = new Point[5];
    private Point[] am3 = new Point[5];
    private Point[] as1 = new Point[4];
    private Point[] as2 = new Point[5];
    private Point[] as3 = new Point[6];


    public AsteroidGenerator() {
        populate();
    }

    public Point[] which(int asteroidType){
        switch(asteroidType){
            case 1:
                return ab1;
            case 2:
                return ab2;
            case 3:
                return ab3;
            case 4:
                return am1;
            case 5:
                return am2;
            case 6:
                return am3;
            case 7:
                return as1;
            case 8:
                return as2;
            default:
                return as3;
        }
    }

    private void populate() {
        ab1[0] = new Point(0,0);
        ab1[1] = new Point(5,1);
        ab1[2] = new Point(7,6);
        ab1[3] = new Point(4,5);
        ab1[4] = new Point(5,8);
        ab1[5] = new Point(1,7);
        ab1[6] = new Point(2,3);

        ab2[0] = new Point(2,0);
        ab2[1] = new Point(4,1);
        ab2[2] = new Point(6,0);
        ab2[3] = new Point(8,2);
        ab2[4] = new Point(7,6);
        ab2[5] = new Point(4,5);
        ab2[6] = new Point(1,6);
        ab2[7] = new Point(0,2);

        ab3[0] = new Point(4,0);
        ab3[1] = new Point(8,1);
        ab3[2] = new Point(7,7);
        ab3[3] = new Point(3,3);
        ab3[4] = new Point(0,4);

        am1[0] = new Point(0,1);
        am1[1] = new Point(2,2);
        am1[2] = new Point(1,0);
        am1[3] = new Point(4,1);
        am1[4] = new Point(4,3);
        am1[5] = new Point(3,4);
        am1[6] = new Point(1,4);

        am2[0] = new Point(0,0);
        am2[1] = new Point(3,1);
        am2[2] = new Point(2,2);
        am2[3] = new Point(4,4);
        am2[4] = new Point(1,5);

        am3[0] = new Point(0,2);
        am3[1] = new Point(2,0);
        am3[2] = new Point(4,1);
        am3[3] = new Point(3,4);
        am3[4] = new Point(1,4);

        as1[0] = new Point(0,0);
        as1[1] = new Point(3,1);
        as1[2] = new Point(2,2);
        as1[3] = new Point(0,2);

        as2[0] = new Point(0,0);
        as2[1] = new Point(2,0);
        as2[2] = new Point(3,3);
        as2[3] = new Point(0,2);
        as2[4] = new Point(1,1);

        as3[0] = new Point(0,1);
        as3[1] = new Point(1,0);
        as3[2] = new Point(3,2);
        as3[3] = new Point(2,2);
        as3[4] = new Point(1,3);
        as3[5] = new Point(1,2);
    }
}
