package edu.umich.med.michr.track.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RootController {

  private static final Logger logger = LoggerFactory.getLogger(RootController.class);

  @GetMapping("/hello")
  public ResponseEntity<String> root() {
    logger.debug("Hit the hello end point");
    System.out.println("==== DIRECT SYSTEM OUT TEST ====");
    return ResponseEntity.ok("hello!");
  }
}
