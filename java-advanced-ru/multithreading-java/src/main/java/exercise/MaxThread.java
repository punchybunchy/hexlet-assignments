package exercise;

import java.util.Arrays;

// BEGIN
public class MaxThread extends Thread {
    int[] numbers;
    int max;

    public MaxThread(int[] numbers) {
        this.numbers = numbers;
    }

    public void run() {
        int max = Arrays.stream(this.numbers).max().getAsInt();
        setMax(max);
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }
}
// END
