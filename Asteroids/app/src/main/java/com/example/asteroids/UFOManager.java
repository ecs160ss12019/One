package com.example.asteroids;

/*
 * TODO: ADD "timer" that keeps track of how long UFO has been alive
 */
public class UFOManager {

    //Will hold all of the UFO's to be managed
    UFO[] ufoArray;
    //Number of MAX UFO's on screen
    private int maxUFO;
    private int alive;

    UFOManager(int maxUFO, int x, int y){
        this.maxUFO = maxUFO;
        ufoArray = new UFO[this.maxUFO];

        for(int i = 0; i < this.maxUFO; i++){
            ufoArray[i] = new UFO(x, y);
        }

        alive = 0;
    }

    //Spaw UFO onto screen
    //return 0 on success, else failed

    public int spawnUFO(){
        int index;
        if(alive >= 10){
            return -1;
        }

        //Update number of UFO's active
        alive++;
        index = findAvailableUFO();
        ufoArray[index].state = UFO_State.ENTERING;

        return 0;
    }




    private int findAvailableUFO(){
        for(int i = 0; i < maxUFO; i++){
            if(ufoArray[i].state == UFO_State.READY){
                return i;
            }
        }
        return -1;
    }

}
