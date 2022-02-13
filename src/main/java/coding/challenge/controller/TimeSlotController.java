package coding.challenge.controller;

import coding.challenge.model.TimeSlot;
import coding.challenge.service.TimeSlotService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("timeslot")
public class TimeSlotController {
    
    TimeSlotService timeSlotService;

    public TimeSlotController(TimeSlotService timeSlotService) {
        this.timeSlotService = timeSlotService;
    }
    
    @PostMapping("book")
    public TimeSlot bookTimeSlot(
            Integer day,
            Integer hour,
            Integer userId
    ) {
        TimeSlot timeSlot = timeSlotService.bookTimeSlot(day, hour, userId);
        
        if (timeSlot == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not book timeslot");
        } else {
            return timeSlot;
        }
    }
    
}
