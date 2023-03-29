package com.example.demo.events;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import com.example.demo.common.ErrorsResource;
import java.net.URI;
import javax.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping(value = "/api/events", produces = MediaTypes.HAL_JSON_UTF8_VALUE)
public class EventController {

  private final EventRepository eventRepository;
  private final ModelMapper modelMapper;

  private final EventValidator eventValidator;

  @Autowired
  public EventController(EventRepository eventRepository, ModelMapper modelMapper,
      EventValidator eventValidator) {
    this.eventRepository = eventRepository;
    this.modelMapper = modelMapper;
    this.eventValidator = eventValidator;
  }

  @PostMapping
  public ResponseEntity createEvent(@RequestBody @Valid EventDto eventDto, Errors errors) {
//    Event event = Event.builder().name(eventDto.getName()).description(eventDto.getDescription())
//        .build();

    if (errors.hasErrors()) {
      return badRequest(errors);
    }

    eventValidator.validate(eventDto, errors);

    if (errors.hasErrors()) {
      return badRequest(errors);
    }

    Event event = modelMapper.map(eventDto, Event.class);
    event.update();
    Event newEvent = this.eventRepository.save(event);
    ControllerLinkBuilder selfLinkBuilder = linkTo(EventController.class).slash(newEvent.getId());
    URI createdUri = selfLinkBuilder.toUri();
    event.setId(10);
    EventResource eventResource = new EventResource(event);
    eventResource.add(linkTo(EventController.class).withRel("query-events"));
    eventResource.add(new Link("/docs/index.html#resources-events-create").withRel("profile"));
//    eventResource.add(selfLinkBuilder.withSelfRel());
    eventResource.add(selfLinkBuilder.withRel("update-event"));
    return ResponseEntity.created(createdUri).body(eventResource);
  }

  private static ResponseEntity<ErrorsResource> badRequest(Errors errors) {
    return ResponseEntity.badRequest().body(new ErrorsResource(errors));
  }
}
