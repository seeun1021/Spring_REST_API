package com.example.demo.common;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import com.example.demo.index.IndexController;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.validation.Errors;

public class ErrorsResource extends Resource<Errors> {


  public ErrorsResource(Errors content, Link... links) {
    super(content, links);
    add(linkTo(methodOn(IndexController.class).index()).withRel("index"));
  }
}
