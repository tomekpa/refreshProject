package javavanila.proxy;

import org.hamcrest.Matchers;
import org.junit.Test;

import java.lang.reflect.Proxy;

import static org.hamcrest.MatcherAssert.assertThat;

public class ProxyFunTest {

  @Test
  public void shouldTestSomething() {

    ProxiedObjectInterface proxiedObjectInterface = new ProxiedObject();

    ProxiedObjectInterface proxyInstance = (ProxiedObjectInterface) Proxy.newProxyInstance(
        ProxyFunTest.class.getClassLoader(),
        new Class[] { ProxiedObjectInterface.class },
        new MyProxiedObjectDynamicInvocationHandler(proxiedObjectInterface));

    String s1 = proxyInstance.iWasProxied();
    String s2 = proxyInstance.iWasProxiedAgain();

    assertThat(s1, Matchers.is("iWasProxied"));
    assertThat(s2, Matchers.is("iWasProxiedAgain"));
  }
}
