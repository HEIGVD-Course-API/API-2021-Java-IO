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
        if (!rootDirectory.exists())
            return;

        FileTransformer transformer = new FileTransformer();

        //get all files in a dir
        File[] files = rootDirectory.listFiles();

        for (File file : files) {
            if (file.isDirectory()) {
                explore(file);
            } else {
                transformer.transform(file);
            }
        }

    }
}