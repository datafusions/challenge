package coding.challenge.model;

import javax.persistence.*;

@Entity
@Table(name = "time_slot")
public class TimeSlot {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer ID;
    private Integer day; // 0-Monday -> 6-Sunday
    private Integer hour;
    private Integer userId;

    public TimeSlot() {
    }

    public TimeSlot(
            Integer day,
            Integer hour,
            Integer userId
    ) {
        this.day = day;
        this.hour = hour;
        this.userId = userId;
    }

    public Integer getID() {
        return ID;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public Integer getHour() {
        return hour;
    }

    public void setHour(Integer hour) {
        this.hour = hour;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}