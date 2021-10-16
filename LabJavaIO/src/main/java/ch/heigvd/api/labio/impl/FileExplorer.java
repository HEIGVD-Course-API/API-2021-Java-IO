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

        recursiveExplore(rootDirectory, transformer);

    }

    public void recursiveExplore(final File root, FileTransformer transformer) {
        File[] files = root.listFiles();
        if(files == null) return;
        Arrays.sort(files);
        for (final File fileEntry : files) {
            if (fileEntry.isDirectory()) {
                recursiveExplore(fileEntry, transformer);
            } else {
                transformer.transform(fileEntry);
            }
        }


    }
}