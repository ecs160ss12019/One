package com.example.asteroids;

import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.RectF;

//Simple class that just holds a button's information
public class Button {

    public RectF button;
    public String textBox;
    public Path image;

    //The position of the button accounting for screenResolution
    public Point pos;



    public Button(Point buttonPos, PointF blockSize) {
        //The button is 1/10th screen res in both x & y
        PointF size = new PointF(10, 10);
        pos = new Point(buttonPos.x * (int)blockSize.x, buttonPos.y * (int)blockSize.y);

        button = new RectF(buttonPos.x * blockSize.x, buttonPos.y * blockSize.y,
                (buttonPos.x + size.x) * blockSize.x, (buttonPos.y + size.y) * blockSize.y);
    }

    public Path draw() {
        this.image.addRect(button, Path.Direction.CW);
        return this.image;
    }


}
