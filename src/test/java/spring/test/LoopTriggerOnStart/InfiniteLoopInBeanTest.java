package spring.test.LoopTriggerOnStart;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import spring.test.contextOne.ContextOneInterface;
import spring.test.contextTwo.ContextTwoInterface;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class InfiniteLoopInBeanTest {

  @Autowired
  LoopTriggerClass classOne;

  @Ignore
  @Test
  public void shouldTriggerInfiniteLoop() {
    System.out.println("done");
    try {
      Thread.sleep(120000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  @Configuration
  @ComponentScan(basePackageClasses = { ContextOneInterface.class, ContextTwoInterface.class })
  static class TestContext {

    @Bean
    LoopTriggerClass contextUp(TaskExecutor execute, SomeApiClass someApiClass) throws InterruptedException {
      return new LoopTriggerClass(execute, someApiClass);
    }

    @Bean
    public TaskExecutor taskExecutor() {
      return new SimpleAsyncTaskExecutor();
    }

    @Bean
    public SomeApiClass someApiClass() {
      return new SomeApiClass();
    }
  }

  static class LoopTriggerClass {
    LoopTriggerClass(TaskExecutor execute, SomeApiClass someApiClass) throws InterruptedException {
      execute.execute(new LoopRunnable(someApiClass));
    }
  }

  static class SomeApiClass {
    void apiCall() {
      System.out.println("SomeApiClass apiCall");
    }
  }
}
