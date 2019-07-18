# CRC Cards

## Asteroid.java:
| Responsibilites | Interacts with |
| --- | --- |
| Break apart after getting shot. | Environment
| Moves with a certain velocity. | Spaceship
| - | Projectile
| - | HUD

## AsteroidGenerator.java
| Responsibilites | Interacts with |
| --- | --- |
| On initialization generate the coordinate array for each asteroid type. | Asteroid
| Return corresponding asteroid when passed an asteroid type. | -
| - | - 

## AsteroidManager.java
| Responsibilites | Interacts with |
| --- | --- |
| Initialize Asteroids. | Asteroid
| Send drawn paths for each Asteroid to Env. | Env
| Apply the Direction Vector over time to the Asteroid. | - 
| Regenerate Asteroids when they leave the screen. | - 
| Apply random start points and direction vectors to each Asteroid. | - 

## JoyStick.java
| Responsibilites | Interacts with |
| --- | --- |


## UFO.java:
| Responsibilites | Interacts with |
| --- | --- |
| Must move randomly | Moveable Object
| Shoot randomly | Asteroid
| Must explode when hit. | Space ship
| Know location | Projectile
| - | HUD

## UFOManager.java:
| Responsibilites | Interacts with |
| --- | --- |
| Spawns UFO's into the screen | UFO |
| Keeps track of UFO State | Timers |

## Timers.java:
| Responsibilities | Interacts with |
| --- | ---|
| Keeps track of how long an object has been alive| UFOManager
 

## Projectile.java:
| Responsibilites | Interacts with |
| --- | --- |
| Moves linearly away from point of origin. | UFO
| Has limited range | Ship
| - | Asteroid
| - | HUD

## Spaceship.java:
| Responsibilites | Interacts with |
| --- | --- |
| Move around | UFO
| Shoot | Environment
| Collision Detection | Asteroid
| - | Projectile
| - | HUD
| - | Moveable object

## Environment.java:
| Responsibilites | Interacts with |
| --- | --- |
| Keep track of moveable objects | Spaceship
| Play music | UFO
| Collision Detection | Asteroid
| - | Moveable Object

## Grid.java:
| Responsibilites | Interacts with |
| --- | --- |
| Keep track of grid | -
| Return pixels that are occupied| - 

## HUD.java:
| Responsibilites | Intracts with |
| --- | --- |
| Keep track of score | Spaceship
| Must display on the screen| -

## MoveableObjects.java
| Resonsibilites | Interacts with |
| --- | --- |
| Keep track of position | Every moveable object
| Keep track of velocity | Environment
| Draw Self | HUD

## AsteroidsActivity.java
| Resonsibilites | Interacts with |
| --- | --- |
| Manage the Android Game Life Cycle | -
