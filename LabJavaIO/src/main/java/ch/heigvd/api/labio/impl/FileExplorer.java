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

        recursiveExplore(rootDirectory, transformer);

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

    public void recursiveExplore(final File root, FileTransformer transformer) {
        File[] files = root.listFiles();
        if(files == null) return;
        Arrays.sort(files);
        for (final File fileEntry : files) {
            if (fileEntry.isDirectory()) {
                recursiveExplore(fileEntry, transformer);
            } else {
                transformer.transform(fileEntry);
            }
        }


    }
}