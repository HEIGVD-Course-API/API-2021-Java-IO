package ch.heigvd.api.labio.impl;

import java.io.File;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The FileExplorer performs an exploration of the file system. It
 * visits the files and directory in alphabetic order.
 * When the explorer sees a directory, it recursively explores the directory.
 * When the explorer sees a file, it invokes the transformation on it.
 *
 * @author Olivier Liechti, Juergen Ehrensberger
 */
public class FileExplorer {


    private static final Logger LOG = Logger.getLogger(FileTransformer.class.getName());
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

        try {
            if (rootDirectory != null) {

                File[] files = rootDirectory.listFiles();

                if (files == null) return;
                Arrays.sort(files);

                for (File file : files) {
                    if (file.isDirectory()) {
                        explore(file);
                    } else {
                        transformer.transform(file);
                    }
                }
            }
        }catch (Exception ex){
            LOG.log(Level.SEVERE, "Error while reading, writing or exploring directory.", ex);
        }
      //  throw new UnsupportedOperationException("The student has not implemented this method yet.");

    }
}