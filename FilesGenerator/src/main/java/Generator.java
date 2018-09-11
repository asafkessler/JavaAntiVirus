import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class Generator {
    public static void main(String[] args) throws IOException {
        final int numberOfFiles = 10000;
        final int numberOfFalseFiles = 3141;
        final int everyWhatFolder = 50;
        final String pattern = "$@#";
         StringBuilder targetPath = new StringBuilder("C:\\devl\\work\\Hackend\\JavaFiles\\");
        final int linesPerFile = 100;
        final int charsPerLine = 50;
        Set<Integer> randomIndexes = new HashSet<>();

//generate random indexes to be the virus files
        while(randomIndexes.size() != numberOfFalseFiles){
            randomIndexes.add( ThreadLocalRandom.current().nextInt(0, numberOfFiles));
        }

        List<String> lines = new ArrayList<>();
//        foreach file
        for (int fileIndex =0 ; fileIndex< numberOfFiles;fileIndex++){
            lines.clear();
            if (fileIndex % everyWhatFolder == 0){
                targetPath.append("A folder").append("\\");
                new File(targetPath.toString()).mkdir();
            }


//            foreachLine
            for (int lineIndex =0 ;lineIndex<linesPerFile;lineIndex++){
                StringBuilder line= new StringBuilder();
                for (int charIndex=0;charIndex<charsPerLine;charIndex++){
                    line.append((char) ThreadLocalRandom.current().nextInt(0, 256));
                }
                lines.add(line.toString());
            }
//            add virus at end of line 0
            if (randomIndexes.contains(fileIndex)){
                lines.set(0,lines.get(0)+pattern);
            }

            // write file
            Path file = Paths.get(targetPath+"file"+fileIndex+".in");
            Files.write(file, lines, Charset.forName("UTF-8"));
        }
    }
}
