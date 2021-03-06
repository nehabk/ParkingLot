package puzzle.parking;

import puzzle.parking.model.Car;

import java.util.Objects;

import static java.lang.String.format;
import static java.util.Arrays.stream;
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
        Integer slot = range(1, totalNumberOfSpaces+1)
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

        range(1, totalNumberOfSpaces+1)
                .filter(i -> cars[i - 1] != null)
                .forEach(i -> parkingLotStatus.append(format("\n%s\t%s", i, cars[i - 1])));

        return parkingLotStatus.toString();
    }

    public String[] getRegistrationNumbersForColor(String color) {
        return stream(cars)
                .filter(Objects::nonNull)
                .filter(car -> color.equals(car.getColor()))
                .map(Car::getRegistrationNumber)
                .toArray(String[]::new);
    }

    public int[] getSlotNumbersForColor(String color) {
        return range(1, totalNumberOfSpaces+1)
                .filter(i -> cars[i - 1] != null)
                .filter(i -> color.equals(cars[i - 1].getColor()))
                .toArray();
    }

    public Integer getSlotNumberForRegistrationNumber(String registrationNumber) {
        return range(1, totalNumberOfSpaces+1)
                .filter(i -> cars[i - 1] != null)
                .filter(i -> registrationNumber.equals(cars[i - 1].getRegistrationNumber()))
                .findFirst()
                .orElse(-1);
    }
}
