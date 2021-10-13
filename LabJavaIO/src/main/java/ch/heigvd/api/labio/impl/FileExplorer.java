package ch.heigvd.api.labio.impl;

import java.io.File;
import java.nio.file.Files;

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

        if (Files.exists(rootDirectory.toPath())) {
            for (File file : rootDirectory.listFiles()) {
                if (file.isDirectory()) {
                    explore(file);
                } else {
                    transformer.transform(file);
                }
            }
        }
    }
}