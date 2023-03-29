package com.example.demo.index;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import com.example.demo.events.EventController;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

  @GetMapping("/api")
  public ResourceSupport index(){
    var index = new ResourceSupport();
    index.add(linkTo(EventController.class).withRel("events"));
    return  index;
  }
}
