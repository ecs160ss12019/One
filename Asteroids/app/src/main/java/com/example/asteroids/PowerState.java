/* Use the fire method if you are changing the shooting of the spaceship. If creating
a new PowerUp, you have to define the shoot method as the DefaultPowerState method since
our version of Android doesn't support default methods
*  Otherwise use the update method to do anything else you want with the ship
* */

package com.example.asteroids;

//Martin Petrov


import android.util.Log;

public interface PowerState {

    void update(Spaceship ship);
    void fire(Spaceship ship);
}

class BurstFirePowerState implements  PowerState{
    public void fire(Spaceship ship) {
        ship.projectileManager.fire(ship.shapeCoords[0], ship.shapeCoords[2], ship.rotation - 10, ship.projectileOwner);
        ship.projectileManager.fire(ship.shapeCoords[0], ship.shapeCoords[2], ship.rotation, ship.projectileOwner);
        ship.projectileManager.fire(ship.shapeCoords[0], ship.shapeCoords[2], ship.rotation + 10, ship.projectileOwner);
        ship.firing = false;
    }
    public void update(Spaceship ship) {
        Log.d("Powerstate", "BurstFirePowerState");

    }
}

/*Default Powerstate*/
class DefaultPowerState implements PowerState {
    public void fire(Spaceship ship) {
        ship.projectileManager.fire(ship.shapeCoords[0], ship.shapeCoords[2], ship.rotation, ship.projectileOwner);
        ship.firing = false;
    }
    public void update(Spaceship ship) {
        Log.d("Powerstate", "DefaultPowerState");

    }
}

class ExtraLifePowerState implements PowerState {
    public void fire(Spaceship ship) {
        ship.projectileManager.fire(ship.shapeCoords[0], ship.shapeCoords[2], ship.rotation, ship.projectileOwner);
        ship.firing = false;
    }

    public void update(Spaceship ship) {
        Log.d("Powerstate", "ExtraLifePowerState");
        ship.numLives++;
        ship.currPowerState = new DefaultPowerState();
    }
}


class MachineGunPowerState implements PowerState {
    public void fire(Spaceship ship) {
        ship.projectileManager.fire(ship.shapeCoords[1], ship.shapeCoords[3], ship.rotation, ship.projectileOwner);
    }
    public void update(Spaceship ship) {
        Log.d("Powerstate", "BurstFirePowerState");

    }

}


/*Invulnerable to anything*/
class ShieldPowerState implements PowerState {
    public void fire(Spaceship ship) {
        ship.projectileManager.fire(ship.shapeCoords[0], ship.shapeCoords[2], ship.rotation, ship.projectileOwner);
        ship.firing = false;
    }
    public void update(Spaceship ship) {
        //TODO: Draw Shield around ship
        Log.d("Powerstate", "ShieldPowerState");
        ship.isHit = false;
    }


}
