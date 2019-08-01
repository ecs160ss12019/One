package com.example.asteroids;

// Jose Torres-Vargas
import android.content.res.Resources;
import android.graphics.BlurMaskFilter;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.RectF;
import android.util.Log;

import java.util.Random;

/**
 * Tells us which side of the display the UFO comes in from
 */
enum UFO_Origin{
    LEFT, TOP, RIGHT, BOTTOM
}

/**
 * UFO represents the UFO objects in the game.
 * Contains all logic needed to draw UFO such as difficulty of UFO,
 * shape of UFO, and it's current position.
 *
 * @author Jose Torres-Vargas
 */
public class UFO extends MovableObject {

    //UFO body
    RectF body;
    private PointF bodyDim;
    private PointF circle;
    private float radius;
    private PointF circleOffsets;

    //Does UFO turn "invisible"
    boolean phase;

    PointF velocity;
    //float mXVelocity, mYVelocity;

    //Where does UFO shot originate from
    PointF bulletOrigin1;
    PointF bulletOrigin2;

    //Screen resolution
    Point res;
    Resources resources;

    StateContext state;
    Explosion explosion;
    UFO_Origin enterFrom;
    UFO_Type difficulty;

    //Max Display Boundary for UFO
    float xLBound, xRBound;
    float yTBound, yBBound;

    //Screen has four sides
    private int[] ufoEntry = new int[4];

    Random random = new Random();
    ProjectileManager projectileManager;
    SFXManager sfxManager;

    Paint normalPaint;
    Paint blurPaint;
    UFO(Point res, PointF blockSize, Resources resources, ProjectileManager projectileManager,
            SFXManager sfxManager) {
        super(blockSize);
        this.resources = resources;
        this.sfxManager = sfxManager;
        //ID attatched to bullets to know who shot who
        projectileOwner = 2;

        body = new RectF();
        bodyDim = new PointF(100, 40);
        circle = new PointF();
        circleOffsets = new PointF(50,10);
        radius = 30;

        velocity = new PointF(res.x/10, res.x/10);
        xLBound = 0;
        xRBound = res.x;
        yTBound = 0;
        yBBound = res.y;

        //Left
        ufoEntry[0] = (int)xLBound - (int)bodyDim.x;
        //Top
        ufoEntry[1] = (int)yTBound - (int)bodyDim.y;
        //Right
        ufoEntry[2] = (int)xRBound + (int)bodyDim.x;
        //Bottom
        ufoEntry[3] = (int)yBBound + (int)bodyDim.y;

        state = new StateContext();

        //Sprite sheet is 4 X 4
        explosion = new Explosion(4,4, resources);
        this.projectileManager = projectileManager;
        bulletOrigin1 = new PointF();
        bulletOrigin2 = new PointF();
        this.res = new Point();
        this.res.set(res.x,res.y);
        phase = false;
        difficulty = UFO_Type.GREEN;//Easy by default
        setUpPaint();
    }


    /**
     * Method updates UFO by performing UFO state action which
     * varies depending on current UFO state.
     * @param fps: Frames Per Second
     */
    void update(long fps){
        //starts visible
        phase = false;
        if(astHitUfo){
            astHitUfo = false;
            phase = true;
        }else{
            phase = false;
            if(!state.isDead()) {
                //If ufo was hit by something other than asteroid
                if (this.isHit && state.isInside()) {
                    Log.d("UFOLife: ", "UFO set to DeadState");
                    sfxManager.playExplosion();
                    state.setState(new DeadState());
                }
            }
        }
        state.stateAction(this, fps);
    }

    /**
     *
     * @return shape: Path representing UFO drawing
     */
    public Path draw(){
        shape.rewind();
        shape.addCircle(circle.x, circle.y, radius, Path.Direction.CW );
        shape.addOval(body, Path.Direction.CW);
        return shape;
    }

