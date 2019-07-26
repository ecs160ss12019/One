package com.example.asteroids;

import android.graphics.PointF;

public class Menu {

    public Button resume;
    public Button newGame;

    public Menu(PointF blockSize) {

        resume = new Button(new PointF(50,40), blockSize, "Resume");
        newGame = new Button(new PointF(50, 60), blockSize, "New Game");

    }

}
