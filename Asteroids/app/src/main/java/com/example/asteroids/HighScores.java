package com.example.asteroids;

import android.graphics.Path;
import android.graphics.PointF;

public class HighScores {
    protected int numOfScores;
    protected int[] scoreList;
    protected Path[] scoreBoxes;

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
        float yStart = blockSize.y * 20;
        float xOffset = blockSize.x * 25;
        float yOffset = blockSize.y * 20;

        // generate the rect path objects
        for (int i = 0; i < numOfScores; i++) {
            scoreBoxes[i] = new Path();
            scoreBoxes[i].addRect(xStart, yStart, xStart + xOffset, yStart + yOffset, Path.Direction.CW);
            yStart += yOffset;
        }


    }

    public void addAScore(int score) {
        for (int i = 0; i < numOfScores; i++) {
            if (score > scoreList[i]) {
                int temp = scoreList[i];
                scoreList[i] = score;
                if (i > 0)
                    scoreList[i - 1] = temp;
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