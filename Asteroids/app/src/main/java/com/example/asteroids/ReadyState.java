package com.example.asteroids;

import android.util.Log;

import java.util.Random;

public class ReadyState implements State {
    Random random;
    ReadyState(){
        random = new Random();
    }

    @Override
    public void stateAction(StateContext context, UFO ufo, long fps) {
        Log.d("ReadyState: ", "Inside stateAction");
        int side = random.nextInt(4);
        if(side == 0){ufo.enterFrom = UFO_Origin.LEFT;}
        else if(side == 1){ufo.enterFrom = UFO_Origin.TOP;}
        else if(side == 2){ufo.enterFrom = UFO_Origin.RIGHT;}
        else{ufo.enterFrom = UFO_Origin.BOTTOM;}
        ufo.ufoSetPosition(side);
        context.setState(new EnterState());
    }

    @Override
    public boolean isAvailable() {
        return false;
    }
}
