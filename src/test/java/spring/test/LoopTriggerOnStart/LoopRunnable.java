package spring.test.LoopTriggerOnStart;

import spring.test.LoopTriggerOnStart.InfiniteLoopInBeanTest.SomeApiClass;

/**
 * Created by tomasz.pasieka on 21.02.2019.
 */
public class LoopRunnable implements Runnable {

  SomeApiClass someApiClass;

  LoopRunnable(SomeApiClass someApiClass) {
    this.someApiClass = someApiClass;
  }

  Boolean triggerMe = false;

  @Override
  public void run() {
    System.out.println("INIT");
    while (true) {
      System.out.println("INSIDE LOOP");
      if (triggerMe) {
        System.out.println("YOUPEEEEEEEEEEEEEEEEEEEEEE");
        someApiClass.apiCall();
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        triggerMe = false;
      }
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
