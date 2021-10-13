package ch.heigvd.api.labio.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.nio.file.Paths;
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

        if(rootDirectory.exists()){
            File[] files = rootDirectory.listFiles();
            Arrays.sort(files);
            for(File file : files){
                if(file.isDirectory()){
                    explore(file);
                } else{
                    try {
                        transformer.transform(file);
                    }
                    catch(FileNotFoundException ex){
                        System.out.println(ex.getMessage());
                    }
                }
            }
        }
    }
}