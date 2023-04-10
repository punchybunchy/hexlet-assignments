package exercise;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import java.util.logging.Level;

class App {
    private static final Logger LOGGER = Logger.getLogger("AppLogger");

    // BEGIN

    public static Map<String, Integer> getMinMax(int[] numbers) {
        Map<String, Integer> result = new HashMap<>();

        MaxThread maxThread = new MaxThread(numbers);
        MinThread  minThread = new MinThread(numbers);
        LOGGER.log(Level.INFO, "Thread " +  maxThread.getName() + " started");
        maxThread.start();
        LOGGER.log(Level.INFO, "Thread " +  maxThread.getName() + " finished");

        LOGGER.log(Level.INFO, "Thread " +  minThread.getName() + " started");
        minThread.start();
        try {
            minThread.join();
        } catch (InterruptedException e) {
            System.out.println("Поток был прерван");
        }
        LOGGER.log(Level.INFO, "Thread " +  minThread.getName() + " finished");

        result.put("max", maxThread.getMax());
        result.put("min", minThread.getMin());
        return result;
    }
    // END
}
