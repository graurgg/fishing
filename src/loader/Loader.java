package loader;

import com.fasterxml.jackson.databind.ObjectMapper;
import loader.input.FishInput;
import loader.input.RodInput;
import lombok.Getter;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

@Getter
public class Loader {
    public static ObjectMapper objectMapper = new ObjectMapper();
    public static void loadData(GlobalLibrary library) throws NullPointerException, IOException {
        File fishFile = new File("data/fish_types.json");
        File rodFile = new File("data/rod_types.json");

        library.addFishes(Arrays.asList(objectMapper.readValue(fishFile, FishInput[].class)));
        library.addRods(Arrays.asList(objectMapper.readValue(rodFile, RodInput[].class)));

    }
}