    /**
     * Randomly positions the UFO somewhere outside the display screen.
     * @param side: which side or "edge" will the UFO come in from
     */
    void ufoSetPosition(int side){
        int xPosition, yPosition;

        if( (side % 2) == 0 ){
            yPosition = random.nextInt((int)yBBound);
            xPosition = ufoEntry[side];
        }
        else{
            xPosition = random.nextInt((int)xRBound);
            yPosition = ufoEntry[side];
        }
        body.set(xPosition, yPosition, xPosition + bodyDim.x, yPosition + bodyDim.y );
        circle.set(xPosition + circleOffsets.x, yPosition + circleOffsets.y);
    }

    /**
     * Checks to see if UFO is out of bounds(display dimensions)
     */
    void isOut(){
        //Top
        if(body.bottom < yTBound){

            state.setState(new WaitingState());
            Log.d("isOut: ", "changing state to " + state);
        }
        //Right
        else if(body.left > xRBound){
            state.setState(new WaitingState());
            Log.d("isOut: ", "changing state to " + state);
        }
        //Bottom
        else if((body.top - radius) > yBBound ){
            state.setState(new WaitingState());
            Log.d("isOut: ", "changing state to " + state);
        }
        //Left
        else if(body.right < xLBound){
            state.setState(new WaitingState());
            Log.d("isOut: ", "changing state to " + state);
        }
    }

    /**
     * Update UFO along the x-axis
     * @param fps: Frames Per Second
     */
    void ufoUpdateX(long fps){
        body.left = body.left + (velocity.x / fps);
        body.right = body.left + bodyDim.x;
        circle.x += (velocity.x / fps);
    }


    /**
     * Update UFO along the y-axis
     * @param fps: Frames Per Second
     */
    void ufoUpdateY(long fps){
        body.top = body.top + (velocity.y / fps);
        body.bottom = body.top + bodyDim.y;
        circle.y += (velocity.y / fps);
    }

    /**
     * Check to see if UFO has hit a edge or "Wall" If so the UFO
     * bounces back.
     */
    void checkBounds(){
        if(body.right > xRBound){
            body.right = xRBound;
            body.left = body.right - bodyDim.x;
            circle.x = body.left + circleOffsets.x;
            reverseXVelocity();
        }
        else if(body.left < xLBound){
            body.left = xLBound;
            body.right = body.left + bodyDim.x;
            circle.x = body.left + circleOffsets.x;
            reverseXVelocity();
        }
        else if(body.top < (yTBound + radius) ){
            body.top = yTBound + radius;
            body.bottom = body.top + bodyDim.y;
            circle.y = body.top + circleOffsets.y;
            reverseYVelocity();
        }
        else if(body.bottom > yBBound){
            body.bottom = yBBound;
            body.top = body.bottom - bodyDim.y;
            circle.y = body.top + circleOffsets.y;
            reverseYVelocity();
        }
    }

    /**
     * Reverse the x-axis component of UFO velocity.
     */
    private void reverseXVelocity(){
        velocity.x = -velocity.x;
    }

    /**
     * Reverse the y-axis component of UFO velocity.
     */
    private void reverseYVelocity(){
        velocity.y = -velocity.y;
    }

    /**
     * makes the UFO transparent
     */
    void phaseThrough(){
        normalPaint.setAlpha(100);
    }

    /**
     * makes UFO visible
     */
    void solidUFO(){
        normalPaint.setAlpha(248);
    }

    void setUpPaint(){
        normalPaint = new Paint();
        normalPaint.setAntiAlias(true);
        normalPaint.setDither(true);
        normalPaint.setColor(Color.argb(248, 0, 255, 0));
        normalPaint.setStyle(Paint.Style.STROKE);
        normalPaint.setStrokeJoin(Paint.Join.ROUND);
        normalPaint.setStrokeCap(Paint.Cap.ROUND);
        normalPaint.setStrokeWidth(20f);

        blurPaint = new Paint();
        blurPaint.set(normalPaint);
        blurPaint.setColor(Color.argb(235,74,138,255));
        blurPaint.setStrokeWidth(30f);
        blurPaint.setMaskFilter(new BlurMaskFilter(15, BlurMaskFilter.Blur.NORMAL));

    }

}