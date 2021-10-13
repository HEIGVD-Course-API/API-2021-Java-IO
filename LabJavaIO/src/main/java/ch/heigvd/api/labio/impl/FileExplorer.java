package ch.heigvd.api.labio.impl;

import java.io.File;
import java.util.Arrays;

import org.apache.commons.io.comparator.NameFileComparator;

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

        File[] files = rootDirectory.listFiles();
        if (files == null) {
            return;
        }

        Arrays.sort(files, NameFileComparator.NAME_COMPARATOR);
        for (File file : files) {
            if (file.isFile()) {
                transformer.transform(file);
            }

            if (file.isDirectory()) {
                explore(file);
            }
        }
    }
}