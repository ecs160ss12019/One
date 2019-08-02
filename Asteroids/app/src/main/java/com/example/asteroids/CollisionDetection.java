package com.example.asteroids;

import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Region;

public class CollisionDetection {
    ///////////////////////////
    //      VARIABLES
    ///////////////////////////
    private Region clip;
    private Region r1;
    private Region r2;

    ///////////////////////////
    //      CONSTRUCTOR
    ///////////////////////////
    public CollisionDetection(PointF blockSize) {
        this.clip = new Region(0, 0, (int)(blockSize.x * 100), (int)(blockSize.y * 100));
        r1 = new Region();
        r2 = new Region();
    }


    ///////////////////////////
    //      METHODS
    ///////////////////////////

    // binary comparison of 2 regions, "Does A contain B?"
    public boolean checkBinaryCollision(Path p1, Path p2) {
        r1.setEmpty();
        r2.setEmpty();

        r1.setPath(p1, clip);
        r2.setPath(p2, clip);

        return !r1.quickReject(r2) && r1.op(r2, Region.Op.INTERSECT);
    }
}
