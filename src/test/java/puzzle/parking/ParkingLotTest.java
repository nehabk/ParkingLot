package puzzle.parking;

import org.junit.Test;
import puzzle.parking.model.Car;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ParkingLotTest {

    @Test
    public void should_create_parking_lot_for_the_given_number_of_parking_spaces() {
        assertThat(new ParkingLot(4).getTotalNumberOfSpaces(), is(4));
        assertThat(new ParkingLot(6).getTotalNumberOfSpaces(), is(6));
    }

    @Test
    public void should_be_able_to_park_a_car_in_the_nearest_slot() {

        ParkingLot parkingLot = new ParkingLot(4);

        Integer slotNumberForFirstCar = parkingLot.park(new Car("ABC", "White"));
        assertThat(slotNumberForFirstCar, is(1));

        Integer slotNumberForSecondCar = parkingLot.park(new Car("ABC", "White"));
        assertThat(slotNumberForSecondCar, is(2));
    }

    @Test
    public void should_vacate_slot_when_a_car_leaves() {

        ParkingLot parkingLot = new ParkingLot(4);
        parkingLot.park(new Car("ABC", "White"));
        parkingLot.park(new Car("ABC", "White"));
        parkingLot.park(new Car("ABC", "White"));

        assertThat(parkingLot.leave(3), is(true));
        assertThat(parkingLot.leave(0), is(false));
        assertThat(parkingLot.leave(5), is(false));
    }

    @Test
    public void should_return_status_string() {
        String expectedStatus = "Slot No.\tRegistration No\tColor" +
                "\n1\tKA-01-HH-1234\tWhite" +
                "\n2\tKA-01-HH-7777\tRed";
        ParkingLot parkingLot = new ParkingLot(4);
        parkingLot.park(new Car("KA-01-HH-1234", "White"));
        parkingLot.park(new Car("KA-01-HH-7777", "Red"));

        assertThat(parkingLot.getStatus(), is(expectedStatus));
    }

    @Test
    public void should_return_registration_numbers_of_cars_with_given_color() {
        ParkingLot parkingLot = new ParkingLot(4);
        parkingLot.park(new Car("KA-01-HH-1234", "White"));
        parkingLot.park(new Car("KA-01-HH-9999", "White"));
        parkingLot.park(new Car("KA-01-HH-7777", "Red"));

        String[] registrationNumbers = parkingLot.getRegistrationNumbersForColor("White");

        assertThat(registrationNumbers.length, is(2));
        assertThat(registrationNumbers[0], is("KA-01-HH-1234"));
    }

    @Test
    public void should_return_slot_numbers_for_cars_with_given_color() {
        ParkingLot parkingLot = new ParkingLot(4);
        parkingLot.park(new Car("KA-01-HH-1234", "White"));
        parkingLot.park(new Car("KA-01-HH-9999", "White"));
        parkingLot.park(new Car("KA-01-HH-7777", "Red"));

        int[] slotNumbers = parkingLot.getSlotNumbersForColor("White");

        assertThat(slotNumbers.length, is(2));
        assertThat(slotNumbers[0], is(1));
        assertThat(slotNumbers[1], is(2));
    }

    @Test
    public void should_return_slot_number_for_given_registration_number() {
        ParkingLot parkingLot = new ParkingLot(4);
        parkingLot.park(new Car("KA-01-HH-1234", "White"));
        parkingLot.park(new Car("KA-01-HH-9999", "White"));
        parkingLot.park(new Car("KA-01-HH-7777", "Red"));

        assertThat(parkingLot.getSlotNumberForRegistrationNumber("KA-01-HH-9999"), is(2));
        assertThat(parkingLot.getSlotNumberForRegistrationNumber("1234"), is(-1));
    }

}
