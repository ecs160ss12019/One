package com.example.asteroids;

// Brian Coe

import android.graphics.PointF;

import java.util.Vector;

public class ProjectileManager {
    private Vector<Projectile> projectileVector;
    private PointF blockSize;
    public ProjectileManager(PointF blockSize){
        projectileVector = new Vector<Projectile>();
        this.blockSize = blockSize;

    }

    public void fire(PointF speed, PointF direction, PointF Position, long fps){
        projectileVector.add(new Projectile(blockSize, speed, direction, Position, fps));
    }

    public void updateProjectiles(long fps){
        for(Projectile p: projectileVector){
            if(p.startTime - System.nanoTime() / 1000000 < 10)
                p.update(fps);
            else
                projectileVector.remove(p);
        }

    }
}
