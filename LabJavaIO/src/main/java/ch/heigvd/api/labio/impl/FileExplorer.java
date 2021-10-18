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

        // Check if rootDirectory exists as Directory
        if(rootDirectory.exists()) {

            // If it's a File --> transform()
            if (rootDirectory.isFile()) transformer.transform(rootDirectory);

            // If not --> Explore rootDirectory
            File[] contents = rootDirectory.listFiles();

            // Check if contents is not empty (null) and sort alphabetically as default
            if(contents != null) {
                Arrays.sort(contents);

                // If it's a Directory --> explore()
                for ( File f : contents) {
                    explore(f);
                }
            }
        }
    }
}