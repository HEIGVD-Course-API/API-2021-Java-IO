package ch.heigvd.api.labio.impl;

import java.io.File;

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
        if(!rootDirectory.exists()) // Stop if the directory is empty
            return;

        for (final File fileEntry : rootDirectory.listFiles()) {

            if (fileEntry.isDirectory()) { // Recursively explore directories
                    explore(fileEntry);
            } else if(fileEntry.isFile()) {
                transformer.transform(fileEntry);
            } else {
                return;
            }
        }
    }
}