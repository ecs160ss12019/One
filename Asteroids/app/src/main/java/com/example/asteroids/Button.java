package com.example.asteroids;

import android.graphics.PointF;
import android.graphics.RectF;

public class Button {

    public RectF button;
    protected PointF size;


    public Button(PointF buttonPos, PointF blockSize) {

        //Standard button size is 1/10th screen res in both x & y
        size = new PointF(10, 10);

        button = new RectF(buttonPos.x * blockSize.x, buttonPos.y * blockSize.y,
                (buttonPos.x + size.x) * blockSize.x, (buttonPos.y + size.y) * blockSize.y);
    }

    public void onClick(float x, float y) {



    }

}
