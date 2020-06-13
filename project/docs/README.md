# LPOO_28 - Pacman (Reimagined)

Pacman is the classic everyone knows. It never goes out of style and we wanted to honor that by giving it a new light with some new ideas, like extra levels, powerups and portals mechanics.

The point of the game is to complete the levels without touching any of the ghosts (our maybe losing all HP), through collecting all the _pacdots_ (also knows as pellets or cookies). There are 3 levels, designed to be played in order with increasing difficulty. To win you must complete all the levels in a row.

Developped by [Francisco Gonçalves](https://github.com/kiko-g) and [João Rocha](https://github.com/JayRx).

---

## IMPLEMENTED FEATURES
* **Main Menu** - A menu that displays the main options ("Play", "Highscore" and "Exit"). Any of the options can be selected using the Arrow Keys and the Enter Key.
* **Highscore Menu** - A menu that displays the top 10 scores saved in a [highscore.txt](../src/main/resources/level.txt) text file.
* **Player and Elements representation** - Pacman is shown inside his arena (alongside pacdots, ghosts, portals and powerups) delimited by walls, that he is unable to trespass.
* **Player Movement** - The player character will move in a certain direction and repeat this movement for each 200 ms based on the input keys (ArrowUp, ArrowDown, ArrowLeft, ArrowRight).
* **Map Reader** - The map is created based on a full customizable text file [level.txt](../src/main/resources/level.txt)
* **PacDot collecting** - Consuming the pacdots removes the pacdot characters from the screen, incrementing the player's score.
* **PowerUp collecting** - Consuming a powerUp will give the player the ability to eat a ghost. Each powerUp only lasts for 5 seconds.
* **Portal Warping** - The portals on [level.txt](../src/main/resources/level.txt) allow pacman to teleport from one position to the other.
* **Ghost Movement** - Each ghost will move based on his strategy. The red ghost will pursue the player. The blue ghost will avoid the player. The pink ghost will move randomly. And the yellow ghost will try to intercept the player. If the player gets a power up, all ghosts turn blue and will run away from him for 5 seconds.
* **Ghost vs Player Collision** - If the player collides with a ghost it will lose one heart (he has three hearts in total) and all ghosts will respawn at their starting position. Only if the ghosts are running away from the player (they are all blue) and the player catches one of them it will respawn at his original starting position.

Below you can see a screenshot of how the game is looking:

### Main Menu
![PACMAN-MENU](https://i.gyazo.com/ea534de7cfd3e07059071a567d05b0a8.png)

### Game
![PACMAN-GAME](https://i.gyazo.com/60bf3633b4b3948b9507d61deb56edb4.png)

### Highscore Menu
![PACMAN-HIGHSCORE](https://i.gyazo.com/d74bd87aa84cb7e31ab260d99387b0ca.png)

## PLANNED FEATURES
* Ignore client input when he prompts a direction in which there is a wall blocking. This way pacman would not stop moving in the previous direction.
* More creative levels rather than just classic
  - Add more ghost behaviors upon other events on the map based on new elements
  - Add other score oriented elements (for example a cherry that spawns for 5 seconds in place of a pacdot and grants double points for 30 seconds)
  - Other ideas to make the game more thrilling and more replayable
* Add more levels and/or make levels playable on their own instead of sequentially
* Highscores showing time taken in attempt

### WHAT CHANGED SINCE INTERMEDIATE DELIVERY
The planned features we had set on the [intermediate delivery report](https://github.com/FEUP-LPOO/lpoo-2020-g28/blob/65b820c97914de975d0468282ff59ec32c36d5d9/docs/README.md#planned-features) were almost all attained (game time and level progress were not implemented because we don't see great value in these features) but there other changes specially when it comes to design and logic.

First lets discuss what changed in terms of features more specifically:
- New square font
- Classic pacman level
- Sequential levels
- Printing score and hearts (HP) left
- Saving scores in a file (and seeing the top 10 in the menu)
- Game Over screen as a state
- Ghosts frightened behaviour

In terms of design and logic of the project:
- Added a State Pattern (menu, arena, game over, highscores)
- Strattegy pattern was upgraded, with an Abstract Class (MovingStrattegy) and this class now extends all ghost movements (chasing and fleeing). This helped remove duplicated code.
- Removed drawing responsibility from Arena and Element classes and attempted to migrate the project to a MVC structure, but could not move everything from model to controller. MVC was left almost complete with the controller being yet to complete.


---

## DESIGN
### The ghosts movement strattegy should be different depending on its type
#### Problem in Context
Implementing a different tactic for each type of ghost would add a lot of scattered conditional logic. Each type of ghost adopts a different approach to chase the player.

#### The Pattern
We applied the **Strategy Pattern** to solve this problem. This patten allows us to represent different movement tactics with different Classes. To make this work we just need to use dependency injection to inject a [ChaseBehaviour](../src/main/java/com/lpoo28/pacman/model/ChaseBehaviour.java) and a [FrightenedBehaviour](../src/main/java/com/lpoo28/pacman/model/FrightenedBehaviour.java) into a [Ghost](../src/main/java/com/lpoo28/pacman/model/Ghost.java).

To minimize duplicated code we created the [MovingStrategy](../src/main/java/com/lpoo28/pacman/model/MovingStrategy.java).<br>
This class has two methods:
* **calculateAvailableMoves()** - is used to calculate each ghost's available moves.
* **calculateMove()** - calculates the move that each ghost will do. This method is overwritten in each Chase and Frightened Strategies.

#### Implementation
The following figure shows how the pattern was implemented.
![Ghost Strategy Pattern](https://i.gyazo.com/18cd7aa1b0858db81a029a3701f4aded.png)

These classes can be found in the following files:
* [Ghost](../src/main/java/com/lpoo28/pacman/model/Ghost.java)
* [MovingStrategy](../src/main/java/com/lpoo28/pacman/model/MovingStrategy.java)
* [ChaseBehaviour](../src/main/java/com/lpoo28/pacman/model/ChaseBehaviour.java)
* [FrightenedBehaviour](../src/main/java/com/lpoo28/pacman/model/FrightenedBehaviour.java)
* [ChaseAgressive](../src/main/java/com/lpoo28/pacman/model/ChaseAgressive.java)
* [ChasePredict](../src/main/java/com/lpoo28/pacman/model/ChasePredict.java)
* [ChaseRandom](../src/main/java/com/lpoo28/pacman/model/ChaseRandom.java)
* [ChaseSentinel](../src/main/java/com/lpoo28/pacman/model/ChaseSentinel.java)
* [FrightenedWandering](../src/main/java/com/lpoo28/pacman/model/FrightenedWandering.java)

#### Consequences
The use of the Strategy Pattern allows the following benefits:
* The multiple strategies become explicit in the code and more readable.
* We don't need to check the ghost type to make him move according to the strategy of his type.
* Now there are more classes to manage, but still in a reasonable number.

### Main Menu, Game, Game Over Screens, other Menus and Changing Between them
#### Problem in Context
To improve our game and add more options we decided to create a Main Menu Screen where the user can choose between three different options ("Play", "Highscores", "Exit"). We also added a Game Over Screen and a Highscore Screen.
<br>
First the user would see the Main Menu. If he chooses the "Play" option then the Screen render and execute the game. After, When the player loses it would show a Game Over screen with the score achieved and finally be redirected back to the Main Menu. If the user choose the "Highscores" option then the Screen would show the top 10 highest scores achieved by the user.
<br>
<br>
In order to do this, we needed to find a way to implement these parts without overusing conditional logic.

#### The pattern
We applied something very close to the State Pattern.
<br>
Each one of the parts previously described has a state (running or not). This state has to be changed in run-time to enter or exit menus.
<br>
Each state will start automatically open (end = false) and when the close() method is called the state will change to closed (end = true).

This allows an object to alter its behaviour when its internal state changes. If the state is open then this screen is being rendered.

#### Implementation
The following figure shows how the pattern was implemented.
![State Pattern](https://i.gyazo.com/beb835f4ab1d6e089b1170eb16a7b033.png)

These classes can be found in the following files:
* [State](../src/main/java/com/lpoo28/pacman/model/State.java)
* [Menu](../src/main/java/com/lpoo28/pacman/model/Menu.java)
* [Arena](../src/main/java/com/lpoo28/pacman/model/Arena.java)
* [Highscore](../src/main/java/com/lpoo28/pacman/model/Highscore.java)
* [GameOverMenu](../src/main/java/com/lpoo28/pacman/model/GameOverMenu.java)

#### Consequences
The use of the State Pattern has the following consequences:
* Localizes and partitions behaviour for different states.
* Makes state transitions explicit.
---

## KNOWN CODE SMELLS AND REFACTORING SUGGESTIONS
As stated above we were unable to finish migrating the project to a MVC structure and that's why we did not mention the Model View Controller pattern in the design section. Looking back now, we are able to realize that we should have focused on the design a little more, instead of always developping upon a version that would later require some heavy refactoring (to fit a good pattern for games like MVC). 
What is missing as an obvious refactor is moving the controller's responsabilities from the Arena (model) class. Not having done this, we are obviously violating the first rule of SOLID principles. We should not have left the MVC migration towards the deadline. We tried and we did make some progress, but in the end we had to revert to a version where the controller is mostly inside the model. Functions like `checkCell`, `createMap`, `processKey`, `start` among others in Arena class should have its logic be dealt by the Arena Controller and possibly other controller classes.

---

## TESTING
The test coverage result can be viewed in html [here](html/) or as snapshot of IntelliJ IDEA below:

![Unit Testing Coverage](https://i.gyazo.com/868f2c999f35ce4fa3d5a809e4d27d1a.png)

![Unit Testing Results](https://i.gyazo.com/01233fe83b9a931b3ba09e7728aff428.png)

If we check the first image we can see that we didn't achieve a 100% coverage on the testing of our project.<br> This can be caused by a number of factors:
##### Methods that only call other methods:
This happens because some methods only call other methods (that are unit tested) so it's redundant to test these methods.
##### Use of Timers
Another factor is that we use timers to control the player's movement (if the user only pressed 'W' one time the player will keep moving Up until it's colliding with a wall) and to control the player's Power Up (each power up only lasts 5 seconds once consumed).


---

## SELF-EVALUATION
* [Francisco Gonçalves](https://github.com/kiko-g): 50%
* [João Rocha](https://github.com/JayRx): 50%
