package com.example.asteroids;

import android.content.Context;

import java.io.File;
import java.io.FileOutputStream;

public class IO_Util {
    String fileName;

    public IO_Util(String fileName) {
        this.fileName = fileName;
    }

    public File readFile(Context ctx) {
        File directory = ctx.getFilesDir();
        File file = new File(directory, fileName);
        return file;
    }

    public void writeFile(Context ctx, HighScores scores) {
        String filename = "myfile";
        String fileContents = "Hello world!";
        FileOutputStream outputStream;

        try {
            outputStream = ctx.openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(fileContents.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

class HighScores {
    protected int numOfScores;
    protected int[] scoreList;

    public HighScores() {
        this.numOfScores = 3;
        scoreList = new int[numOfScores];
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
