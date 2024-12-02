package cards;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

/**
 * Runs all tests for the package.
 * 
 * @author Edward Pratt & Sandy Hay
 * @version 1.0
 */
@Suite
@SelectClasses({testCard.class, testCardDeck.class, testCardGame.class, testFileEditor.class, testPack.class, testPlayer.class})

public class testCardGameSuite {
}
