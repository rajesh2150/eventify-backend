package com.mphasis.eventify3.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mphasis.eventify3.entity.Event;
import com.mphasis.eventify3.entity.Feedback;
import com.mphasis.eventify3.entity.TicketBooking;
import com.mphasis.eventify3.service.AttendeeService;

//import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api/attendee")
@CrossOrigin("*")
public class AttendeeController {

    @Autowired
    private AttendeeService attendeeService;
    
  
    @GetMapping("/getalleventsbytype/{type}")
    public ResponseEntity<List<Event>> getAllEventsByEventType(@PathVariable String type) {
        try {
            List<Event> events = attendeeService.getAllEventsByEventType(type);
            if (events.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content
            }
            return new ResponseEntity<>(events, HttpStatus.OK); // 200 OK
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // 500 Internal Server Error
        }
    }
    
    
    

    
    @GetMapping("/getalleventsbydate/{date}") // we need to pass as 2025-02-19 10:05:49.005000
    public ResponseEntity<List<Event>> getAllEventsByEventDate(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date) {
        try {
            List<Event> events = attendeeService.getAllEventsByEventDate(date);
            if (events.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content
            }
            return new ResponseEntity<>(events, HttpStatus.OK); // 200 OK
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // 500 Internal Server Error
        }
    }
    
    
    
    @GetMapping("/getalleventsbylocation/{location}")
    public ResponseEntity<List<Event>> getAllEventsByEventLocation(@PathVariable String location) {
        try {
            List<Event> events = attendeeService.getAllEventsByEventLocation(location);
            if (events.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content
            }
            return new ResponseEntity<>(events, HttpStatus.OK); // 200 OK
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // 500 Internal Server Error
        }
    }

    
    

    
    
    @PostMapping("/buyticket")
    public ResponseEntity<?> buyTicket(@RequestBody TicketBooking ticketBooking) {
        try {
            TicketBooking bookedTicket = attendeeService.bookTicket(ticketBooking);
            if (bookedTicket != null) {
                return new ResponseEntity<>(bookedTicket, HttpStatus.CREATED); // 201 Created
            } else {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST); 
        }
    }
    
    
    
//    @PostMapping("/givefeedback")
//    public Feedback buyTicket(@RequestBody Feedback feedback) {
//    	return attendeeService.giveFeedback(feedback);
//    }
//    
    
    
    @PostMapping("/givefeedback")
    public ResponseEntity<?> giveFeedback(@RequestBody Feedback feedback) {
        try {
            Feedback submittedFeedback = attendeeService.giveFeedback(feedback);
            if (submittedFeedback != null) {
                return new ResponseEntity<>(submittedFeedback, HttpStatus.CREATED); // 201 Created
            } else {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // 400 Bad Request if feedback submission fails
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST); // 500 Internal Server Error
        }
    }
    
    
    @GetMapping("/getallevents")
    public List<Event> getAllEvents(){
    	List<Event> eList = attendeeService.getAllEvents();
    	return eList;
    }
 

	
    
    
    
    
    

//    // Browse events based on criteria
//    @GetMapping("/browse")
//    public List<Event> browseEvents(@RequestParam(required = false) String type,
//                                    @RequestParam(required = false) String location,
//                                    @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
//                                    @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
//        return attendeeService.browseEvents(type, location, startDate, endDate);
//    }
//
//    // Register for an event
//    @PostMapping("/register")
////    public TicketBooking registerForEvent(@RequestParam int attendeeId, @RequestParam int eventId, @RequestParam int ticketCount) {
////        return attendeeService.registerForEvent(attendeeId, eventId, ticketCount);
////    }
//
//    // View registered events
//    @GetMapping("/participations")
//    public List<TicketBooking> viewRegisteredEvents(@RequestParam int attendeeId) {
//        return attendeeService.viewRegisteredEvents(attendeeId);
//    }

    // Submit feedback after event
//    @PostMapping("/feedback")
//    public Feedback submitFeedback(@RequestParam int attendeeId, @RequestParam int eventId, @RequestParam double rating, @RequestParam String comment) {
//        return attendeeService.submitFeedback(attendeeId, eventId, rating, comment);
//    }
}
