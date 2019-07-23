package com.example.asteroids;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.graphics.RectF;

public class Explosion {

    Bitmap bitMap;
    private int frameWidth = 128;
    private int frameHeight = 128;
    private int numCol;
    private int numRow;
    private int currentCol = 0;
    private int currentRow = 0;
    private long lastFrameTime = 0;
    private long frameLenghtMS = 100;
    Rect frameToDraw;
    RectF whereToDraw;
    int currentFrame = 0;
    int numFrames;

    Explosion(int numCol, int numRow, Resources resources){
        this.numCol = numCol;
        this.numRow = numRow;
        //Start at first frame
        frameToDraw = new Rect(0,0,frameWidth,frameHeight);
        whereToDraw = new RectF(500,500,500+frameWidth, 500+frameHeight);
        bitMap = BitmapFactory.decodeResource(resources,R.drawable.expclear);
        bitMap = Bitmap.createScaledBitmap(bitMap,
                frameWidth*numCol,
                frameHeight*numRow,
                false);
        numFrames = this.numCol*this.numRow;
    }

    int getCurrentFrame(){
        long time = System.currentTimeMillis();
        if(time > lastFrameTime + frameLenghtMS){
            lastFrameTime = time;
            currentCol++;
            currentFrame++;
            if(currentCol >= numCol){
                currentCol = 0;
                frameToDraw.top += frameHeight;
                frameToDraw.bottom += frameHeight;
            }
        }
        return currentFrame;
    }

    void placeExplosion(float x, float y){
        whereToDraw.set(x,y,x+frameWidth, y+frameHeight);
    }

    void resetExplosion(){
        currentRow = 0;
        currentCol = 0;
        frameToDraw.top = 0;
        frameToDraw.bottom = frameToDraw.top + frameHeight;
        currentFrame = 0;
    }
}
