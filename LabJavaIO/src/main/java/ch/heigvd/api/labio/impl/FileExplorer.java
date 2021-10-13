package ch.heigvd.api.labio.impl;

import java.io.File;
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

        /*  For each file, call the FileTransformer (see above).
         *  For each directory, recursively explore the directory.
         */
        if(rootDirectory.exists()) {
            if (rootDirectory.isDirectory()) {
                File[] files = rootDirectory.listFiles();
                Arrays.sort(files);
                for (File file : files)
                    explore(file);
            } else
                transformer.transform(rootDirectory);
        }
    }
}