package ch.heigvd.api.labio.impl;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

/**
 * The FileExplorer performs an exploration of the file system. It
 * visits the files and directory in alphabetic order.
 * When the explorer sees a directory, it recursively explores the directory.
 * When the explorer sees a file, it invokes the transformation on it.
 *
 * @author Olivier Liechti, Juergen Ehrensberger
 */
public class FileExplorer {

    public void explore(File rootDirectory) {
        FileTransformer transformer = new FileTransformer();

        File[] directoryContent = rootDirectory.listFiles();
            if(directoryContent == null)
                return;
            Arrays.sort(directoryContent);
            for(File file : directoryContent){
                if (file.isDirectory())
                    explore(file);
                else
                    transformer.transform(file);
         }
    }
}