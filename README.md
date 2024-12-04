# ECM2414 Card-Game
## Introduction
The premise of this project is to simulate a card game in which players draw and discard cards from decks until they 
have 4 cards of the same value.

## Running the game from the jar file
To run the game from the jar file, navigate to the directory containing the jar file and run the following command:

```cmd
java -jar cards.jar
```

This will start the game and prompt you to enter the number of players you want to simulate.
<br>
After this it will ask for the file location of a pack file. It checks that the pack has a number of cards(lines) 
equal to 8n where n is the number of players.
<br>
If this is true, the game will start and when it finished the number of the player that has won will be printed to 
the command line. A set of files will also be created in the directory containing information about the players choices.


## Running the test suite
To run the test suite, navigate to the root folder of the project in CMD
and run the following command:

```cmd
java -jar .\tests\lib\junit-platform-console-standalone-1.11.3.jar -cp .;cards-1.0.jar;tests --select-class cards.testCardGameSuite
```