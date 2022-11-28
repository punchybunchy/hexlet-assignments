package exercise;

// BEGIN
public class Cottage implements Home {
    private double area;
    private int floorCount;

public Cottage(double area, int floorCount) {
    this.area = area;
    this.floorCount = floorCount;
}

    @Override
    public double getArea() {
        return area;
    }

    @Override
    public int compareTo(Home another) {
        int result;

        if (this.getArea() == another.getArea()) {
            result = 0;
        }
        else {
            result = this.getArea() < another.getArea() ? -1 : 1;
        }
        return result;
    }

    @Override
    public String toString() {
        String formattedNumber = String.format("%.1f", this.getArea()).replace(",", ".");
        return String.format("%d этажный коттедж площадью %s метров", floorCount, formattedNumber);
    }
}
// END
