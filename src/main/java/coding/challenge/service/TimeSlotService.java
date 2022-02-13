package coding.challenge.service;

import coding.challenge.model.TimeSlot;
import coding.challenge.model.TimeSlotRepository;
import org.hibernate.validator.constraints.Range;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@Service
@Validated
public class TimeSlotService {
    
    private final TimeSlotRepository timeSlotRepository;

    public TimeSlotService(TimeSlotRepository timeSlotRepository) {
        this.timeSlotRepository = timeSlotRepository;
    }

    /**
     * Book a timeslot on the given day and hour for the given User.
     * 
     * <ul>
     * <li>A time slot can be booked by up to 8 users at the same time.
     * <li>One user can only book one-time slot per day.
     * <li>One user can book one-time slot every day.
     * </ul>
     * 
     * @param day Day to book (0=monday -> 6=Sunday)
     * @param hour Hour to book (0-24)
     * @param userId The user Id
     *               
     * @return The saved TimeSlot on success, null otherwise.
     */
    public TimeSlot bookTimeSlot(
            @Range(min=0, max=6) Integer day,
            @Range(min=0, max=23) Integer hour,
            @NotNull Integer userId
) {
        
        // check if user already booked that day.
        TimeSlot userDaySlot = timeSlotRepository.getByDayAndUserIdOrderByHour(day, userId);
        if (userDaySlot != null) {
            return null;
        }
            
        List<TimeSlot> usedSlots = timeSlotRepository.getAllByDayAndHour(day, hour);

        //If we already have 8 slots booked, we won't be able to book more.
        if (usedSlots.size() >= 8) {
            return null;
        }
        
        Optional<TimeSlot> first = usedSlots.stream().filter(ts -> ts.getUserId().equals(userId)).findFirst();
        
        // This slot is already booked by the user.
        if (first.isPresent()) {
            return null;
        } else {
            // We can now book the timeslot
            TimeSlot timeSlot = new TimeSlot(day, hour, userId);
            return timeSlotRepository.save(timeSlot);
        }
    }

    /**
     * Get all the booked time slots for a given day
     * 
     * @param day Day to get (0=monday -> 6=Sunday)
     *            
     * @return A list of all booked TimeSlots.
     */
    public List<TimeSlot> getTimeSlotsForDay(
            @Range(min=0, max=6) Integer day
    ) {
        return timeSlotRepository.getAllByDayOrderByHour(day);
    }

    /**
     * Get all TimeSlots booked for a User
     * 
     * @param userId The userId to look
     *               
     * @return A list of all booked TimeSlots for the User.
     */
    public List<TimeSlot> getTimeSlotsForUser(
            @NotNull Integer userId
    ) {
        return timeSlotRepository.getAllByUserIdOrderByDay(userId);
    }
}
