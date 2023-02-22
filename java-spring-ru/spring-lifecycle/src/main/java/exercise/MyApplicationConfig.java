package exercise;

import java.time.LocalDateTime;

import exercise.daytimes.Daytime;
import exercise.daytimes.Morning;
import exercise.daytimes.Day;
import exercise.daytimes.Evening;
import exercise.daytimes.Night;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// BEGIN
@Configuration
public class MyApplicationConfig {

    @Bean
    public Daytime getDaytime() {
        int hour = LocalDateTime.now().getHour();
        if (hour >= 6 && hour < 12) {
            return getMorning();
        } else if (hour >= 12 && hour < 18) {
            return getDay();
        } else if (hour >= 18 && hour < 23) {
            return getEvening();
        } else {
            return getNight();
        }
    }

    public Morning getMorning() {
        return new Morning();
    }

    public Day getDay() {
        return new Day();
    }

    public Evening getEvening() {
        return new Evening();
    }

    public Night getNight() {
        return new Night();
    }
}

// END
