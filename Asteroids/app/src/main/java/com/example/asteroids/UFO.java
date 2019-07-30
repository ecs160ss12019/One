package com.example.asteroids;

// Jose Torres-Vargas
import android.content.res.Resources;
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
    private float bodyWidth, bodyHeight;
    private float circleX, circleY, radius;
    private float circleXOffset, circleYOffset;

    //Does UFO turn "invisible"
    boolean phase;

    float mXVelocity, mYVelocity;

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

    Paint paint;
    UFO(Point res, PointF blockSize, Resources resources, ProjectileManager projectileManager,
            SFXManager sfxManager) {
        super(blockSize);
        this.resources = resources;
        this.sfxManager = sfxManager;
        //ID attatched to bullets to know who shot who
        projectileOwner = 2;

        body = new RectF();
        bodyWidth = 100;
        bodyHeight = 40;
        radius = 30;
        circleXOffset = 50;
        circleYOffset = 10;

        mXVelocity = res.x / 10;
        mYVelocity = res.x / 10;

        xLBound = 0;
        xRBound = res.x;
        yTBound = 0;
        yBBound = res.y;

        //Left
        ufoEntry[0] = (int)xLBound - (int)bodyWidth;
        //Top
        ufoEntry[1] = (int)yTBound - (int)bodyHeight;
        //Right
        ufoEntry[2] = (int)xRBound + (int)bodyWidth;
        //Bottom
        ufoEntry[3] = (int)yBBound + (int)bodyHeight;

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
        paint = new Paint();
        paint.setColor(Color.argb(255,0,255,0));
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
                if (this.isHit) {
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
        shape.addOval(body, Path.Direction.CW);
        shape.addCircle(circleX, circleY, radius, Path.Direction.CW );
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
        body.set(xPosition, yPosition, xPosition + bodyWidth, yPosition + bodyHeight );
        circleX = xPosition + circleXOffset;
        circleY = yPosition + circleYOffset;

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
        body.left = body.left + (mXVelocity / fps);
        body.right = body.left + bodyWidth;
        circleX = circleX + (mXVelocity / fps);
    }


    /**
     * Update UFO along the y-axis
     * @param fps: Frames Per Second
     */
    void ufoUpdateY(long fps){
        body.top = body.top + (mYVelocity / fps);
        body.bottom = body.top + bodyHeight;
        circleY = circleY + (mYVelocity / fps);
    }

    /**
     * Check to see if UFO has hit a edge or "Wall" If so the UFO
     * bounces back.
     */
    void checkBounds(){
        if(body.right > xRBound){
            body.right = xRBound;
            body.left = body.right - bodyWidth;
            circleX = body.left + circleXOffset;
            reverseXVelocity();
        }
        else if(body.left < xLBound){
            body.left = xLBound;
            body.right = body.left + bodyWidth;
            circleX = body.left + circleXOffset;
            reverseXVelocity();
        }
        else if(body.top < (yTBound + radius) ){
            body.top = yTBound + radius;
            body.bottom = body.top + bodyHeight;
            circleY = body.top + circleYOffset;
            reverseYVelocity();
        }
        else if(body.bottom > yBBound){
            body.bottom = yBBound;
            body.top = body.bottom - bodyHeight;
            circleY = body.top + circleYOffset;
            reverseYVelocity();
        }
    }

    /**
     * Reverse the x-axis component of UFO velocity.
     */
    private void reverseXVelocity(){
        mXVelocity = -mXVelocity;
    }

    /**
     * Reverse the y-axis component of UFO velocity.
     */
    private void reverseYVelocity(){
        mYVelocity = -mYVelocity;
    }

    /**
     * makes the UFO transparent
     */
    void phaseThrough(){
        paint.setAlpha(100);
    }

    /**
     * makes UFO visible
     */
    void solidUFO(){
        paint.setAlpha(255);
    }

}