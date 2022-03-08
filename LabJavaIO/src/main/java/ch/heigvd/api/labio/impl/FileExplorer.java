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

    public void explore(File rootFile) {

        if (rootFile.isFile()) {
            FileTransformer transformer = new FileTransformer();
            transformer.transform( rootFile );
        }
        else if (rootFile.isDirectory()) {
            File[] dirFiles = rootFile.listFiles();

            if (dirFiles != null) {
                Arrays.stream( dirFiles ).sorted().forEach( this::explore );
            }
        }
    }
}