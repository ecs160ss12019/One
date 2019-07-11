# CRC Cards

## Asteroid.java:
| Responsibilites | Interacts with |
| --- | --- |
| Break apart after getting shot. | Environment
| Moves with a certain velocity. | Spaceship
| - | Projectile
| - | HUD


## UFO.java:
| Resonsibilites | Interacts with |
| --- | --- |
| Must move randomly | Moveable Object
| Shoot randomly | Asteroid
| Must explode when hit. | Space ship
| Know location | Projectile
| - | HUD

## Projectile
| Resonsibilites | Interacts with |
| --- | --- |
| Moves linearly away from point of origin. | UFO
| Has limited range | Ship
| - | Asteroid
| - | HUD
