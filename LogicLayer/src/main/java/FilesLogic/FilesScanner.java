package FilesLogic;

import java.io.File;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FilesScanner {
    private FilesState state;
    private ExecutorService executorService;
    private long lastScanTime;
    private List<File> files;

    public FilesScanner(List<File> files) {
        state = new FilesState();
        this.files = files;
    }

    public void scan(int threadNum) {
        state.getFileStateMap().clear();
        long scanStartTime = System.currentTimeMillis();
        if (threadNum == 0) {
            return;
        }
        if (threadNum > files.size()) {
            threadNum = files.size();
        }

        executorService = Executors.newFixedThreadPool(threadNum);
        files.forEach(file -> {
            executorService.submit(new FileRunnable(file));
        });
        while (!isFinished()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        lastScanTime = System.currentTimeMillis() - scanStartTime;
    }

    private boolean isFinished() {
        return state.getFileStateMap().keySet().size() == files.size();
    }

    public long getLastScanTime() {
        return lastScanTime;
    }

    public FilesState getState() {
        return state;
    }

    class FileRunnable implements Runnable {
        private File file;

        FileRunnable(File file) {
            this.file = file;
        }

        @Override
        public void run() {
            boolean isFileValid = Validator.isValid(file);
            state.setFileStatus(file.getName(), isFileValid);
        }
    }
}
