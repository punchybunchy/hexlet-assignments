package exercise;

// BEGIN
public class NegativeRadiusException extends Exception {

    private String notification;

    public NegativeRadiusException(String notification) {
        this.notification = notification;
    }
}
// END
