package puzzle.parking.model;

public class Car {

    private String registrationNumber;
    private String color;

    public Car(String registrationNumber, String color) {
        this.registrationNumber = registrationNumber;
        this.color = color;
    }

    @Override
    public String toString() {
        return String.format("%s\t%s", registrationNumber, color);
    }

}
