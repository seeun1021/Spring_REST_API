package com.example.demo.events;


import java.time.LocalDateTime;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Component
public class EventValidator {

  public void validate(EventDto eventDto, Errors errors) {
    if (eventDto.getBasePrice() > eventDto.getMaxPrice() && eventDto.getMaxPrice() > 0) {
      errors.rejectValue("basePrice", "wrongValue", "BasePrice is wrong.");
      errors.rejectValue("maxPrice", "wrongValue", "MaxPrice is wrong.");
      errors.reject("wrongPrices", "Values of prices are wrong");

    }

    LocalDateTime endEvnetDateTime = eventDto.getEndEventDateTime();
    if (endEvnetDateTime.isBefore(eventDto.getBeginEventDateTime()) ||
        endEvnetDateTime.isBefore(eventDto.getCloseEnrollmentDateTime()) ||
        endEvnetDateTime.isBefore(eventDto.getBeginEnrollmentDateTime())) {
      errors.rejectValue("endEventDateTime", "wrongValue", "endEventDateTime is wrong");
    }

    // TODO BeginEventDateTime
    // TODO CloseEnrollmentDateTime

  }

}
