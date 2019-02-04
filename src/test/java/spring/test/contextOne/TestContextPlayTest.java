package spring.test.contextOne;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import spring.test.contextTre.ContextTreInterface;
import spring.test.contextTwo.ContextTwoInterface;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class TestContextPlayTest {

    @Autowired
    ContextOneInterface contextOneInterface;

    @Autowired
    ContextTwoInterface contextTwoInterface;

    @Autowired
    @Qualifier("contextTwo")
    ContextTwoInterface contextTwoInterface2;

    @Autowired
    @Qualifier("contextTre")
    ContextTreInterface contextTreInterface;

    @Autowired
    @Qualifier("contextTreConst")
    ContextTreInterface contextTreInterface2;

    @Test
    public void contextUp() {
        System.out.println(contextOneInterface.foo());
        System.out.println(contextTwoInterface.foo());
        System.out.println(contextTwoInterface2.foo());
        System.out.println(contextTreInterface.foo());
        System.out.println(contextTreInterface2.foo());
        System.out.println("done");
    }

    @Configuration
    @ComponentScan(basePackageClasses = {ContextOneInterface.class, ContextTwoInterface.class})
    @ImportResource("classpath:my-config.xml")
    static class TestContext {

        @Primary
        @Bean
        ContextTwoInterface contextUp() {
            ContextTwoInterface test = mock(ContextTwoInterface.class);
            when(test.foo()).thenReturn("ContextTwoImplMock");
            return test;
        }
    }
}