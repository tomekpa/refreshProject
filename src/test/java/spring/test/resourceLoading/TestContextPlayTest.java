package spring.test.resourceLoading;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import spring.test.contextOne.ContextOneInterface;
import spring.test.contextTre.ContextTreInterface;
import spring.test.contextTwo.ContextTwoInterface;

import java.io.IOException;
import java.net.URL;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class TestContextPlayTest {

    @Autowired
    ClassWithResources classWithResources;

    @Test
    public void contextUp() throws IOException {
        URL url = classWithResources.testTemplate();

        Object content = url.getContent();

        System.out.println((String)content);
        System.out.println("done");
    }

    @Configuration
    @ImportResource("classpath:my-config.xml")
    static class TestContext {

    }
}
