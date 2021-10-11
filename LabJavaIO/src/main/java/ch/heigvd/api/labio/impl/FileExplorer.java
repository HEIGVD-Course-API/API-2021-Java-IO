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

		File[] files = rootDirectory.listFiles(); // get files and directories
		if (files != null && files.length != 0) { // if directory and if directory
			// not empty
			Arrays.sort(files); // sort files
			for (File f : files) {
				if (f.isFile())
					transformer.transform(f);
				if (f.isDirectory())
					explore(f);
			}
		}
	}

}
