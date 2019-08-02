package com.example.asteroids;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class IO_Util {
    String fileName;

    public IO_Util() {
        fileName = "scores.dat";
    }

    public HighScores readFile(Context context) {
        FileInputStream fin;
        ObjectInputStream oin;

        try {
            fin = context.openFileInput(fileName);
            oin = new ObjectInputStream(fin);
            oin.close();
            fin.close();
            return (HighScores) oin.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public void writeFile(Context context, HighScores scores) {
        FileOutputStream fout;
        ObjectOutputStream oos;

        try {
            fout = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            oos = new ObjectOutputStream(fout);
            oos.writeObject(scores);
            oos.close();
            fout.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
