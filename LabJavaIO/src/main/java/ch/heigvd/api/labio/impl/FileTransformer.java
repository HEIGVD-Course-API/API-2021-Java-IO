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
     * Both files must be opened (read or write) with encoding StandardCharsets.UTF_8.
     * Before writing each character to the output file, the transformer calls
     * a character transformer to transform the character before writing it to the output.
     */

    NoOpCharTransformer noOpCharTransformer = new NoOpCharTransformer();
    UpperCaseCharTransformer upperCaseCharTransformer = new UpperCaseCharTransformer();
    LineNumberingCharTransformer lineNumberingCharTransformer = new LineNumberingCharTransformer();

    try {

      InputStreamReader isr = new InputStreamReader(new FileInputStream(inputFile), StandardCharsets.UTF_8);

      File file = new File(inputFile.getParent(),inputFile.getName() + ".out");
      OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8);

      int b;
      while((b = isr.read()) != -1){
        String s = Character.toString((char)b);
        osw.write(lineNumberingCharTransformer.transform(upperCaseCharTransformer.transform(s)));
      }
      isr.close();
      osw.flush();
      osw.close();

    } catch (Exception ex) {
      LOG.log(Level.SEVERE, "Error while reading, writing or transforming file.", ex);
    }
  }
}