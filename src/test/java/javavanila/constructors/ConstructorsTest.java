package javavanila.constructors;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * Static items are initialised in order they were put in code
 */
@RunWith(MockitoJUnitRunner.class)
public class ConstructorsTest {

    @Test
    public void shouldShowHowObjectsElementsAreInitialised() throws Exception {
        new ConstructorsSub().hello();
        System.out.printf("%s\n", "------------");
        new ConstructorsSub().hello();
    }
}