package spring.test.resourceLoading;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourceArrayPropertyEditor;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.net.URL;

import static org.mockito.Mockito.mock;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class TestContextPlayTest {

    ResourceArrayPropertyEditor resourceArrayPropertyEditor;
//    TypeConverterDelegate typeConverterDelegate;
    ResourcePatternResolver resourcePatternResolver;
    PathMatchingResourcePatternResolver pathMatchingResourcePatternResolver;
    DefaultResourceLoader defaultResourceLoader;

    @Autowired
    MyClassWithResourceList classWithResources;

    @Test
    public void contextUp() throws IOException {

        URL url = classWithResources.testTemplate();
        String text = Resources.toString(url, Charsets.UTF_8);
        System.out.println(text);
        System.out.println("done");
    }

    @Test
    public void getTemplateFiles() throws IOException {
        URL url0 = classWithResources.getTemplateFiles()[0].getURL();
        URL url1 = classWithResources.getTemplateFiles()[1].getURL();
        URL url2 = classWithResources.getTemplateFiles()[2].getURL();
        String text = Resources.toString(url0, Charsets.UTF_8);
        System.out.println(text);
        text = Resources.toString(url1, Charsets.UTF_8);
        System.out.println(text);
        text = Resources.toString(url2, Charsets.UTF_8);
        System.out.println(text);
        System.out.println("done");
    }


    @Test
    public void getNumebrOfTemplates() throws IOException {
        System.out.println(classWithResources.getTemplateFiles().length);
        System.out.println("done");
    }

    @Test
    public void getSimpleResource() throws IOException {

        URL url = Resources.getResource("myFile4.txt");
        String text = Resources.toString(url, Charsets.UTF_8);
        System.out.println(text);

        url = Resources.getResource("META-INF/myFile1.txt");
        text = Resources.toString(url, Charsets.UTF_8);
        System.out.println(text);

//        url = Resources.getResource("**/myFile1.txt");
//        text = Resources.toString(url, Charsets.UTF_8);
//        System.out.println(text);
        System.out.println("done");
    }

    @Configuration
    @ImportResource("classpath:my-config.xml")
    static class TestContext {

    }
}
