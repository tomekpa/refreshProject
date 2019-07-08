package javavanila.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyProxiedObjectDynamicInvocationHandler implements InvocationHandler {

  private static Logger LOGGER = LoggerFactory.getLogger(MyProxiedObjectDynamicInvocationHandler.class);

  private final Object target;

  public MyProxiedObjectDynamicInvocationHandler(Object target) {
    this.target = target;
  }

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    LOGGER.info("Before method: {}", method.getName());
    Object invoke = method.invoke(target, args);
    LOGGER.info("After method: {}", method.getName());
    return invoke;
  }
}
