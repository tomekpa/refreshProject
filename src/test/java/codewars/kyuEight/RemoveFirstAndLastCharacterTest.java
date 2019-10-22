package codewars.kyuEight;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RemoveFirstAndLastCharacterTest {
    @Test
    public void testRemoval() {
        assertEquals("loquen", RemoveFirstAndLastCharacter.remove("eloquent"));
        assertEquals("ountr", RemoveFirstAndLastCharacter.remove("country"));
        assertEquals("erso", RemoveFirstAndLastCharacter.remove("person"));
        assertEquals("lac", RemoveFirstAndLastCharacter.remove("place"));
    }
}