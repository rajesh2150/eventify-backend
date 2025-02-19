package com.mphasis.eventify3.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mphasis.eventify3.entity.Attendee;
import com.mphasis.eventify3.entity.Organizer;
import com.mphasis.eventify3.service.UserService;

@RestController
@RequestMapping("/eventify/user")
@CrossOrigin("*")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	
	
	@PostMapping("/attendee/register")
	public Attendee registerAttendee(@RequestBody Attendee attendee) {
		
		return userService.registerAttendee(attendee);
		
	}

	@PostMapping("/organizer/register")
	public Organizer registerOrganizer(@RequestBody Organizer organizer) {
		
		return userService.registerOrganizer(organizer);
		
	}
	
	
//	@GetMapping("/attendee/register/{email}/{pwd}")
//	public String loginAttendee(@PathVariable String email, @PathVariable String pwd) {
//		
//		String isAttendee =  userService.loginAttendee(email,pwd);
//		
//		if(isAttendee !=null) {
//		
//			return isAttendee;
//		}
//		else {
//			return isAttendee;
//		}
//		 
//		}
//	
//	
//	@GetMapping("/organizer/register/{email}/{pwd}")
//	public String loginOrganizer(@PathVariable String email, @PathVariable String pwd) {
//		
//		String isAttendee =  userService.loginAttendee(email,pwd);
//		
//		if(isAttendee !=null) {
//		
//			return isAttendee;
//		}
//		else {
//			return isAttendee;
//		}
//		 
//		}
	@GetMapping("/attendee/register/{email}/{pwd}")
    public Map<String, String> loginAttendee(@PathVariable String email, @PathVariable String pwd) {
        String role = userService.loginAttendee(email, pwd);
        Map<String, String> response = new HashMap<>();
        response.put("role", role != null ? role : "Invalid credentials");
        return response;
    }

    @GetMapping("/organizer/register/{email}/{pwd}")
    public Map<String, String> loginOrganizer(@PathVariable String email, @PathVariable String pwd) {
        String role = userService.loginOrganizer(email, pwd);
        Map<String, String> response = new HashMap<>();
        response.put("role", role != null ? role : "Invalid credentials");
        return response;
    }
}
