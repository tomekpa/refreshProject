package spring.test;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

@Transactional
@Rollback
@SpringBootTest(classes = TestConfiguration.class)
abstract class BaseIntegrationSpec {
}