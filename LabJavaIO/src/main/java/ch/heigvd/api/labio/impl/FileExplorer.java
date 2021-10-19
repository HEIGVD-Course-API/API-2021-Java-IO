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
     * Recursively explores a directory and invokes transformation on each found file. The input may also be a file.
     *
     * @param rootDirectory File or directory to explore recursively. If rootDirectory is not an existing file or
     *                      directory, nothing is done.
     */
    public void explore(File rootDirectory) {
        if (rootDirectory.exists()) {
            if (rootDirectory.isFile()) {
                new FileTransformer().transform(rootDirectory);
            } else {
                // Here it is guaranteed that listFiles() doesn't return null, because listFiles() returns null only if
                // rootDirectory is not a directory, and we have already checked that both rootDirectory exists and is
                // not a file.
                File[] files = rootDirectory.listFiles();
                // this sorts files in lexicographic order because File implements the interface Comparable, and
                // the compareTo method of File compares path names lexicographically
                Arrays.sort(files);
                for (File file : files)
                    explore(file);
            }
        }
    }
}