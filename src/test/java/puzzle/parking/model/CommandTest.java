package puzzle.parking.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static puzzle.parking.model.Command.*;

public class CommandTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(System.out);
    }

    public void resetOutContent() {
        outContent.reset();
    }

    @Test
    public void should_execute_create_parking_lot_command() {
        String[] args = {"create_parking_lot", "6"};
        String output = "Created a parking lot with 6 slots\n";
        CREATE_PARKING_LOT.execute(args);

        assertThat(outContent.toString(), is(output));
    }

    @Test
    public void should_execute_park_command() {
        String[] createParkingLotArgs = {"create_parking_lot", "6"};
        String[] parkArgs = {"park", "KA-01-HH-1234", "White"};
        String output = "Allocated slot number: 1\n";

        CREATE_PARKING_LOT.execute(createParkingLotArgs);
        resetOutContent();
        PARK.execute(parkArgs);

        assertThat(outContent.toString(), is(output));
    }

    @Test
    public void should_execute_leave_command() {
        String[] createParkingLotArgs = {"create_parking_lot", "6"};
        String[] leaveArgs = {"leave", "4"};
        String output = "Slot number 4 is free\n";

        CREATE_PARKING_LOT.execute(createParkingLotArgs);
        resetOutContent();
        LEAVE.execute(leaveArgs);

        assertThat(outContent.toString(), is(output));
    }

    @Test
    public void should_execute_status_command() {
        String[] createParkingLotArgs = {"create_parking_lot", "6"};
        String[] parkArgs = {"park", "KA-01-HH-1234", "White"};
        String[] statusArgs = {"status"};
        String output = "Slot No.\tRegistration No\tColor\n1\tKA-01-HH-1234\tWhite\n";

        CREATE_PARKING_LOT.execute(createParkingLotArgs);
        PARK.execute(parkArgs);
        resetOutContent();
        STATUS.execute(statusArgs);

        assertThat(outContent.toString(), is(output));
    }

    @Test
    public void should_execute_registration_numbers_for_cars_with_colors_command() {
        String[] createParkingLotArgs = {"create_parking_lot", "6"};
        String[] parkArgs = {"park", "KA-01-HH-1234", "White"};
        String[] registrationNumberForColorArgs = {"registration_numbers_for_cars_with_colour", "White"};
        String output = "KA-01-HH-1234\n";

        CREATE_PARKING_LOT.execute(createParkingLotArgs);
        PARK.execute(parkArgs);
        resetOutContent();
        REGISTRATION_NUMBERS_FOR_CARS_WITH_COLOR.execute(registrationNumberForColorArgs);

        assertThat(outContent.toString(), is(output));
    }

    @Test
    public void should_execute_slot_numbers_for_cars_with_colors_command() {
        String[] createParkingLotArgs = {"create_parking_lot", "6"};
        String[] parkArgs = {"park", "KA-01-HH-1234", "White"};
        String[] slotNumberForColorArgs = {"slot_numbers_for_cars_with_colour", "White"};
        String output = "1, 2\n";

        CREATE_PARKING_LOT.execute(createParkingLotArgs);
        PARK.execute(parkArgs);
        PARK.execute(parkArgs);
        resetOutContent();
        SLOT_NUMBERS_FOR_CARS_WITH_COLOR.execute(slotNumberForColorArgs);

        assertThat(outContent.toString(), is(output));
    }

    @Test
    public void should_execute_slot_number_for_registration_number_command() {
        String[] createParkingLotArgs = {"create_parking_lot", "6"};
        String[] parkArgs = {"park", "KA-01-HH-1234", "White"};
        String[] slotNumberForRegistrationNumberArgs = {"slot_number_for_registration_number", "KA-01-HH-1234"};
        String output = "1\n";

        CREATE_PARKING_LOT.execute(createParkingLotArgs);
        PARK.execute(parkArgs);
        resetOutContent();
        SLOT_NUMBER_FOR_REGISTRATION_NUMBER.execute(slotNumberForRegistrationNumberArgs);

        assertThat(outContent.toString(), is(output));
    }


}