package loader;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import loader.input.FishInput;
import loader.input.RodInput;
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
    public static ObjectWriter objectWriter = objectMapper.writer(new DefaultPrettyPrinter());
    public static FileWriter fileWriter;
    public static GlobalLibrary loadData() throws NullPointerException, IOException {
        GlobalLibrary library = new GlobalLibrary();
        File fishFile = new File("data/fish_types.json");
        File rodFile = new File("data/rod_types.json");

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
        return library;
    }

    public void updateFishes(GlobalLibrary library) throws IOException {
        objectWriter.writeValue(new File("data/fish_types.json"), library.getFishInputList());
    }
// TODO
    public void updateRods(GlobalLibrary library) throws IOException {
        objectWriter.writeValue(new File("data/rod_types.json"), library.getRodInputList());
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
