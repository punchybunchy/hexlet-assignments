package exercise;

import java.util.Random;

// BEGIN
public class ListThread extends Thread {
    SafetyList safetyList;

    public ListThread(SafetyList safetyList) {
        this.safetyList = safetyList;
    }

    public void run() {
        Random rand = new Random();
        for (int i = 0; i <  1000; i++) {
            int randomNumber = rand.nextInt(10);
            try {
                sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            safetyList.add(randomNumber);
        }
    }
}
// END
