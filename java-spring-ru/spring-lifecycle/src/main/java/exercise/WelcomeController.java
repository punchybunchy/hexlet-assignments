package exercise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// BEGIN
@RestController
public class WelcomeController {

    @Autowired
    private MyApplicationConfig myApplicationConfig;

    @Autowired
    private Meal meal;

    @GetMapping(path = "/daytime")
    public String greetings() {
        String dayTime = myApplicationConfig.getDaytime().getName();
        return "It is "
                + dayTime
                + " now. Enjoy your "
                + meal.getMealForDaytime(dayTime);
    }


}
// END
