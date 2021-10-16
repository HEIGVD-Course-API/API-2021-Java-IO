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
        if(!rootDirectory.exists())
            return;

        for (final File fileEntry : rootDirectory.listFiles()) {

            if (fileEntry.isDirectory()) {
                    explore(fileEntry);
            } else if(fileEntry.isFile()) {
                transformer.transform(fileEntry);
            } else {
                return;
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


    }
}