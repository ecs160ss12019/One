package com.example.asteroids;

import android.content.Context;
import android.util.Log;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class IO_Util {

    ///////////////////////////
    //      VARIABLES
    ///////////////////////////

    String fileName;
    Context context;

    ///////////////////////////
    //      CONSTRUCTOR
    ///////////////////////////

    // sets the context to the overall environment to work with system file paths
    public IO_Util(Context context) {
        this.fileName = "scores.dat";
        this.context = context;
    }

    ///////////////////////////
    //      METHODS
    ///////////////////////////

    // This reads in a serialized array representing the high scores
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

    // This writes the serialized array of high scores to the OS file system
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
