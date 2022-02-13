package coding.challenge.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TimeSlotRepository extends JpaRepository<TimeSlot, Integer> {
    
    List<TimeSlot> getAllByDayAndHour(int day, int hour);
    
    TimeSlot getByDayAndUserIdOrderByHour(int day, int userId);

    List<TimeSlot> getAllByDayOrderByHour(int day);
    
    List<TimeSlot> getAllByUserIdOrderByDay(int userId);
    
}