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
    private PointF stickPosition = new PointF();
    private Point resolution;
    private PointF blockSize;
    private float baseRadius;
    private float hatRadius;

    private Path base = new Path();
    private Path hat = new Path();

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

        this.base.addCircle(baseCenter.x, baseCenter.y, baseRadius, Path.Direction.CW);
        this.hat.addCircle(hatCenter.x, hatCenter.y, hatRadius, Path.Direction.CW);
    }

    ///////////////////////////
    //      METHODS
    ///////////////////////////
    public PointF getStickPosition() {
        return this.stickPosition;
    }

    // TODO: make mapped stick functionality.
    public PointF getScaledStickPosition() {
        return null;
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        if (e.getAction() != e.ACTION_UP) {
            // read the stick's current position
            stickPosition.x = e.getX();
            stickPosition.y = e.getX();

            // find magnitude of displacement from center of base circle
            float displacement = (float) Math.sqrt(Math.pow((stickPosition.x - baseCenter.x), 2) + Math.pow((stickPosition.y - baseCenter.y), 2));

            if (displacement < baseRadius) {
                // when hat does't need constraining to base radius
                hatCenter = stickPosition;
            } else {
                // when hat needs constraining to base radius
                float ratio = baseRadius / displacement;
                float constrainedX = baseCenter.x + (stickPosition.x - baseCenter.x) * ratio;
                float constrainedY = baseCenter.y + (stickPosition.y - baseCenter.y) * ratio;
                stickPosition.x = constrainedX;
                stickPosition.y = constrainedY;
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
    public Path[] draw() {
        //get current positions, then move circles to them.


        Path[] joyStick = new Path[2];
        joyStick[0] = base;
        joyStick[1] = hat;
        return joyStick;
    }


}
