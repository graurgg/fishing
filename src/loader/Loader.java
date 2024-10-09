package loader;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import entities.Player;
import loader.input.FishInput;
import loader.input.RodInput;
import loader.input.Zone;
import lombok.Getter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;


// Class to take care of reading and writing from files
@Getter
public class Loader {
    public static ObjectMapper objectMapper = new ObjectMapper().configure(
            DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    public static FileWriter fileWriter;
    private static GlobalLibrary library = GlobalLibrary.getInstance();
    private static Player player = Player.getInstance();
    public static void loadData() throws NullPointerException, IOException {
        File fishFile = new File("data/fish_types.json");
        File rodFile = new File("data/rod_types.json");
        File zoneFile = new File("data/zones.json");

        try {
            library.addFish(Arrays.asList(objectMapper.readValue(fishFile, FishInput[].class)));
        } catch (MismatchedInputException e) {
            System.out.println(e.getMessage());
            // If database file is empty, simply ignore it
            // This shouldn't happen
        }
        try {
            library.addRod(Arrays.asList(objectMapper.readValue(rodFile, RodInput[].class)));
        } catch (MismatchedInputException e) {
            System.out.println(e.getMessage());
            // If database file is empty, simply ignore it
            // This shouldn't happen
        }
        try {
            library.addZone(Arrays.asList(objectMapper.readValue(zoneFile, Zone[].class)));
        } catch (MismatchedInputException e) {
            System.out.println(e.getMessage());
            // If database file is empty, simply ignore it
            // This shouldn't happen
        }

        // Updates the fish weights for each zone in the library
        library.getZoneList().forEach(Zone::getWeights);

        // Initializes the player equipment and zone with defaults, decided in the GlobalLibrary class
        {
            player.changeZone(library.getDefaultZone());
            player.equipRod(library.getDefaultRod());
        }
    }

    public static void log(Exception e) {
        try {
            fileWriter = new FileWriter("logs/log");
            fileWriter.write(Arrays.toString(e.getStackTrace()));
        } catch (IOException ex) {
            System.out.printf("Sorry, couldn't even log the exception %s :(((((.%n", ex.getMessage());
        }
    }
}
