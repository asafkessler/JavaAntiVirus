package Input;

import java.io.File;

class FileFetcher {
    //root path of files and directories
    private String rootPath;

    FileFetcher(String path) {
        this.rootPath = path;
    }

    File[] getFilesTree() {
        File dir = new File(rootPath);
        //if doesn't path exists
        if (!dir.exists()) {
            System.out.println("Error. Unknown Path.");
            return null;
        }
        return dir.listFiles();
    }

}
