# Background:
The first step to solving collision detection was in finding a way to represent our game objects as physical entities, and finding a compatible way of detecting interactions between objects. Our game represents objects as Paths, which are the descriptions of their geometric outlines. We are going to use Android's Region class to represent a continuous 2D region that is bounded by the path which outlines the game object. Region contains methods for detecting overlapping regions, and we will use them.

NOTES-1
![alt text](https://github.com/ecs160ss12019/One/blob/master/collisionDetectionBlueprint/IMG_9485.png "Notes p.1")
 
The picture above lists some of the Region methods that will be used. It also contains my initial collection of object interactions that get tested during collision detection. This is not an optimal number of comparisons to make, as there are redendant checks. The graph in the upper right visually represents the collision detection calls to compare objects in a binary manor (these are the edges that connect verticies). This brute force method exhaustively compares each game object with every other game object. There is also a 2D "Collidibility" matrix which represents which game objects actually interact. For example, Asteroids pass thrugh each other, but collide with space ships.


# Simplifying the number of Comparison calls
With the collision checking graph constructed, my goal was to simplify it according to the Collidibility matrix and minimize the number of comparisons. To do this I wrote out sets that list an object's collision checks with other objects, then crossed out redundancies and also checks not required by the Collidibility matrix. Finally, I had a refined list of comparisons to make when designing the algorithm.
Picture 2:

NOTES-2
![alt text](https://github.com/ecs160ss12019/One/blob/master/collisionDetectionBlueprint/IMG_9486.png "Notes p.2")

The picture above shows the simplification process for pruning the sets.


# Optimality
To test the effect of optimization, I created an example graph containing 7 different nodes. The original method would make 42 total comparisions between objects, the new version makes 30 comparisons. That's roughly a 30% smaller problem. Results will vary with the number of objects existing at once, but still better performance is achieved. Now for planning the algorithm. To start, lets think about what is being checked for collisions against the player's ship. The ship can collide with ateroids, ufos, and ufo projectiles. Notice that the player ship doesn't have to check for collisions with its own projectiles (it cannot hurt itself). To achieve optimality, this thinking is applied to each type of game object. For example, all active ufos will be checked against player projectiles and asteroid - nothing more.

NOTES-3+4
![alt text](https://github.com/ecs160ss12019/One/blob/master/collisionDetectionBlueprint/IMG_9487.png "Notes p.3")

![alt text](https://github.com/ecs160ss12019/One/blob/master/collisionDetectionBlueprint/IMG_9488.png "Notes p.4")

The pictures above shows the simplified comparison graph and structures of comparisons to be made in the algorithm.

# The Algorithm Pseudocode
public globalCollisions(MovableObject[] moS) {

// get sublists of objects from moS
     ship = getShip(moS)
     ufos = getUfos(moS)
     asts = getAsts(moS)
     projs = getprojs(moS)
 
 // Check what hit the player's ship
     for i = 1 to asts.size()
          if asteroid collides with ship
               record collision
     for i = 1 to ufos.size()
          if ufo collides with ship
               record collision
     for i = 1 to projs.size()
          if projectile is alien made
               if ufo collides with ship
                    record collision

// Check what hit the Ufos
     for i=1 to ufos.size()
          for i=1 to projs.size()
               if projectile is player made
                    record collision
          for i=1 to asts.size()
               if asteroid collides with ufo
                    record collision

// Check what hit the ateroids
     for i=1 to asts.size()
          for i=1 to projs.size()
               if projectile hits asteroid
                    record collision

![alt text](https://github.com/ecs160ss12019/One/blob/master/collisionDetectionBlueprint/IMG_9489.png "Notes p.5")

The image above shows the hand written psuedocode
