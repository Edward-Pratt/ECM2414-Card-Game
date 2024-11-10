package cards;

import java.util.Scanner;

public class CardGame {
    public static void main(String[] args) {
        try{
            Scanner commandReader = new Scanner(System.in);
            System.out.println("Welcome to the card game! Please enter the number of players: ");
            int numPlayers = commandReader.nextInt();
            System.out.println("Please enter the directory of your pack file: ");
            String packFile = commandReader.next();
            System.out.println(("You are playing with "+ numPlayers+ " players and the pack file"+ packFile));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static void playGame(){
        
    }
}
