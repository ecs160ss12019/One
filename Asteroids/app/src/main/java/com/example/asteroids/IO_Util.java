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

    public int[] readFile() {
        FileInputStream fin;
        ObjectInputStream oin;
        int[] scoreList = new int[3];

        try {
            fin = context.openFileInput(fileName);
            oin = new ObjectInputStream(fin);
            scoreList = (int[]) oin.readObject();
            fin.close();
            oin.close();
            return (int[]) oin.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return scoreList;
    }

    public void writeFile(int[] scoreList) {
        FileOutputStream fout;
        ObjectOutputStream oos;

        try {
            fout = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            oos = new ObjectOutputStream(fout);
            oos.writeObject(scoreList);
            Log.d("FIO: ", "Write Successful");
            Log.d("FIO: ", "Wrote " + scoreList[0] + ", " + scoreList[1] + ", " + scoreList[2]);
            //           fout.close();
            oos.close();
        } catch (Exception e) {
            Log.d("You fucked up", "tes");
            e.printStackTrace();
        }

    }
}
