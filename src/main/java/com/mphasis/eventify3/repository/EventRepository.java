package com.mphasis.eventify3.repository;


import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mphasis.eventify3.entity.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {

    // Existing method using Spring Data JPA method naming conventions
    List<Event> findByEventType(String type);

    // Custom query using @Query
    @Query("SELECT e FROM Event e WHERE e.eventType = :type")
    List<Event> findEventsByType(@Param("type") String type);
    
    // Existing method using Spring Data JPA method naming conventions
    List<Event> findByEventLocation(String location);

    // Custom query using @Query
    @Query("SELECT e FROM Event e WHERE e.eventLocation = :location")
    List<Event> findEventsByLocation(@Param("location") String location);

    // Existing method using Spring Data JPA method naming conventions
    List<Event> findByEventStartTimeBetween(LocalDateTime start, LocalDateTime end);

    // Custom query using @Query
    @Query("SELECT e FROM Event e WHERE e.eventStartTime BETWEEN :start AND :end")
    List<Event> findEventsByStartTimeRange(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);
}
