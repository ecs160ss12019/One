package com.example.asteroids;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.graphics.RectF;

/**
 *
 * Contains logic related to Sprite explosion.
 * @author Jose Torres-Vargas
 */
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

    /**
     * Initializes necessary variables.
     * @param numCol: number of columns in sprite sheet.
     * @param numRow: number of rows in sprite sheet.
     * @param resources: project resources
     */
    Explosion(int numCol, int numRow, Resources resources){
        this.numCol = numCol;
        this.numRow = numRow;
        //Start at first frame
        frameToDraw = new Rect(0,0,frameWidth,frameHeight);
        whereToDraw = new RectF(500,500,500+frameWidth, 500+frameHeight);
        bitMap = BitmapFactory.decodeResource(resources, R.drawable.expclear);
        bitMap = Bitmap.createScaledBitmap(bitMap,
                frameWidth*numCol,
                frameHeight*numRow,
                false);
        numFrames = this.numCol*this.numRow;
    }

    /**
     * returns current frame of sprite animation.
     * For example if sprite sheet is 4 x 4 it has
     * 16 sprites or "frames". Method ensures that there
     * is a time gap before current frame is updated.
     * @return
     */
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

    /**
     * position explosion on screen
     * @param x: x position in terms of resolution
     * @param y: y position in terms of resolution
     */
    void placeExplosion(float x, float y){
        whereToDraw.set(x,y,x+frameWidth, y+frameHeight);
    }

    /**
     * Allow for explosion to be called again later.
     * Resets variables.
     */
    void resetExplosion(){
        currentRow = 0;
        currentCol = 0;
        frameToDraw.top = 0;
        frameToDraw.bottom = frameToDraw.top + frameHeight;
        currentFrame = 0;
    }
}
