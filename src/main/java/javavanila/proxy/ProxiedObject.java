package javavanila.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProxiedObject implements ProxiedObjectInterface {

  private static Logger log = LoggerFactory.getLogger(ProxiedObject.class);

  public String iWasProxied() {
    String s = "iWasProxied";
    log.info(s);
    return s;
  }
  public String iWasProxiedAgain() {
    String s = "iWasProxiedAgain";
    log.info(s);
    return s;
  }
}
