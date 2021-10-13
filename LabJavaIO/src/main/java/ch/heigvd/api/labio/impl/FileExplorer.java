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

        File[] files = rootDirectory.listFiles();
        if (files == null)
            return;

        Arrays.sort(files);
        for (File f : files) {
            if (f.isDirectory()) {
                explore(f);
            } else if (f.isFile()) {
                transformer.transform(f);
            }
        }

    }
}