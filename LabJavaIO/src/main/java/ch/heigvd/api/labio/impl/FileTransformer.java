package ch.heigvd.api.labio.impl;

import ch.heigvd.api.labio.impl.transformers.LineNumberingCharTransformer;
import ch.heigvd.api.labio.impl.transformers.NoOpCharTransformer;
import ch.heigvd.api.labio.impl.transformers.UpperCaseCharTransformer;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.OutputStreamWriter;


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


      NoOpCharTransformer transformer = new NoOpCharTransformer();



    try {
      // Create an input stream to read a text file
      InputStreamReader isr = new InputStreamReader(new FileInputStream(inputFile), StandardCharsets.UTF_8);

      // Create an output file to write the read file
      File outputFile = new File(inputFile.getAbsolutePath() + ".out");
      OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(outputFile), StandardCharsets.UTF_8);

      // Write in the output file and make every transformation asked on every char
      int b;
      UpperCaseCharTransformer transformer1 = new UpperCaseCharTransformer();
      LineNumberingCharTransformer transformer2 = new LineNumberingCharTransformer();
      while ( (b = isr.read()) != -1 ) {
        String c = transformer.transform(Character.toString((char)b));
        c = transformer1.transform(Character.toString(b));
        osw.write(transformer2.transform(c));
      }

      osw.close();
      isr.close();

    } catch (Exception ex) {
      LOG.log(Level.SEVERE, "Error while reading, writing or transforming file.", ex);
    }
  }
}