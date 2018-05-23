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

}
