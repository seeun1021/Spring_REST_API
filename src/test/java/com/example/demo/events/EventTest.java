package com.example.demo.events;

import static org.assertj.core.api.Assertions.assertThat;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class EventTest {

  @Test
  public void builder() {
    Event event = Event.builder().name("Inflear Spring REST API")
        .description("REST API development with Spring").build();
    assertThat(event).isNotNull();
  }

  @Test
  public void javaBean() {

    String name = "Event";
    String description = "Spring";

    Event event = new Event();
    event.setName(name);
    event.setDescription("Spring");

    assertThat(event.getName()).isEqualTo(name);
    assertThat(event.getDescription()).isEqualTo(description);

  }

  @Test
//  @Parameters({"0,0,true","100,0,false","0,100,true"})
//  @Parameters(method = "parametersForTestFree")
  @Parameters
  public void testFree(int basePrice, int maxPrice, boolean isFree) {
    //Given
    Event event = Event.builder().basePrice(basePrice).maxPrice(maxPrice).build();

    //When
    event.update();
    //Then
    assertThat(event.isFree()).isEqualTo(isFree);
//
//    //Given
//    event = Event.builder().basePrice(100).maxPrice(0).build();
//
//    //When
//    event.update();
//    //Then
//    assertThat(event.isFree()).isFalse();
//
//    //Given
//    event = Event.builder().basePrice(0).maxPrice(100).build();
//
//    //When
//    event.update();
//    //Then
//    assertThat(event.isFree()).isFalse();
  }

  private Object[] parametersForTestFree() {
    return new Object[]{
        new Object[]{0, 0, true},
        new Object[]{100, 0, false},
        new Object[]{0, 100, false},
        new Object[]{100, 200, false}
    };
  }

  @Test
  @Parameters
  public void testOffline(String location, boolean isOffline) {
    //Given
    Event event = Event.builder().location(location).build();

    //When
    event.update();
    //Then
    assertThat(event.isOffline()).isEqualTo(isOffline);

//    //Given
//     event = Event.builder().build();
//
//    //When
//    event.update();
//    //Then
//    assertThat(event.isOffline()).isFalse();

  }

  private Object[] parametersForTestOffline() {
    return new Object[]{
        new Object[]{"강남", true},
        new Object[]{null, false},
        new Object[]{"    ", false}
    };

  }

  ;
}