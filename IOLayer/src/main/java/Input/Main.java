package Input;

import Handlers.FilesContainer;

public class Main {
    public static void main(String[] args) {
        FileFetcher fileFetcher = new FileFetcher("C:\\devl\\work\\Hackend");
        FilesContainer container = new FilesContainer();
        container.init(fileFetcher.getFilesTree());
        container.getFiles().forEach(file-> System.out.println(file.getName()));
    }
}
