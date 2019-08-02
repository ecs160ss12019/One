package com.example.asteroids;

public class HighScores {
    protected int numOfScores;
    protected int[] scoreList;

    public HighScores() {
        this.numOfScores = 3;
        this.scoreList = new int[numOfScores];
    }

    public HighScores(int numOfScores, int[] scoreList) {
        this.numOfScores = numOfScores;
        this.scoreList = scoreList;
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