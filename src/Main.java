
import loader.GlobalLibrary;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        GlobalLibrary library = new GlobalLibrary();

        loader.Loader.loadData(library);

        library.printLibrary();

    }
}
