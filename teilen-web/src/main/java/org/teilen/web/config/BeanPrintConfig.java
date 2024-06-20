package org.teilen.web.config;


import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@ComponentScan({"org/teilen/web"})
@Slf4j
public class BeanPrintConfig {

  private final ApplicationContext context;

  @Autowired
  public BeanPrintConfig(ApplicationContext context) {
    this.context = context;
  }

  @PostConstruct
  public void printBeans() {
    String[] beanNames = context.getBeanDefinitionNames();
    log.info(
        "---------------------------------After root config init --------------------------------");
    StringBuilder sb = new StringBuilder();
    if (beanNames != null && beanNames.length != 0) {
      for (String name : beanNames) {
        sb.append(name).append("\n");
      }
      log.info("", sb);
      log.info("----------------------------" + beanNames.length
          + " beans found --------------------------------");
    } else {
      log.info(
          "---------------------------------- 0 beans found ----------------------------------");
    }
  }


}
