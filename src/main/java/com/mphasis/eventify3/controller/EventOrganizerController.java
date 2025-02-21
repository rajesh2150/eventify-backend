package com.mphasis.eventify3.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mphasis.eventify3.entity.Attendee;
import com.mphasis.eventify3.entity.Event;
import com.mphasis.eventify3.entity.TicketBooking;
import com.mphasis.eventify3.exception.EventOrganizerExceptionHandler;
import com.mphasis.eventify3.service.EventOrganizerService;

@RestController
@RequestMapping("/eventify/organizer")
@CrossOrigin("*")
public class EventOrganizerController {
	
	
	

	@Autowired
	private EventOrganizerService es;

	

	 @GetMapping("/getallattendeesbyeventid/{id}")
	    public ResponseEntity<?> getAllAttendeesByEventId(@PathVariable int id) {
	        List<Attendee> attendees = es.getAllAttendeesByEventId(id);
	        if (attendees.isEmpty()) {
	            return new ResponseEntity<>("No Attendees for This Event",HttpStatus.NO_CONTENT); // 204 No Content
	        }
	        return new ResponseEntity<>(attendees, HttpStatus.OK); // 200 OK
	    }
	
	

	 @PostMapping("/addevent")
	    public ResponseEntity<Event> addEvent(@RequestBody Event event) throws EventOrganizerExceptionHandler {
	        Event createdEvent = es.addEvent(event);
	       
	        	
	        if(createdEvent !=null) {	        	
	        	return new ResponseEntity<>(createdEvent, HttpStatus.CREATED); // 201 Created
	        }
	        else {
	        	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }	        
	           
	    }
	
//	@PutMapping("/updateevent")
//	public Event updateEvent(@RequestBody Event event) {
//		return es.updateEvent(event);
//	}
//	
	 
	 
	 @PutMapping("/updateevent")
	    public ResponseEntity<?> updateEvent(@RequestBody Event event) throws EventOrganizerExceptionHandler {
	        
	        try {
	        	Event updatedEvent = es.updateEvent(event);
	        	 if (updatedEvent == null) {
	 	            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Not Found
	 	        }
	 	        else {	        	
	 	        	return new ResponseEntity<>(updatedEvent, HttpStatus.OK); // 200 OK
	 	        }
	        }catch(Exception e) {
	        	return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
	        }
	       
	    }
	

	
	
	 

	    @DeleteMapping("/deleteevent/{id}")
	    public ResponseEntity<?> deleteEvent(@PathVariable int id) throws EventOrganizerExceptionHandler {
	    	
	    	try {
	    		Optional<Event> event = es.deleteEvent(id);
		        if (!event.isPresent()) {
		            return new ResponseEntity<>(event,HttpStatus.OK); // 200 OK
		        }
		        return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Not Found
	    		
	    	}catch(Exception e) {
	    		throw new EventOrganizerExceptionHandler("Not a Valid Event To delete");
	    	}
	        
	    }
	
	
	    @GetMapping("/getallticketbookingsbyeventid/{id}")
	    public ResponseEntity<List<TicketBooking>> getAllTicketBookingsByEventId(@PathVariable int id) throws EventOrganizerExceptionHandler {
	       
	        List<TicketBooking> ticketBookings = es.getAllTicketBookingsByEventId(id);
	        
	       
	        if (ticketBookings.isEmpty()) {
	            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content
	        }
	        
	 
	        return new ResponseEntity<>(ticketBookings, HttpStatus.OK); // 200 OK
	    }

	    
	    
	
	@GetMapping("/showeventrevenue/{id}")
	public double showAllRevenueByEvent(@PathVariable("id") int id) {
		return es.showAllRevenueByEvent(id);
	}
	
	
	
	@GetMapping("/event/{eventTitle}")
	public Event getByEventTitle(@PathVariable("eventTitle") String eventTitle) {
		System.out.println(eventTitle     );
		Event e= es.getByEventTitle(eventTitle);
		return e;
		
	}
	
	@GetMapping("/{email}")
    public Map<String,Integer> getOrganizerIdByEmail(@PathVariable String email) {
    	int id= es.getOrganizerIdByEmail(email);
    	HashMap<String, Integer> respose=new HashMap<>();
    	respose.put("id", id);
    	
    	System.out.println(respose);
    	return respose;
    }
	
	    
//	    @GetMapping("/showeventrevenue/{id}")
//	    public ResponseEntity<Double> showAllRevenueByEvent(@PathVariable("id") int id) {
//	        double revenue = es.showAllRevenueByEvent(id);
//	        if (revenue == 0.0) {
//	            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content if revenue is 0
//	        }
//	        return new ResponseEntity<>(revenue, HttpStatus.OK); // 200 OK
//	    }
//	
	
}
