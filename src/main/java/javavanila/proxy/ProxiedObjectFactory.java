package javavanila.proxy;

import java.lang.reflect.Proxy;

class ProxiedObjectFactory {
  static ProxiedObjectInterface get(ProxiedObjectInterface proxiedObjectInterface) {
    ProxiedObjectInterface proxyInstance = (ProxiedObjectInterface) Proxy.newProxyInstance(
        ProxiedObjectFactory.class.getClassLoader(),
        new Class[] { ProxiedObjectInterface.class },
        new MyProxiedObjectDynamicInvocationHandler(proxiedObjectInterface));
    return proxyInstance;
  }
}
