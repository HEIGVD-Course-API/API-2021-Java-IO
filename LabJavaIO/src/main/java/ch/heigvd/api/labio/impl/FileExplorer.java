package ch.heigvd.api.labio.impl;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
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

        /* TODO: implement the logic to explore the rootDirectory.
         *  Use the Java JDK documentation to see:
         *  - how to get the files and directories of rootDirectory (which is of class File)
         *  - to sort the items (files and directories) alphabetically
         *  - to check if an item is a file or a directory
         *  For each file, call the FileTransformer (see above).
         *  For each directory, recursively explore the directory.
         */
        //throw new UnsupportedOperationException("The student has not implemented this method yet.");
        try {
            File[] files = rootDirectory.listFiles();
            if (files.length > 0) {
                Arrays.sort(files);

                for (int i = 0; i < files.length; ++i) {
                    if (files[i].isFile()) {
                        transformer.transform(files[i]);
                    } else if (files[i].isDirectory()) {
                        explore(files[i]);
                    }
                }
            }
        }
        catch (Exception ex){
            System.err.println("Exception: " + ex.getMessage());
        }
    }
}