package javavanila.proxy;

import java.lang.reflect.Proxy;

class ProxiedObjectFactory {
  static ProxiedObjectInterfaceOne get(ProxiedObjectInterfaceOne proxiedObjectInterface) {
    ProxiedObjectInterfaceOne proxyInstance = (ProxiedObjectInterfaceOne) Proxy.newProxyInstance(
        ProxiedObjectFactory.class.getClassLoader(),
        new Class[] { ProxiedObjectInterfaceOne.class, ProxiedObjectInterfaceTwo.class },
        new MyProxiedObjectDynamicInvocationHandler(proxiedObjectInterface));
    return proxyInstance;
  }
}
