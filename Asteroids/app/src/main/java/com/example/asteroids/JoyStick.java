package com.example.asteroids;

import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PointF;
import android.view.MotionEvent;

public class JoyStick {

    ///////////////////////////
    //      VARIABLES
    ///////////////////////////
    private PointF baseCenter = new PointF();
    private PointF hatCenter = new PointF();
    ;
    private PointF stickPosition = new PointF();
    ;
    private Point resolution;
    private PointF blockSize;
    private float baseRadius;
    private float hatRadius;

    ///////////////////////////
    //      CONSTRUCTOR
    ///////////////////////////

    public JoyStick(PointF blockSize) {
        this.blockSize = blockSize;
        this.baseCenter.x = (50 * blockSize.x);
        this.baseCenter.y = (50 * blockSize.y);
        this.hatCenter.x = (50 * blockSize.x);
        this.hatCenter.y = (50 * blockSize.y);
        this.resolution.x = (int) blockSize.x * 100;
        this.resolution.y = (int) blockSize.y * 100;
        this.baseRadius = (int) (20 * blockSize.x);
        this.hatRadius = (int) (8 * blockSize.x);

        this.stickPosition.x = 0;
        this.stickPosition.y = 0;
    }

    ///////////////////////////
    //      METHODS
    ///////////////////////////
    @Override
    public boolean onTouchEvent(MotionEvent e) {
        if (e.getAction() != e.ACTION_UP) {
            // read the stick's current position
            stickPosition.x = e.getX();
            stickPosition.y = e.getX();
            float displacement = (float) Math.sqrt(Math.pow((stickPosition.x - baseCenter.x), 2) + Math.pow((stickPosition.y - baseCenter.y), 2));

            if (displacement < baseRadius) {
                // TODO: case for drawing when hat does't need constraining to base radius
                hatCenter = stickPosition;
                draw();
            } else {
                // TODO: case for drawing when hat needs constraining to base radius
                float ratio = baseRadius / displacement;
                float constrainedX = baseCenter.x + (stickPosition.x - baseCenter.x) * ratio;
                float constrainedY = baseCenter.y + (stickPosition.y - baseCenter.y) * ratio;
                stickPosition.x = constrainedX;
                stickPosition.y = constrainedY;
                draw();
            }
        }
        return true;
    }

    /*
        public void draw(float newX, float newY) {
            if (getHolder().getSurface().isValid()) {
                Canvas myCanvas = this.getHolder().lockCanvas(); //Stuff to draw
                Paint colors = new Paint();
                myCanvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR); // Clear the BG

                //First determine the sin and cos of the angle that the touched point is at relative to the baseCenter of the joystick
                float hypotenuse = (float) Math.sqrt(Math.pow(newX - centerX, 2) + Math.pow(newY - centerY, 2));
                float sin = (newY - centerY) / hypotenuse; //sin = o/h
                float cos = (newX - centerX) / hypotenuse; //cos = a/h

                //Draw the base first before shading
                colors.setARGB(255, 100, 100, 100);
                myCanvas.drawCircle(centerX, centerY, baseRadius, colors);
                for (int i = 1; i <= (int) (baseRadius / ratio); i++) {
                    colors.setARGB(150 / i, 255, 0, 0); //Gradually decrease the shade of black drawn to create a nice shading effect
                    myCanvas.drawCircle(newX - cos * hypotenuse * (ratio / baseRadius) * i,
                            newY - sin * hypotenuse * (ratio / baseRadius) * i, i * (hatRadius * ratio / baseRadius), colors); //Gradually increase the size of the shading effect
                }

                //Drawing the joystick hat
                for (int i = 0; i <= (int) (hatRadius / ratio); i++) {
                    colors.setARGB(255, (int) (i * (255 * ratio / hatRadius)), (int) (i * (255 * ratio / hatRadius)), 255); //Change the joystick color for shading purposes
                    myCanvas.drawCircle(newX, newY, hatRadius - (float) i * (ratio) / 2, colors); //Draw the shading for the hat
                }

                getHolder().unlockCanvasAndPost(myCanvas); //Write the new drawing to the SurfaceView
            }
        }

    */
    public Path draw() {

        return null;
    }


}
