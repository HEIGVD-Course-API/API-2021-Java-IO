package ch.heigvd.api.labio.impl;

import ch.heigvd.api.labio.impl.transformers.LineNumberingCharTransformer;
import ch.heigvd.api.labio.impl.transformers.NoOpCharTransformer;
import ch.heigvd.api.labio.impl.transformers.UpperCaseCharTransformer;
import org.apache.commons.io.Charsets;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class transforms files. The transform method receives an inputFile.
 * It writes a copy of the input file to an output file, but applies a
 * character transformer before writing each character.
 *
 * @author Juergen Ehrensberger
 */
public class FileTransformer {
  private static final Logger LOG = Logger.getLogger(FileTransformer.class.getName());

  public void transform(File inputFile) {
    /*
     * This method opens the given inputFile and copies the
     * content to an output file.
     * The output file has a file name <inputFile-Name>.out, for example:
     *   quote-2.utf --> quote-2.utf.out
     * Both files must be opened (read or write) with encoding "UTF-8".
     * Before writing each character to the output file, the transformer calls
     * a character transformer to transform the character before writing it to the output.
     */

    var capitalizer = new UpperCaseCharTransformer();
    var lineNumberer = new LineNumberingCharTransformer();

    try {

      String outputFilePath = inputFile.getCanonicalPath() + ".out";
      var inputStream = new InputStreamReader(new FileInputStream(inputFile.getCanonicalPath()), Charsets.UTF_8);
      var outputStream = new OutputStreamWriter(new FileOutputStream(outputFilePath), Charsets.UTF_8);

      while (inputStream.ready()) {
        String c = Character.toString(inputStream.read());

        // Apply transformation
        c = capitalizer.transform(c);
        c = lineNumberer.transform(c);

        outputStream.write(c);
      }

      inputStream.close();
      outputStream.close();

    } catch (Exception ex) {
      LOG.log(Level.SEVERE, "Error while reading, writing or transforming file.", ex);
    }
  }
}