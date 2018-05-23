package puzzle.parking;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ParkingLotTest {

    @Test
    public void should_create_parking_lot_for_the_given_number_of_parking_spaces() {
        assertThat(new ParkingLot(4).getTotalNumberOfSpaces(), is(4));
        assertThat(new ParkingLot(6).getTotalNumberOfSpaces(), is(6));
    }

}
