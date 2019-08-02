package com.example.asteroids;

import android.graphics.Path;
import android.graphics.PointF;

import java.io.Serializable;

public class HighScores implements Serializable {

    ///////////////////////////
    //      VARIABLES
    ///////////////////////////

    protected int numOfScores;
    protected int[] scoreList;
    protected Path[] scoreBoxes;
    protected float xStart;
    protected float yStart;
    protected float xOffset;
    protected float yOffset;

    ///////////////////////////
    //      CONSTRUCTOR
    ///////////////////////////

    public HighScores(PointF blockSize) {
        this.numOfScores = 3;
        this.scoreList = new int[numOfScores];
        this.scoreBoxes = new Path[numOfScores];

        // initialize scores to zero in case of nullPointer
        for (int i = 0; i < numOfScores; i++) {
            scoreList[i] = 0;
        }


        // create rects that have positions on screen to aid in drawing
        //      scores in the correct locations
        float xStart = blockSize.x * 65;
        float yStart = blockSize.y * 30;
        float xOffset = blockSize.x * 25;
        float yOffset = blockSize.y * (float) 16.667;

        // generate the rect path objects
        for (int i = 0; i < numOfScores; i++) {
            scoreBoxes[i] = new Path();
            scoreBoxes[i].addRect(xStart, yStart + (yOffset * i), xStart + xOffset,
                    yStart + (yOffset * (i + 1)), Path.Direction.CW);
        }


    }

    ///////////////////////////
    //      METHODS
    ///////////////////////////

    // Add test a single score to see if it is in the top 3 of high scores and eject the lowest one
    public void addAScore(int score) {
        boolean added = false;
        boolean contained = false;

        for (int i = 0; i < numOfScores; i++) {
            if (scoreList[i] == score)
                contained = true;
        }

        for (int i = 0; i < numOfScores; i++) {
            if (score > scoreList[i] && !added && !contained) {
                int temp = scoreList[i];
                scoreList[i] = score;
                added = true;
                if (i < numOfScores - 1)
                    scoreList[i + 1] = temp;
            }
        }
    }

    public HighScores(int[] arr) {
        this.numOfScores = arr.length;
        scoreList = arr;
    }

    public void addScoreList(int[] arr) {
        this.scoreList = arr;
    }

    public int[] getScores() {
        return this.scoreList;
    }

}