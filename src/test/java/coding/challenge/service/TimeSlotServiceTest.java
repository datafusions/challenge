package coding.challenge.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TimeSlotServiceTest {

    @Autowired
    TimeSlotService service;
    
    @Test
    void canBookTimeSlot() {
        assertNotNull(service.bookTimeSlot(1, 2, 3));
    }
    
    @Test
    void cantDoubleBook() {
        assertNotNull(service.bookTimeSlot(2, 2, 3));
        assertNull(service.bookTimeSlot(2, 2, 3));
        assertNull(service.bookTimeSlot(2, 5, 3));
    }
    
    @Test
    void cantOverBook() {
        for(int i = 1; i <= 8; ++i) {
            assertNotNull(service.bookTimeSlot(3, 3, i));
        }
        assertNull(service.bookTimeSlot(3, 3, 9));
    }
    
    @Test
    void canBookEveryDay() {
        for(int i = 0; i <= 6; ++i) {
            assertNotNull(service.bookTimeSlot(i, 14, 10));
        }
    }
    
}