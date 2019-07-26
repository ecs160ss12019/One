package com.example.asteroids;

import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Region;

import java.util.ArrayList;

public class CollisionDetection {
    ///////////////////////////
    //      VARIABLES
    ///////////////////////////
    private Region clip;

    ///////////////////////////
    //      CONSTRUCTOR
    ///////////////////////////
    public CollisionDetection(PointF blockSize) {
        this.clip = new Region(0, 0, (int)(blockSize.x * 100), (int)(blockSize.y * 100));
    }


    ///////////////////////////
    //      METHODS
    ///////////////////////////

    // binary comparison of 2 regions, "Does A contain B?"
    public boolean checkBinaryCollision(Path p1, Path p2) {
        Region r1 = new Region();
        Region r2 = new Region();
        r1.setPath(p1, clip);
        r2.setPath(p2, clip);

        if (!r1.quickReject(r2) && r1.op(r2, Region.Op.INTERSECT) ) {
            return true;
        } else {
            return false;
        }
    }
    
}