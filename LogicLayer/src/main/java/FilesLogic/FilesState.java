package FilesLogic;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class FilesState {
    private Map<File, Boolean> fileStateMap;

    FilesState() {
        this.fileStateMap = new ConcurrentHashMap<>();
    }

    void setFileStatus(File file, boolean isVirus){
        fileStateMap.put(file,isVirus);
    }

    Map<File, Boolean> getFileStateMap() {
        return fileStateMap;
    }

    public List<File> getFalseFiles(){
        List<File> falseFiles = new ArrayList<>();
        fileStateMap.forEach((fileName,isValid)->{
            if (!isValid){
                falseFiles.add(fileName);
            }
        });
        return falseFiles;
    }

    /**
     * deletes all virus files
     * @return number of file that weren't deleted
     */
    public int clearFalseFiles(){
        List<File> falseFiles = getFalseFiles();
        falseFiles.forEach(file->{
            if (file.delete()){
                fileStateMap.remove(file);
            }
        });
        return getFalseFiles().size();
    }

}
