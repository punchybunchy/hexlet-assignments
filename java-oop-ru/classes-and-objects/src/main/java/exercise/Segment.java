package exercise;

// BEGIN
public class Segment {
    private Point point1;
    private Point point2;

    public Segment (Point point1, Point point2) {
        this.point1 = point1;
        this.point2 = point2;
    }

    public Point getBeginPoint() {
        return point1;
    }

    public Point getEndPoint() {
        return point2;
    }

    public Point getMidPoint() {
        int pointCount = 2;
        int xMidPosition = (point1.getX() + point2.getX()) / pointCount;
        int yMidPosition = (point1.getY() + point2.getY()) / pointCount;
        return new Point(xMidPosition, yMidPosition);
    }
}
// END
