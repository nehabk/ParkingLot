package puzzle.parking.model;

import puzzle.parking.ParkingLot;

import static java.lang.String.format;

public enum Command {

    CREATE_PARKING_LOT("^create_parking_lot ([0-9]+)") {
        @Override
        public void execute(String[] args) {
            int numberOfParkingSpaces = Integer.parseInt(args[1]);
            parkingLot = new ParkingLot(numberOfParkingSpaces);

            System.out.println(format("Created a parking lot with %s slots", numberOfParkingSpaces));
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
