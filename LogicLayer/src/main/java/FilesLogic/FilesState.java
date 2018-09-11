package FilesLogic;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class FilesState {
    private Map<String, Boolean> fileStateMap;

    FilesState() {
        this.fileStateMap = new ConcurrentHashMap<>();
    }

    void setFileStatus(String name, boolean isVirus){
        fileStateMap.put(name,isVirus);
    }

    Map<String, Boolean> getFileStateMap() {
        return fileStateMap;
    }

    public List<String> getFalseFiles(){
        List<String> falseFiles = new ArrayList<>();
        fileStateMap.forEach((fileName,isValid)->{
            if (!isValid){
                falseFiles.add(fileName);
            }
        });
        return falseFiles;
    }
}
