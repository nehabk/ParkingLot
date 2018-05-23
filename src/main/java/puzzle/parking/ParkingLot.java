package puzzle.parking;

import puzzle.parking.model.Car;

import static java.util.stream.IntStream.range;

public class ParkingLot {

    private Integer totalNumberOfSpaces;
    private Car[] cars;

    public ParkingLot(Integer numberOfParkingSpaces) {
        this.totalNumberOfSpaces = numberOfParkingSpaces;
        this.cars = new Car[numberOfParkingSpaces];
    }

    public Integer getTotalNumberOfSpaces() {
        return this.totalNumberOfSpaces;
    }

    public Integer park(Car car) {
        Integer slot = range(1, totalNumberOfSpaces)
                .filter(i -> cars[i - 1] == null)
                .findFirst()
                .orElse(-1);

        if (slot > 0)
            cars[slot - 1] = car;

        return slot;
    }

    public Boolean leave(Integer slotToVacate) {
        return null;
    }
}
