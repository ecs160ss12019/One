package com.example.asteroids;

import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.RectF;
import android.graphics.Region;

//Simple class that just holds a button's information
public class Button {

    public RectF box;
    public String textBox;
    public Path shape;
    public PointF dimensions;
    public Region touchBox;
    private Region clip;

    //The position of the button accounting for screenResolution
    public Point pos;


    public Button(Point pos, int xSize, int ySize, PointF blockSize, String text) {
        // set the pixel position of button
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

        this.clip = new Region(0, 0, (int) (blockSize.x * 100), (int) (blockSize.y * 100));
        touchBox = new Region();
        touchBox.setPath(shape, clip);
    }

    public Path draw() {
        return this.shape;
    }
}
