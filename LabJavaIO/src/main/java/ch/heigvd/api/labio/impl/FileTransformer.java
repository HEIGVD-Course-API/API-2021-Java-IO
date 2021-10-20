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

    /* TODO: first start with the NoOpCharTransformer which does nothing.
     *  Later, replace it by a combination of the UpperCaseCharTransformer
     *  and the LineNumberCharTransformer.
     */
    // ... transformer = ...
    NoOpCharTransformer noOpCharTransformer = new NoOpCharTransformer();
    LineNumberingCharTransformer lineNumberingCharTransformer = new LineNumberingCharTransformer();
    UpperCaseCharTransformer upperCaseCharTransformer = new UpperCaseCharTransformer();


    /* TODO: implement the following logic here:
     *  - open the inputFile and an outputFile
     *    Use UTF-8 encoding for both.
     *    Filename of the output file: <inputFile-Name>.out (that is add ".out" at the end)
     *  - Copy all characters from the input file to the output file.
     *  - For each character, apply a transformation: start with NoOpCharTransformer,
     *    then later replace it with a combination of UpperCaseFCharTransformer and LineNumberCharTransformer.
     */
    try {
      //File file = new File(inputFile.getAbsolutePath(), inputFile.getName() + ".out");

      String outputFileName = inputFile.getAbsolutePath() + ".out";

      OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(outputFileName), StandardCharsets.UTF_8);
      InputStreamReader isr = new InputStreamReader(new FileInputStream(inputFile.getAbsolutePath()), StandardCharsets.UTF_8);
      // osw.write(noOpCharTransformer.transform("b"));
      // osw.close();

      // Writer writer = new BufferedWriter(new FileWriter(outputFileName));
      // FileInputStream reader = new FileInputStream(inputFile.getAbsolutePath());

      int b = isr.read();
      //int b = reader.read();
      String c;

      while(b != -1) {
        c = noOpCharTransformer.transform("" + (char) b);
        c = lineNumberingCharTransformer.transform(c);
        // writer.write(upperCaseCharTransformer.transform(c));
        osw.write(upperCaseCharTransformer.transform(c));
        // b = reader.read();
        b = isr.read();
      }
      // writer.close();
      osw.close();

    } catch (Exception ex) {
      LOG.log(Level.SEVERE, "Error while reading, writing or transforming file.", ex);
    }
  }
}