package javavanila.proxy;

import org.hamcrest.Matchers;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;

public class ProxyFunTest {

  @Test
  public void shouldTestSomething() {

    ProxiedObjectInterface proxiedObjectInterface = new ProxiedObject();
    ProxiedObjectInterface proxied = ProxiedObjectFactory.get(proxiedObjectInterface);

    String s1 = proxied.iWasProxied();
    String s2 = proxied.iWasProxiedAgain();

    assertThat(s1, Matchers.is("iWasProxied"));
    assertThat(s2, Matchers.is("iWasProxiedAgain"));
  }
}