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

