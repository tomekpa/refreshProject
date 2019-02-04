package javavanila.dispatch;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Dispatch is the way a language links calls to function/method definitions.
 *
 * In java, a class may have multiple methods with the same name but different parameter types,
 * and the language specifies that method calls are dispatched to the method with the right number of
 * parameters that has the most specific types that the actual parameters could match. That's static dispatch.
 */
@RunWith(MockitoJUnitRunner.class)
public class DispatchTest {

    @Test
    public void shouldBeDynamicallyDispatchedWhenRunInstanceMethod() throws Exception {
        DispatchBaseClass clazz = new DispatchBaseClass();
        clazz.foo();
        clazz = new DispatchDerivedClass();
        assertThat(clazz.foo(), equalTo(Dispatch.DERIVED));
    }

    @Test
    public void shouldBeStaticallyDispatchedWhenRunStaticMethod() throws Exception {
        DispatchBaseClass baseClazz = new DispatchDerivedClass();
        DispatchDerivedClass derivedClazz = new DispatchDerivedClass();

        assertThat(baseClazz.staticFoo(), equalTo(Dispatch.BASE));
        assertThat(derivedClazz.staticFoo(), equalTo(Dispatch.DERIVED));

        assertThat(baseClazz.foo(), equalTo(Dispatch.DERIVED));
        assertThat(derivedClazz.foo(), equalTo(Dispatch.DERIVED));
    }
}