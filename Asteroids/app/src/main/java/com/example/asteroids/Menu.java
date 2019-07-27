package com.example.asteroids;

import android.graphics.Point;
import android.graphics.PointF;

public class Menu {

    public Button resume;
    public Button newGame;

    public Menu(PointF blockSize) {

        resume = new Button(new Point(50,40), blockSize, "Resume" );

        newGame = new Button(new Point(50, 60), blockSize, "New Game");

    }

}
