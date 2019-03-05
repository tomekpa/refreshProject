package spring.test.activeProfile;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;

import static org.hamcrest.core.Is.is;

//@ActiveProfiles(value = {"ON CLASS", "localPdfTestGeneration"})
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration

//THIS ONE IS APPLIED IN PRECEDENCE EVEN TO VM PARAMS
@TestPropertySource("classpath:activeProfile/application-context-main.properties")
public class ActiveProfileTest {

    @Autowired
    Environment springEnvironment;

    @Autowired
    @Qualifier("localPdfTestGenerationIndicator")
    Boolean localPdfTestGenerationIndicator;

    @Test
    public void shouldReturnActiveProfiles() {
        for (String s : springEnvironment.getActiveProfiles()) {
            System.out.println(s);
        }
    }

    @Test
    public void shouldApplyAndRetrieveProfileCorrectly() {
        Assert.assertThat(localPdfTestGenerationIndicator, is(true));
    }

    @ActiveProfiles(value = {"ON_TEST_CONTEXT"})
    @Configuration
    //THIS ONE HAS LOWER PRIO THAN VM OPTS
    @PropertySource("classpath:activeProfile/application-context.properties")
    //THIS ONE IS IGNORED
    @TestPropertySource("classpath:activeProfile/application-context-test.properties")
    static class TestContext {
        //ADD TO VM OPTION -Dspring.profiles.active=localPdfTestGeneration
        @Primary
        @Bean(value = "localPdfTestGenerationIndicator")
        Boolean isLocalDevelopement(Environment springEnvironment) {
            return Arrays.stream(springEnvironment.getActiveProfiles())
                    .filter(profile -> profile.contains("localPdfTestGeneration"))
                    .map(profile -> true)
                    .findFirst()
                    .orElse(false);
        }
    }
}