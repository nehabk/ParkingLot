package puzzle.parking.model;

public enum Command {

    CREATE_PARKING_LOT("^create_parking_lot ([0-9]+)"),

    PARK("^park (.*) (.*)"),

    LEAVE("^leave ([0-9]+)"),

    STATUS("^status"),

    REGISTRATION_NUMBERS_FOR_CARS_WITH_COLOR("^registration_numbers_for_cars_with_colour (.*)"),

    SLOT_NUMBERS_FOR_CARS_WITH_COLOR("^rslot_numbers_for_cars_with_colour (.*)"),

    SLOT_NUMBER_FOR_REGISTRATION_NUMBER("^slot_number_for_registration_number (.*)");

    String pattern;

    Command(String pattern) {
        this.pattern = pattern;
    }

    public String getPattern() {
        return pattern;
    }

}
