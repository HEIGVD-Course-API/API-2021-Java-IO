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

        if (rootDirectory.exists()) {

            FileTransformer transformer = new FileTransformer();

            File[] entries = rootDirectory.listFiles();
            Arrays.sort(entries);

            for (File entry : entries)
                if (entry.isFile())
                    transformer.transform(entry);
                else
                    explore(entry);
        }
    }
}