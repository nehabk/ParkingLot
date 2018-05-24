package puzzle.parking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class ParkingLotApplication {

    public static void main(String[] args) throws IOException {

        if (args.length > 0) {
            try (Stream<String> stream = Files.lines(Paths.get(args[0]))) {

                stream.forEach(ParkingLotService::executeCommand);

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

            while (true) {
                String input = bufferedReader.readLine();
                if ("exit".equals(input))
                    break;

                ParkingLotService.executeCommand(input);
            }
        }
    }

}
