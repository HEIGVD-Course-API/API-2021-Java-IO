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

        File[] filesList = rootDirectory.listFiles();
        if (filesList == null) return;
        Arrays.sort(filesList);

        for (File file : filesList) {
            if (file.isFile())
                transformer.transform(file);
            if (file.isDirectory())
                explore(file);
        }
    }
}