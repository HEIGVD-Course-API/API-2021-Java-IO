package ch.heigvd.api.labio.impl;

import ch.heigvd.api.labio.impl.transformers.LineNumberingCharTransformer;
import ch.heigvd.api.labio.impl.transformers.NoOpCharTransformer;
import ch.heigvd.api.labio.impl.transformers.UpperCaseCharTransformer;

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

    /* TODO: first start with the NoOpCharTransformer which does nothing.
     *  Later, replace it by a combination of the UpperCaseCharTransformer
     *  and the LineNumberCharTransformer.
     */
    // ... transformer = ...
    NoOpCharTransformer transformerNo = new NoOpCharTransformer();
    UpperCaseCharTransformer transformerUpper = new UpperCaseCharTransformer();
    LineNumberingCharTransformer transformerLine = new LineNumberingCharTransformer();

    /* TODO: implement the following logic here:
     *  - open the inputFile and an outputFile
     *    Use UTF-8 encoding for both.
     *    Filename of the output file: <inputFile-Name>.out (that is add ".out" at the end)
     *  - Copy all characters from the input file to the output file.
     *  - For each character, apply a transformation: start with NoOpCharTransformer,
     *    then later replace it with a combination of UpperCaseFCharTransformer and LineNumberCharTransformer.
     */

    try {
      // input file
      InputStream       inputStream = new FileInputStream(inputFile);
      InputStreamReader isr = new InputStreamReader(inputStream, "UTF-8");


      // output file
      File outFile = new File(inputFile.getParent(), inputFile.getName() + ".out");

      OutputStream       outputStream       = new FileOutputStream(outFile);
      OutputStreamWriter osw = new OutputStreamWriter(outputStream, "UTF-8");

      int c = 0;
      while((c = isr.read()) != -1) {
        StringBuilder str = new StringBuilder();
        str.append(Character.toChars(c));
        osw.write(transformerUpper.transform(transformerLine.transform(str.toString())));
      }
      osw.close();
      isr.close();

    } catch (Exception ex) {
      LOG.log(Level.SEVERE, "Error while reading, writing or transforming file.", ex);
    }
  }
}