package principles;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import principles.LiskovSubstitutionBreakExample.Square;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static principles.LiskovSubstitutionBreakExample.Rectangle;
import static principles.LiskovSubstitutionBreakExample.calculateArea;

@RunWith(value = SpringJUnit4ClassRunner.class)
public class LiskovSubstitutionBreakExampleTest {

    @Test
    public void shouldCorrectlyCalculateAreaForRectangle() {
        Rectangle r = new Rectangle();
        r.setLength(2);
        r.setBreadth(3);
        assertThat(calculateArea(r), is(6));
    }

    @Test
    public void shouldInCorrectlyCalculateAreaForSquare() {
        /**
         * Setting any property changes other property
         */
        Rectangle r = new Square();
        r.setLength(2);
        r.setBreadth(3);
        assertThat(calculateArea(r), not(6));
    }
}