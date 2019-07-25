# Team ONE - Asteroids

## BHAG:
"You are a space pilot avoiding obstacles and shooting down as many asteroids
as possible."

## EPICS:
1) As a pilot I want to drive a ship.
2) While avoiding and shooting down asteroids.
3) As well as avoiding and shooting down UFO's
4) Through space while keeping track of the score.


## Sprints:
![Sprints Chart](https://github.com/ecs160ss12019/One/blob/master/Chart.png) 

## Acceptance Criteria:
![Acceptance Criteria Chart](https://github.com/ecs160ss12019/One/blob/master/acceptance-criteria.png)  

## Sprint 2 Update:

### Sprint 1 Short Summary
After completing Sprint 1 we ended up making a little more progress than we had
initially expected. During the implementaion phase we decided to make a few 
updates to the initial design which was the addition of a few new classes that 
we had not though of during our design phase.

In terms of what we have completed so far we satisfied all of our Sprint 1 
Acceptance Criteria.  

* When I start the game, I see a space ship.
* When I start the game, I see asteroids.
* When I start the game, I see at least 1 UFO.
* When I start the game, I see all objects together.

Additional tasks completed

* The movement of the Space Ship
* Implemented JoyStick feature.
* Spawning of multiple and random (UFOs/Asteroids)


### Changes To Design
* Added AsteroidManager.java class
* Added AsteroidGenerator.java class
* Added UFOManager.java class
* Added Joystick.java class
* Changed how our `draw()` method would be implemented.
* Added blockSize variable for universal scaling.

### Sprints Chart
![Sprints Chart 2](https://github.com/ecs160ss12019/One/blob/master/Chart_Sprint2.PNG)

### Acceptance Critera
![Acceptance Criteria Chart 2](https://github.com/ecs160ss12019/One/blob/master/acceptance-criteria2.png)


## Sprint 3 Update: 

### Sprint 2 Short Summary
In sprint 2 some designe changes were made to the UFO class. Before UFO states
were implemented using a `switch case`. In the new design we used a State
Pattern implementation. The benifit of this was the abilitly to encapsulate the
varying behavior of the UFO objects.

![UFO State](https://github.com/ecs160ss12019/One/blob/master/UFOStateDiagram.pdf)

A Builder was also implemented for UFOManager because its constructor was
starting to get long. Apart from improving readability this implemenation
reduces the chance of new bugs from messing up the order of the arguments passed
in.

Implemenation of the *shooting* and *collision detection* systems have been
implemented. Integration with a few other components is still necessary.

Music, Sound Effects, and a game menu have also been implemented. During our
next sprint we will further enhance thesee systems.


Acceptance criteria that we met during our second sprint
* When I press a button the space ship shoots
* When I fly off screen I reappear from the opposite side.


