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

    public void fire(PointF speed, PointF pos1, PointF pos2, float rotation){
        projectileVector.addElement(new Projectile(this.blockSize, speed, pos1, pos2, rotation));
    }

    public void updateProjectiles(long fps){
        Vector<Projectile> temp = new Vector<Projectile>(0);
        if(!projectileVector.isEmpty())
        for(Projectile p: projectileVector){
            p.update(fps);
            if(System.currentTimeMillis() - p.startTime < 2000){
                temp.addElement(p);
            }
        }
        projectileVector = null;
        projectileVector = temp;
    }
}
