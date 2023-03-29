package com.example.demo.events;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceSupport;

public class EventResource extends Resource<Event> {

  public EventResource(Event event, Link... links) {
    super(event, links);
//    add(new Link("http://localhost:8080/api/events"+event.getId()));
    add(linkTo(EventController.class).slash(event.getId()).withSelfRel()); //ㅊㅜ천
  }
//  @JsonUnwrapped
//  private Event event;
//  public EventResource(Event event){
//    this.event = event;
//  }
//  public Event getEvent(){
//    return event;
//  }

}
