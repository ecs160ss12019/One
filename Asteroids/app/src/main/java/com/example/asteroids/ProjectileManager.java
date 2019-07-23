package com.example.asteroids;

// Brian Coe

import android.graphics.PointF;
import android.util.Log;

import java.util.Vector;

public class ProjectileManager {
    public Vector<Projectile> projectileVector;
    private PointF blockSize;
    public ProjectileManager(PointF blockSize){
        projectileVector = new Vector<Projectile>(0);
        this.blockSize = blockSize;

    }

    public void fire(PointF speed, PointF direction, PointF Position, long fps){
        projectileVector.addElement(new Projectile(this.blockSize, speed, direction, Position, fps));
    }

    public void updateProjectiles(long fps){
        Vector<Projectile> temp = new Vector<Projectile>(0);
        if(!projectileVector.isEmpty())
        for(Projectile p: projectileVector){
            p.update(fps);
            if(System.nanoTime() / 1000000 - p.startTime < 2000){
                temp.addElement(p);
            }
        }
        projectileVector = temp;
    }
}
