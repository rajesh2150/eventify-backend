package com.mphasis.eventify3.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mphasis.eventify3.entity.Attendee;
import com.mphasis.eventify3.entity.Organizer;
import com.mphasis.eventify3.entity.OrganizerRevenue;
import com.mphasis.eventify3.entity.TicketBooking;

@Repository
public interface OrganizerRepository extends JpaRepository<Organizer, Integer> {


	@Query("SELECT a FROM Attendee a WHERE a.attendeeId IN (SELECT tb.attendee.attendeeId FROM TicketBooking tb JOIN Event e  on tb.event=e.eventId where e.eventId = :id)")
	    List<Attendee> findAllByEventId(int id);
	
	@Query(" select tb from TicketBooking tb join Event e on tb.event=e.eventId  where e.eventId= :id")
	List<TicketBooking> findAllTicketBookingsByEventId(int id);
	
	
	@Query(value = "select revenue from organizer_revenue where  organizer_id in (select organizer_id from events where  event_id= :id);",nativeQuery = true)
	double showAllRevenueByEvent(int id);
		
	
	
	@Query("select a.organizerName,a.organizerEmail,a.organizerPhoneNumber,a.organizerIsSuspended,a.role from Organizer a")
	List<Object[]> findAllOrganizer();
	
	@Query(value="select organizer_id from organizer where organizer_email = :email",nativeQuery = true)
	int findOrganizerIdByEmail(@Param("email") String email);
}  
