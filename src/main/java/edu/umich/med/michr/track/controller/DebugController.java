package edu.umich.med.michr.track.controller;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletRegistration;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;

@RestController
public class DebugController {

  private final ApplicationContext applicationContext;

  public DebugController(ApplicationContext applicationContext) {
    this.applicationContext = applicationContext;
  }

  @GetMapping("/debug/beans")
  public String listH2ConsoleBeans() {
    StringBuilder result = new StringBuilder();
    result.append("H2 Console Beans:<br/>\n");

    try {
      String[] beanNames = applicationContext.getBeanNamesForType(Object.class);
      for (String name : beanNames) {
        if (name.toLowerCase().contains("h2") || name.toLowerCase().contains("console")) {
          result.append("- ").append(name).append("<br/>\n");
        }
      }

      result.append("<br/>\nServlet Mappings:<br/>\n");
      ServletContext servletContext = ((WebApplicationContext) applicationContext).getServletContext();
      if (servletContext != null) {
        ServletRegistration h2Console = servletContext.getServletRegistration("h2-console");
        if (h2Console != null) {
          result.append("H2 Console mappings: ").append(h2Console.getMappings()).append("<br/>\n");
        } else {
          result.append("No H2 Console servlet registered!<br/>\n");
        }
      }
    } catch (Exception e) {
      result.append("Error: ").append(e.getMessage());
    }

    return result.toString();
  }
}
