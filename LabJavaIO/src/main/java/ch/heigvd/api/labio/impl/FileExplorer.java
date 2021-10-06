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
        if(!rootDirectory.exists() || !rootDirectory.isDirectory()) return;

        FileTransformer transformer = new FileTransformer();
        File[] FilesList = rootDirectory.listFiles();

        if(FilesList == null) return;

        for(File f : FilesList) {
            if (f.isDirectory()) {
                explore(f);
            } else {
                transformer.transform(f);
            }
        }
    }
}