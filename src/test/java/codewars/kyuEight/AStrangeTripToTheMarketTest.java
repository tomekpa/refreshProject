package codewars.kyuEight;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AStrangeTripToTheMarketTest {
    @Test
    public void testBasicTrue() {
        String n = "Your girlscout cookies are ready to ship. Your total comes to tree fiddy";
        System.out.println(n);
        assertTrue(AStrangeTripToTheMarket.isLockNessMonster(n));
    }

    @Test
    public void testBasicFalse() {
        String n = "Yo, I heard you were on the lookout for Nessie. Let me know if you need assistance.";
        System.out.println(n);
        assertFalse(AStrangeTripToTheMarket.isLockNessMonster(n));
    }
}