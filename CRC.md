# CRC Cards

## Asteroid.java:
| Responsibilites | Interacts with |
| --- | --- |
| Break apart after getting shot. | Environment
| Moves with a certain velocity. | Spaceship
| - | Projectile
| - | HUD


## UFO.java:
| Responsibilites | Interacts with |
| --- | --- |
| Must move randomly | Moveable Object
| Shoot randomly | Asteroid
| Must explode when hit. | Space ship
| Know location | Projectile
| - | HUD

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
