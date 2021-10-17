package ch.heigvd.api.labio.impl;

import javassist.compiler.ast.Visitor;

import java.io.File;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.Files;
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

        /* TODO: implement the logic to explore the rootDirectory.
         *  Use the Java JDK documentation to see:
         *  - how to get the files and directories of rootDirectory (which is of class File)
         *  - to sort the items (files and directories) alphabetically
         *  - to check if an item is a file or a directory
         *  For each file, call the FileTransformer (see above).
         *  For each directory, recursively explore the directory.
         */


        File[] listOfFiles = rootDirectory.listFiles();

        if(listOfFiles == null)
            return;
        Arrays.sort(listOfFiles);
        for(File f : listOfFiles){
            if(f.isDirectory()){
                  explore(f);
            }else{
                if(f.isFile())
                    transformer.transform(f);
            }
        }
        //Files.walk(rootDirectory.getPath(), );


        //throw new UnsupportedOperationException("The student has not implemented this method yet.");

    }
}