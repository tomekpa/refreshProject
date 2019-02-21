package spring.test.resourceLoading;

import org.springframework.core.io.Resource;

import java.io.IOException;
import java.net.URL;

public class ClassWithResources {
  private Resource[] templateFiles;

  public void setTemplateFiles(Resource[] templateFiles) {
    this.templateFiles = templateFiles;
  }

  URL testTemplate(){
    try {
      return templateFiles[0].getURL();
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }
}
