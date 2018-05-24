package puzzle.parking.model;

import puzzle.parking.ParkingLot;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.lang.String.format;

public enum Command {

    CREATE_PARKING_LOT("^create_parking_lot ([0-9]+)") {
        @Override
        public void execute(String[] args) {
            int numberOfParkingSpaces = Integer.parseInt(args[1]);
            parkingLot = new ParkingLot(numberOfParkingSpaces);

            System.out.println(format("Created a parking lot with %s slots", numberOfParkingSpaces));
        }
    },

    PARK("^park (.*) (.*)") {
        public void execute(String[] args) {
            String registrationNumber = args[1];
            String color = args[2];
            Car car = new Car(registrationNumber, color);
            Integer slot = parkingLot.park(car);

            if (slot > 0)
                System.out.println(format("Allocated slot number: %s", slot));
            else
                System.out.println("Sorry, parking lot is full");
        }
    },

    LEAVE("^leave ([0-9]+)") {
        public void execute(String[] args) {
            Integer slotToVacate = Integer.parseInt(args[1]);
            parkingLot.leave(slotToVacate);

            System.out.println(format("Slot number %s is free", slotToVacate));
        }
    },

    STATUS("^status") {
        public void execute(String[] args) {
            System.out.println(parkingLot.getStatus());
        }
    },

    REGISTRATION_NUMBERS_FOR_CARS_WITH_COLOR("^registration_numbers_for_cars_with_colour (.*)") {
        public void execute(String[] args) {
            String registrationNumber = args[1];
            String[] registrationNumbers = parkingLot.getRegistrationNumbersForColor(registrationNumber);
            System.out.println(String.join(", ", registrationNumbers));
        }
    },

    SLOT_NUMBERS_FOR_CARS_WITH_COLOR("^slot_numbers_for_cars_with_colour (.*)") {
        public void execute(String[] args) {
            String registrationNumber = args[1];
            int[] slotNumbers = parkingLot.getSlotNumbersForColor(registrationNumber);

            String slotNumbersString = IntStream.of(slotNumbers)
                    .mapToObj(Integer::toString)
                    .collect(Collectors.joining(", "));

            System.out.println(slotNumbersString);
        }
    },

    SLOT_NUMBER_FOR_REGISTRATION_NUMBER("^slot_number_for_registration_number (.*)") {
        public void execute(String[] args) {
            String registrationNumber = args[1];
            Integer slotNumber = parkingLot.getSlotNumberForRegistrationNumber(registrationNumber);

            if (slotNumber != -1) {
                System.out.println(slotNumber);
            } else {
                System.out.println("Not found");
            }
            
        }
    };

    public abstract void execute(String[] args);

    String pattern;
    static ParkingLot parkingLot;

    Command(String pattern) {
        this.pattern = pattern;
    }

    public String getPattern() {
        return pattern;
    }

}
