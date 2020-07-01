# swing-checkers-game
Checkers Game implemented in Java using the Swing Framework in Netbeans
This project was created as a semester project in Principles of Software Engineering at Hellenic Mediterranean University.
# Images
![Imgur](https://imgur.com/U6yhPx4.png)
![Imgur](https://imgur.com/4CjJFHT.png)
# Issues
* Kings are not properly implemented, i thought that the King was supposed to be like a normal Man, only that he can move in both directions.I found out that the King can move diagonally through out the board and keep moving if he captured an enemy.I didn't have the time to correct the Game Logic.
* The GameLogic.java class is terribly written,there is no code that is reused between the players and thus the class is almost 1000 LOC.I want to refactor the code so that most of the code can be reused between Players(Men,Kings,White,Red)
