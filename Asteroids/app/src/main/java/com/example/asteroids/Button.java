package com.example.asteroids;

import android.graphics.PointF;
import android.graphics.RectF;
import android.util.Log;
import android.view.MotionEvent;

//Simple class that just holds a button's information
public class Button {

    public RectF button;
    public String textBox;

    //The position of the button accounting for screenResolution
    public PointF absPosition;



    public Button(PointF buttonPos, PointF blockSize, String text) {

        textBox = text;

        //Standard button size is 1/10th screen res in both x & y
        PointF size = new PointF(10, 10);
        absPosition = new PointF(buttonPos.x * blockSize.x, buttonPos.y * blockSize.y);

        button = new RectF(buttonPos.x * blockSize.x, buttonPos.y * blockSize.y,
                (buttonPos.x + size.x) * blockSize.x, (buttonPos.y + size.y) * blockSize.y);
    }



}
