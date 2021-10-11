package ch.heigvd.api.labio.impl;

import java.io.File;

import static java.util.Arrays.sort;

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

        /* implement the logic to explore the rootDirectory.
         *  Use the Java JDK documentation to see:
         *  - how to get the files and directories of rootDirectory (which is of class File)
         *  - to sort the items (files and directories) alphabetically
         *  - to check if an item is a file or a directory
         *  For each file, call the FileTransformer (see above).
         *  For each directory, recursively explore the directory.
         */

        if(rootDirectory.exists()){
            if(rootDirectory.isDirectory()){
                String[] file = rootDirectory.list();

                assert file != null;
                sort(file);

                for(String doc : file){
                    explore(new File(rootDirectory.getPath() + "/"+ doc));
                }
            }else if (rootDirectory.isFile()){
                FileTransformer transformer = new FileTransformer();
                transformer.transform(rootDirectory);
            }

        }


    }
}