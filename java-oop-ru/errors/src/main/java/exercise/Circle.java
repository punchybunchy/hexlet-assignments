package exercise;

// BEGIN
public class Circle {

    private Point point;
    private int radius;

    public Circle(Point point, int radius) {
        this.point = point;
        this.radius = radius;
    }

    public int getRadius() {
        return radius;
    }

    public double getSquare() throws NegativeRadiusException {
        if (this.radius < 0) {
            throw new NegativeRadiusException("The radius is less then zero");
        }
        else {
            return Math.pow(radius, 2) * Math.PI;
        }
    }
}
// END
