package utils;

public class Location {
    private int floor;
    private int no;

    public Location() {
    }

    public static Location of(int floor, int no) {
        return new Location(floor, no);
    }

    public Location(int floor, int no) {
        this.floor = floor;
        this.no = no;
    }

    public int getFloor() {
        return floor;
    }

    public int getNo() {
        return no;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public void setNo(int no) {
        this.no = no;
    }
}
