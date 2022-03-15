package ch.heigvd.api.labio.impl;

import ch.heigvd.api.labio.impl.transformers.LineNumberingCharTransformer;
import ch.heigvd.api.labio.impl.transformers.NoOpCharTransformer;
import ch.heigvd.api.labio.impl.transformers.UpperCaseCharTransformer;

import java.io.*;
import java.nio.charset.StandardCharsets;
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
    // Instantiate transformers needed later
    LineNumberingCharTransformer lineTransformer = new LineNumberingCharTransformer();
    UpperCaseCharTransformer upperTransformer = new UpperCaseCharTransformer();

    // Create a buffered reader
    try ( BufferedReader br = new BufferedReader(new FileReader(inputFile,  StandardCharsets.UTF_8))){
      // Create outputfile
      File outputFile = new File(inputFile + ".out");
      // Create buffered writer
      BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile, StandardCharsets.UTF_8));
      // Variables need from conversion from int -> char -> String during read/write operation
      int charsRead = 0;
      char c;
      String cString;
      // While there is something to read
      while(br.ready()){
        // Read the file char by char
        charsRead = br.read();
        // Cast to char
        c = (char) charsRead;
        // Cast to String
        cString = String.valueOf(c);
        // Apply transformations
        cString = upperTransformer.transform(cString);
        cString = lineTransformer.transform(cString);
        // Write transformed char to file
        bw.write(cString);
        // Flush output buffer
        bw.flush();
      }
      // Close the reader and writer
      br.close();
      bw.close();

    } catch (Exception ex) {
      LOG.log(Level.SEVERE, "Error while reading, writing or transforming file.", ex);
    }
  }
}