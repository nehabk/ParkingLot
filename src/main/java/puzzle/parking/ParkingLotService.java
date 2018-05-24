package puzzle.parking;

import puzzle.parking.model.Command;

import java.util.EnumSet;

import static java.util.regex.Pattern.matches;

public class ParkingLotService {

    private static EnumSet<Command> commands = EnumSet.allOf(Command.class);

    public static void executeCommand(String input) {

        Command targetCommand = commands.stream()
                .filter(command -> matches(command.getPattern(), input))
                .findFirst()
                .orElse(null);

        targetCommand.execute(input.split(" "));

    }

}
