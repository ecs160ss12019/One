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
| Takes Player's touch input & Normalize input from -100 to 100 | HUD
| Draw Player's touch | Space Ship

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
| - | UFOManBuilder|

## Timers.java:
| Responsibilities | Interacts with |
| --- | ---|
| Keeps track of how long an object has been alive| UFOManager
| Changes UFOState to Leaving on timeout | StateContext| 

## Projectile.java:
| Responsibilites | Interacts with |
| --- | --- |
| Moves linearly away from point of origin. | UFO
| Has limited range | Ship
| - | Asteroid
| - | HUD

## ProjectileManager.java
| Responsibilites | Interacts with |
| --- | --- |

## Spaceship.java:
| Responsibilites | Interacts with |
| --- | --- |
| Move around | UFO
| Shoot | Environment
| Collision Detection | Asteroid
| - | Projectile
| - | HUD
| - | Movable object

## Environment.java:
| Responsibilites | Interacts with |
| --- | --- |
| Keep track of moveable objects | Spaceship
| Play music | UFO
| Collision Detection | Asteroid
| - | Moveable Object
| - | HUD 


## HUD.java:
| Responsibilites | Intracts with |
| --- | --- |
| Keep track of score | Spaceship
| Must display on the screen| Joystick

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

## CollisionDetection.java
| Responsibilites | Interacts with |
| --- | --- |

## StateContext.java
| Resonsibilites | Interacts with |
| --- | --- |
| Implements interface in order to allow define state.| All other state classes|
| - | UFO |
| - | UFOManager |

## WaitingState.java
| Responsibilites | Interacts with |
| --- | --- |
|Handles UFO Behavior when in waiting state.| UFO |

## ReadyState.java
| Responsibilites | Interacts with |
| --- | --- |
| ReadyState- Determines UFO entry point and is ready to enter| UFOManager |
| Changes UFO state to EnterState| - |

## EnterState.java
| Responsibilites | Interacts with |
| --- | --- |
| EnterState - UFO is *entering* the screen.| UFO |
| Changes UFO state to InsideState when UFO is "inside" screen | - |

## InsideState.java
| Responsibilites | Interacts with |
| --- | --- |
| InsideState - Responsible for bounded movement within screen. | UFO |

## LeavingState.java
| Responsibilites | Interacts with |
| --- | --- |
| LeavingState - UFO Leaves the screen and returns to WaitingState| Timers |

## DeadState.java
| Responsibilites | Interacts with |
| --- | --- |
| DeadState - Set by hit detection. UFO explodes | UFO |
| - | CollisoinDetection|

## Explosion.java
| Responsibilites | Interacts with |
| --- | --- |
| Holds logic for the explosion animaiton. | UFO |

## MusicManager.java
| Responsibilites | Interacts with |
| --- | --- |
| Manages game music| Env |

## SFXManager.java
| Responsibilites | Interacts with |
| --- | --- |
| Manages game sound effects(sfx)| Env |

## UFOManBuilder.java
| Responsibilites | Interacts with |
| --- | --- |
| Builds the UFOManager object| UFOManager|








