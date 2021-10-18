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

    NoOpCharTransformer noOpChar = new NoOpCharTransformer();
    UpperCaseCharTransformer upperCaseChar = new UpperCaseCharTransformer();
    LineNumberingCharTransformer lineNumberingChar = new LineNumberingCharTransformer();
    
    try {
      // Create the output file
      File outputFile = new File(inputFile.getPath() + ".out");

      // Read and Write file in place
      InputStreamReader isr = new InputStreamReader(new FileInputStream(inputFile), StandardCharsets.UTF_8);
      OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(outputFile), StandardCharsets.UTF_8);

      // No need to check if exist (already done in FileExplorer.java)

      // Read until buffer empty
      while (isr.ready()){
        String s = Character.toString(isr.read());
        s = upperCaseChar.transform(s);
        s = lineNumberingChar.transform(s);
        osw.append(noOpChar.transform(s));
      }

      isr.close();
      osw.close();

    } catch (Exception ex) {
      LOG.log(Level.SEVERE, "Error while reading, writing or transforming file.", ex);
    }
  }
}