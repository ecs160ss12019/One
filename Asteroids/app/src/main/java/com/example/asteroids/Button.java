package com.example.asteroids;

import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.RectF;

//Simple class that just holds a button's information
public class Button {

    public RectF box;
    public String textBox;
    public Path shape;
    public PointF dimensions;

    //The position of the button accounting for screenResolution
    public Point pos;


    public Button(Point pos, int xSize, int ySize, PointF blockSize, String text) {

        // set position of button in terms of blockSize
        //pos = new Point(buttonPos.x * (int)blockSize.x, buttonPos.y * (int)blockSize.y);
        //pos = new Point(buttonPos.x, buttonPos.y);
        this.pos = pos;

        // set dimensions of button in terms of blockSize
        dimensions = new PointF(xSize * blockSize.x, ySize * blockSize.y);

        // generate rectangle corresponding to position and dimensions
        box = new RectF(pos.x, pos.y, (pos.x + dimensions.x), (pos.y + dimensions.y));

        // now create path us
        shape = new Path();
        shape.addRect(box, Path.Direction.CW);

        // give the button a text field
        textBox = text;

    }

    public Path draw() {
        return this.shape;
    }
}
