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

    LocalDateTime endEventDateTime = eventDto.getEndEventDateTime();
    if (endEventDateTime.isBefore(eventDto.getBeginEventDateTime()) ||
            endEventDateTime.isBefore(eventDto.getCloseEnrollmentDateTime()) ||
            endEventDateTime.isBefore(eventDto.getBeginEnrollmentDateTime())) {
      errors.rejectValue("endEventDateTime", "wrongValue", "endEventDateTime is wrong");
    }

    // TODO BeginEventDateTime
    LocalDateTime beginEventDateTime = eventDto.getBeginEventDateTime();
    if (beginEventDateTime.isAfter(eventDto.getEndEventDateTime()) ||
            beginEventDateTime.isBefore(eventDto.getCloseEnrollmentDateTime()) ||
            beginEventDateTime.isBefore(eventDto.getBeginEnrollmentDateTime())) {
      errors.rejectValue("beginEventDateTime", "wrongValue", "beginEventDateTime is wrong");
    }

    // TODO CloseEnrollmentDateTime
    LocalDateTime closeEnrollmentDateTime = eventDto.getCloseEnrollmentDateTime();
    if (closeEnrollmentDateTime.isAfter(eventDto.getEndEventDateTime()) ||
            closeEnrollmentDateTime.isAfter(eventDto.getBeginEventDateTime()) ||
            closeEnrollmentDateTime.isBefore(eventDto.getBeginEnrollmentDateTime())) {
      errors.rejectValue("closeEnrollmentDateTime", "wrongValue", "closeEnrollmentDateTime is wrong");
    }

  }

}
