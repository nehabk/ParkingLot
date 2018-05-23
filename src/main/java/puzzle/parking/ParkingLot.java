package puzzle.parking;

import puzzle.parking.model.Car;

import static java.lang.String.format;
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
        if (slotToVacate <= 0 || slotToVacate > totalNumberOfSpaces)
            return false;

        cars[slotToVacate - 1] = null;
        return true;
    }

    public String getStatus() {
        StringBuilder parkingLotStatus = new StringBuilder();
        parkingLotStatus.append("Slot No.\tRegistration No\tColor");

        range(1, totalNumberOfSpaces)
                .filter(i -> cars[i - 1] != null)
                .forEach(i -> parkingLotStatus.append(format("\n%s\t%s", i, cars[i - 1])));

        return parkingLotStatus.toString();
    }

    public String[] getRegistrationNumbersForColor(String color) {
        return new String[0];
    }
}
