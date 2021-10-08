package ch.heigvd.api.labio.impl;

import java.io.File;

import static java.util.Arrays.sort;

/**
 * The FileExplorer performs an exploration of the file system. It
 * visits the files and directory in alphabetic order.
 * When the explorer sees a directory, it recursively explores the directory.
 * When the explorer sees a file, it invokes the transformation on it.
 *
 * @author Olivier Liechti, Juergen Ehrensberger, Melissa Gehring
 */
public class FileExplorer {

    public void explore(File rootDirectory) {
        // Only explore if directory exists
        if (rootDirectory.exists()) {
            // Handle directory
            if (rootDirectory.isDirectory()) {
                // Get directory content
                String[] content = rootDirectory.list();
                sort(content);
                for (String s : content) {
                    explore(new File(rootDirectory.getPath() + "/" + s));
                }

            } else if (rootDirectory.isFile()) {
                // Handle file
                FileTransformer transformer = new FileTransformer();
                transformer.transform(rootDirectory);
            }
        }
    }
}