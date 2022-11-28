package exercise;

import java.util.Locale;

// BEGIN
public class Flat implements Home {
    private double area;
    private double balconyArea;
    private int floor;

    public Flat(double area, double balconyArea, int floor) {
        this.area = area;
        this.balconyArea = balconyArea;
        this.floor = floor;
    }

    @Override
    public double getArea() {
        return (area + balconyArea);
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
        return String.format("Квартира площадью %s метров на %d этаже", formattedNumber, floor);
    }
}
// END
