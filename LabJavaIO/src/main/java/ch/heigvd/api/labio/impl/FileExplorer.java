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

    private final FileTransformer transformer = new FileTransformer();

    public void explore(File rootDirectory) {
        if (rootDirectory.isFile()) {
            transformer.transform(rootDirectory);
            return;
        }

        if (rootDirectory.isDirectory()) {
            File[] files = rootDirectory.listFiles();
            if (files != null) {
                for (File file : files) {
                    explore(file);
                }
            }
        }
        /* TODO: implement the logic to explore the rootDirectory.
         *  Use the Java JDK documentation to see:
         *  - how to get the files and directories of rootDirectory (which is of class File)
         *  - to sort the items (files and directories) alphabetically
         *  - to check if an item is a file or a directory
         *  For each file, call the FileTransformer (see above).
         *  For each directory, recursively explore the directory.
         */
        //throw new UnsupportedOperationException("The student has not implemented this method yet.");

    }
}