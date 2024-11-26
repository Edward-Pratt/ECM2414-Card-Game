package cards;

import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Stream;

/**
 * 
 */

abstract class Pack {
    private static final Queue<Card> pack = new LinkedList<>();

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

    public static Boolean validatePack(int NumPlayers, Queue<Card> packToValidate){
        return pack.size() == 8*NumPlayers;
    }
}
