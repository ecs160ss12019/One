package com.example.asteroids;

import android.content.Context;
import android.util.Log;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class IO_Util {
    String fileName;
    Context context;

    public IO_Util(Context context) {
        this.fileName = "scores.dat";
        this.context = context;
    }

    public HighScores readFile() {
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

    public void writeFile(HighScores scores) {
        FileOutputStream fout;
        ObjectOutputStream oos;

        try {
            fout = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            oos = new ObjectOutputStream(fout);
            oos.writeObject(scores);
            Log.d("FIO: ", "Wrote " + context.get);
            oos.close();
            fout.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
