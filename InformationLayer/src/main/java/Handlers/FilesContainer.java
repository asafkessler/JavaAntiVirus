package Handlers;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FilesContainer {
    private List<File> files;

    public FilesContainer() {
        this.files = new ArrayList<>();
    }

    /**
     * init list of files from array of files and directories
     *
     * @param files
     */
    public void init(File[] files) {
        //running on all files and directories
        for (File file : files) {
            //add files to list
            addFiles(file);
        }
    }

    /**
     * recursive function to add files from dir to list
     *
     * @param dir
     */
    private void addFiles(File dir) {
        if (dir.isFile()) {
            files.add(dir);
        } else if (dir.isDirectory()) {
            File[] files = dir.listFiles();
            if (files != null) {
                for (File file : files) {
                    addFiles(file);
                }
            }
        }
    }

    public List<File> getFiles() {
        return files;
    }

    public int getNumOfFiles() {
        return files.size();
    }
}
