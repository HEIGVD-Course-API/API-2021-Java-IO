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

    /**
     * Recursively explores directory and invokes transformation on each found file. The input may also be a file.
     *
     * @param rootDirectory File or directory to explore recursively. If rootDirectory is not an existing file or
     *                      directory, nothing is done.
     */
    public void explore(File rootDirectory) {
        FileTransformer transformer = new FileTransformer();
        if (rootDirectory.exists()) {
            if (rootDirectory.isFile()) {
                transformer.transform(rootDirectory);
            } else {
                // Here it is guaranteed that files can not be null, because listFiles() returns null only if
                // rootDirectory is not a directory, and we have already checked that both rootDirectory exists and is
                // not a file.
                File[] files = rootDirectory.listFiles();
                if (files.length != 0)
                    // this sorts files in lexicographic order because File implements the interface Comparable, and
                    // the method compareTo of File compares path names lexicographically
                    Arrays.sort(files);
                for (File file : files) {
                    explore(file);
                }
            }
        }
    }
}