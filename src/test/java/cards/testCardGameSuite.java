package cards;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({testCard.class, testCardDeck.class, testCardGame.class, testFileEditor.class, testPack.class, testPlayer.class})

public class testCardGameSuite {
}
