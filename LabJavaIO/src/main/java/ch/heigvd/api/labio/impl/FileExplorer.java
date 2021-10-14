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

    private void explore(File file, FileTransformer transformer) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files != null) {
                for (File nextFile : files) {
                    explore(nextFile, transformer);
                }
            }
            return;
        }
        if (file.isFile()) {
            transformer.transform(file);
        }
    }

    public void explore(File rootDirectory) {
        FileTransformer transformer = new FileTransformer();

        if (rootDirectory.exists()) {
            explore(rootDirectory, transformer);
        }
    }
}