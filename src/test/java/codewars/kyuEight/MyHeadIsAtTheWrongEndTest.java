package codewars.kyuEight;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class MyHeadIsAtTheWrongEndTest {
    @Test
    public void exampleTest1() {
        assertArrayEquals(new String[]{"head", "body", "tail"},
                MyHeadIsAtTheWrongEnd.fixTheMeerkat(new String[]{"tail", "body", "head"}));
    }

    @Test
    public void exampleTest2() {
        assertArrayEquals(new String[]{"heads", "body", "tails"},
                MyHeadIsAtTheWrongEnd.fixTheMeerkat(new String[]{"tails", "body", "heads"}));
    }


    @Test
    public void exampleTest3() {
        assertArrayEquals(new String[]{"top", "middle", "bottom"},
                MyHeadIsAtTheWrongEnd.fixTheMeerkat(new String[]{"bottom", "middle", "top"}));
    }

    @Test
    public void exampleTest4() {
        assertArrayEquals(new String[]{"upper legs", "torso", "lower legs"},
                MyHeadIsAtTheWrongEnd.fixTheMeerkat(new String[]{"lower legs", "torso", "upper legs"}));
    }

    @Test
    public void exampleTest5() {
        assertArrayEquals(new String[]{"sky", "rainbow", "ground"},
                MyHeadIsAtTheWrongEnd.fixTheMeerkat(new String[]{"ground", "rainbow", "sky"}));
    }
}