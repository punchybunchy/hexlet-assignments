package exercise;

import java.util.List;
import java.util.stream.Collectors;

// BEGIN
public class App {

    public static List<String> buildAppartmentsList(List<Home> home, int number) {

//        List<String> sortedList = home.stream()
//                .sorted((o1, o2) -> (int) (o1.getArea() - o2.getArea()))
//                .limit(number)
//                .map(Home::toString)
//                .collect(Collectors.toList());
//        return sortedList;

        return home.stream()
                .sorted(Home::compareTo)
                .limit(number)
                .map(Home::toString)
                .collect(Collectors.toList());
    }
}
// END
