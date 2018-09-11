package FilesLogic;

import java.io.File;
import java.util.List;

public class FilesScanner {
    private FilesState state;

    public FilesScanner() {
        state = new FilesState();
    }

    public void scan(List<File> files){
        files.forEach(file->{
            boolean isFileValid = Validator.isValid(file);
            state.setFileStatus(file.getName(),isFileValid);
        });
    }

    public FilesState getState() {
        return state;
    }
}
