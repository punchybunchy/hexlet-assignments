package exercise;

import java.util.Arrays;

// BEGIN
public class MinThread extends Thread {
    int[] numbers;
    int min;

    public MinThread(int[] numbers){
        this.numbers = numbers;
    }

    public void run() {
        int min = Arrays.stream(this.numbers).min().getAsInt();
        setMin(min);
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }
}
// END
