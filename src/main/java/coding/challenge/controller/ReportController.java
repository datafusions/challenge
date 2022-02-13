package coding.challenge.controller;

import coding.challenge.model.TimeSlot;
import coding.challenge.service.TimeSlotService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("report")
public class ReportController {

    TimeSlotService timeSlotService;

    public ReportController(TimeSlotService timeSlotService) {
        this.timeSlotService = timeSlotService;
    }
    
    @GetMapping("user")
    public List<TimeSlot> getUserReport(
            Integer userId
    ) {
        return timeSlotService.getTimeSlotsForUser(userId);
    }
    
    @GetMapping("day")
    public List<TimeSlot> getDayReport(
            Integer day
    ) {
        return timeSlotService.getTimeSlotsForDay(day);
    }
}

