package com.mphasis.eventify3.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mphasis.eventify3.entity.Event;
import com.mphasis.eventify3.entity.Feedback;
import com.mphasis.eventify3.entity.Ticket;
import com.mphasis.eventify3.entity.TicketBooking;
import com.mphasis.eventify3.exception.AttendeeExceptionHandler;
import com.mphasis.eventify3.repository.AttendeeRepository;
import com.mphasis.eventify3.repository.EventRepository;
import com.mphasis.eventify3.repository.FeedbackRepository;
import com.mphasis.eventify3.repository.TicketBookingRepository;
//import com.mphasis.eventify3.repository.FeedbackRepository;
//import com.mphasis.eventify3.repository.TicketBookingRepository;

@Service
public class AttendeeService {

	@Autowired
	private AttendeeRepository attendeeRepo;

	@Autowired
	private TicketBookingRepository ticketBookingRepo;

	@Autowired
	private FeedbackRepository feedbackRepository;

	@Autowired
	private EventRepository eventRepository;

	public List<Event> getAllEventsByEventType(String type) {
		return attendeeRepo.findAllEventByEventType(type);
	}

	public List<Event> getAllEventsByEventDate(LocalDateTime date) {
		return attendeeRepo.findAllEventByEventDate(date);
	}

	public List<Event> getAllEventsByEventLocation(String location) {
		return attendeeRepo.findAllEventByEventLocation(location);
	}

	public TicketBooking bookTicket(TicketBooking ticketBooking) {
		return ticketBookingRepo.save(ticketBooking);
	}

	public Feedback giveFeedback(Feedback feedback) throws AttendeeExceptionHandler {

		boolean isPresent = feedbackRepository.existsById(feedback.getFeedbackId());

		boolean isEvent = eventRepository.existsById(feedback.getEventId());

		boolean isAttendee = attendeeRepo.existsById(feedback.getAttendeeId());

		if (!isPresent && isEvent && isAttendee) {
			return feedbackRepository.save(feedback);
		}

		if (!isEvent) {
			throw new AttendeeExceptionHandler("Event is Not Found");
		}
		if (!isPresent) {
			throw new AttendeeExceptionHandler("Already Feedback Given");
		}
		if (!isAttendee) {
			throw new AttendeeExceptionHandler("Attendee is Not Found");
		}

		else {
			throw new AttendeeExceptionHandler("Not Valid Details");
		}

	}

	// Browse events by type, location, or date
//    public List<Event> browseEvents(String type, String location, LocalDateTime startDate, LocalDateTime endDate) {
//        if (type != null) {
//            return eventRepository.findByEventType(type);
//        } else if (location != null) {
//            return eventRepository.findByEventLocation(location);
//        } else if (startDate != null && endDate != null) {
//            return eventRepository.findByEventStartTimeBetween(startDate, endDate);
//        }
//        return eventRepository.findAll(); // Default case: fetch all events
//    }

//    // Register for an event (purchase tickets)
//    public TicketBooking registerForEvent(int attendeeId, int eventId, int ticketCount) {
//        Event event = eventRepository.findById(eventId).orElseThrow(() -> new RuntimeException("Event not found"));
//        Ticket ticket = event.getTicket();  // Assuming we fetch the ticket details for event
//
//        if (ticket.getCount() < ticketCount) {
//            throw new RuntimeException("Not enough tickets available");
//        }
//
//        TicketBooking booking = new TicketBooking();
//        booking.setAttendeeId(attendeeId);
//        booking.setEventId(eventId);
//        booking.setTotalCost(ticket.getTicketPrice() * ticketCount);
//        booking.setBookingDate(LocalDateTime.now());
//        booking.setStatus("CONFIRMED");
//        return ticketBookingRepository.save(booking);
//    }

	// Manage participation: View registered events
//    public List<TicketBooking> viewRegisteredEvents(int attendeeId) {
//        return ticketBookingRepository.findByAttendeeId(attendeeId);
//    }

//    // Submit feedback after event
//    public Feedback submitFeedback(int attendeeId, int eventId, double rating, String comment) {
//        Feedback feedback = new Feedback();
//        feedback.setAttendeeId(attendeeId);
//        feedback.setEventId(eventId);
//        feedback.setRating(rating);
//        feedback.setComment(comment);
//        return feedbackRepository.save(feedback);
//    }
}
