package ch.heigvd.api.labio.impl;

import java.io.File;
import java.util.Arrays;
import java.util.stream.Stream;

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

        /* TODO: implement the logic to explore the rootDirectory.
         *  Use the Java JDK documentation to see:
         *  - how to get the files and directories of rootDirectory (which is of class File)
         *  - to sort the items (files and directories) alphabetically
         *  - to check if an item is a file or a directory
         *  For each file, call the FileTransformer (see above).
         *  For each directory, recursively explore the directory.
         */

        String[] listOfFiles = rootDirectory.list();
        assert listOfFiles != null;
        Arrays.sort(listOfFiles);

        for(String name : listOfFiles){
            File newfile = new File(rootDirectory.getPath() + "\\" + name);
            if(newfile.isFile())
                transformer.transform(newfile);
            else if(newfile.isDirectory())
                explore(newfile);
        }
    }
}