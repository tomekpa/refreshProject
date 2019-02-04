package javavanila.classes.inner;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class InnerClassesTest {

    @Test
    public void shouldHaveAccessToOuterClassLocalVariableWhileNamesAreTheSame() {
        InnerClassesMyOuter outer = new InnerClassesMyOuter();
        InnerClassesMyOuter.InnerClassesMyInner innerClassesMyInner = outer.makeInner();

        assertThat(innerClassesMyInner.getInnerValue(), equalTo(88));
        assertThat(innerClassesMyInner.getOuterValue(), equalTo(7));
    }

    @Test
    public void shouldInstantiateInnerOnlyWhenOuterPresent() {
        InnerClassesMyOuter outer = new InnerClassesMyOuter();
        InnerClassesMyOuter.InnerClassesMyInner innerClassesMyInner = outer.new InnerClassesMyInner();

        assertThat(innerClassesMyInner.getOuterValue(), equalTo(outer.getOuterValue()));
    }


    @Test
    public void shouldInstantiateInnerStaticWithoutOuterPresent() {
        InnerClassesMyOuter.InnerClassesMyInnerStatic innerClassesMyInner = new InnerClassesMyOuter.InnerClassesMyInnerStatic();

        assertThat(innerClassesMyInner.getInnerValue(), equalTo(99));
    }
}