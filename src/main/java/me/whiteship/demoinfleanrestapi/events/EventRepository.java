package me.whiteship.demoinfleanrestapi.events;

import java.time.LocalDateTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface EventRepository extends JpaRepository<Event, Integer>, JpaSpecificationExecutor<Event> {
  Page<Event> findByBasePriceBetween(int startBasePrice, int endBasePrice, Pageable pageable);

  Page<Event> findByBasePriceBetweenAndBeginEnrollmentDateTimeIsBeforeAndCloseEnrollmentDateTimeIsAfter(
      int minBasePrice, int maxBasePrice, LocalDateTime before, LocalDateTime after, Pageable pageable);

}
