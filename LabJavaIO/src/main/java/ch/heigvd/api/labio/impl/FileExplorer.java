package ch.heigvd.api.labio.impl;

import java.io.File;
import java.util.List;
import java.util.Objects;

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

        if(rootDirectory.isFile()){
            transformer.transform(rootDirectory);
        }
        else if (rootDirectory.isDirectory()){
            File[] files = rootDirectory.listFiles();
            for(int i = 0; i < Objects.requireNonNull(files).length; ++i){
                if(files[i].isFile())
                    transformer.transform(files[i]);
                else
                    explore(files[i]);
            }
        }

    }
}