package codewars.beta;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class PalindromeCheckerTest {

    private PalindromeChecker pc;

    @Parameter
    public String str;
    @Parameter(value = 1)
    public boolean expected;

    @Before
    public void initialize() {
        pc = new PalindromeChecker();
    }

    @Parameters(name = "{index}: {0}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {null, false},
                {"race car", true},
                {"Amor, Roma", true},
                {"123521", false},
                {"No 'x' in Nixon", true},
                {"Are we not pure? 'No sir!' Panama`s moody Noriega brags. 'It is garbage!' Irony dooms a man; a prisoner up to new era.", true}
        });
    }

    @Test
    public void testIsPalindrom() {
        assertEquals(expected, pc.isPalindrome(str));
    }

}