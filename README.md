# ECM2414 Card-Game
## Introduction
The premise of this project is to simulate a card game in which players draw and discard cards from decks until they 
have 4 cards of the same value.

## Running the game from the jar file

1. Open a Command Prompt window and navigate to the directory containing cards.jar file.<br>
2. If you are using Powershell, run the command ```cmd``` to switch to Command Prompt.<br>
3. Run the following command:
```cmd
java -jar cards.jar
```
4. Type the number of players you want to play with and press Enter. (To use test pack type 4)<br>
5. Type the directory of the file containing the pack of cards you want to use and press Enter. (To use test pack type 
   4players.txt)<br>
6. The game will start and when it finishes, the winner will be displayed and files will be written to your directory.
## Running the test suite
1. Open a Command Prompt window and navigate to the directory containing cards.jar file.<br>
2. Run the following command:
```cmd
java -jar .\tests\lib\junit-platform-console-standalone-1.11.3.jar -cp .;cards.jar;tests --select-class cards.testCardGameSuite
```
3. The test suite will run and the results will be displayed in the Command Prompt window.