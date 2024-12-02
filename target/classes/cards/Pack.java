package cards;

import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Stream;

/**
 * Pack class serves as a blueprint for the methods for pack of cards
 * within the game.
 * This abstract class reads the pack of cards from a file and selects
 * retrieves 8n number of cards, for where n is the number of players
 * participating in the game.
 * 
 * @author Edward Pratt & Sandy Hay
 * @version 1.0
 */
abstract class Pack {
    private static final Queue<Card> pack = new LinkedList<>();

    /**
     * Reads the file location of the pack and retrieves the cards from the
     * file and adds 8n cards, where n is the number of players in the game
     * 
     * @param packLocation the file location of the pack of cards
     * @param NumPlayers   the number of players in the game
     * @throws RuntimeException if runtime error occurs if the number of cards
     *                          in the pack is not a multiple of 8
     * @return the non-empty pack of cards
     */
    public static Queue<Card> readPack(String packLocation, int NumPlayers){
        FileEditor fileEditor = new FileEditor();
        String[] packContentsStr = fileEditor.readFile(packLocation);
        int[] packContents = Stream.of(packContentsStr).mapToInt(Integer::parseInt).toArray();
        for (int i : packContents) {
            pack.add(new Card(i));
        }
        if (validatePack(NumPlayers, pack)){
            return pack;
        } else {
            throw new RuntimeException("Pack is not valid");
        }
    }

    /**
     * Validates that the number of cards in the pack is a multiple of 8.
     * 
     * @param NumPlayers     the number of players in the game
     * @param packToValidate the desired pack in question
     * @return boolean statement, TRUE if the number of cards is a multiple of 8
     *         otherwise, returns FALSE
     */
    public static Boolean validatePack(int NumPlayers, Queue<Card> packToValidate){
        return packToValidate.size() == 8*NumPlayers;
    }
}
