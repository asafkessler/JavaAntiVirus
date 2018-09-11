package FilesLogic;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

class Validator {

    static boolean isValid(File file) {
        boolean isValid = true;
        try {
            String[] lines = (String[]) Files.lines(file.toPath()).toArray();
            for (String line : lines) {
                if (line.toLowerCase().contains("$@#")) {
                    return false;
                }
            }
        } catch (IOException e) {
            System.out.println("unable to read from file " + file.getName());
            isValid = false;
        }
        return isValid;
    }
}
