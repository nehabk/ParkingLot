package puzzle.parking.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static puzzle.parking.model.Command.CREATE_PARKING_LOT;

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

    @Test
    public void should_execute_create_parking_lot_command() {
        String[] args = {"create_parking_lot", "6"};
        String output = "Created a parking lot with 6 slots\n";
        CREATE_PARKING_LOT.execute(args);

        assertThat(outContent.toString(), is(output));
    }

}