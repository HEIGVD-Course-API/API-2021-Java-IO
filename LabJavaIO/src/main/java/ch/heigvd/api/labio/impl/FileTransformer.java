package ch.heigvd.api.labio.impl;

import ch.heigvd.api.labio.impl.transformers.LineNumberingCharTransformer;
import ch.heigvd.api.labio.impl.transformers.NoOpCharTransformer;
import ch.heigvd.api.labio.impl.transformers.UpperCaseCharTransformer;

import java.io.*;
import java.nio.file.Paths;
import java.util.Objects;
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
    UpperCaseCharTransformer upperCaseCharTransformer = new UpperCaseCharTransformer();
    LineNumberingCharTransformer lineNumberingTransformer = new LineNumberingCharTransformer();


    /* TODO: implement the following logic here:
     *  - open the inputFile and an outputFile
     *    Use UTF-8 encoding for both.
     *    Filename of the output file: <inputFile-Name>.out (that is add ".out" at the end)
     *  - Copy all characters from the input file to the output file.
     *  - For each character, apply a transformation: start with NoOpCharTransformer,
     *    then later replace it with a combination of UpperCaseFCharTransformer and LineNumberCharTransformer.
     */

    Reader reader = null;
    Writer writer = null;
    try {
      File outPutFile = Paths.get(inputFile.getParentFile().getPath(), inputFile.getName() + ".out").toFile();

      reader = new InputStreamReader(new FileInputStream(inputFile), "UTF-8");
      writer = new OutputStreamWriter(new FileOutputStream(outPutFile), "UTF-8");

      int ch;
      String s;
      while((ch = reader.read()) != -1){
        s = noOpCharTransformer.transform(String.valueOf((char)ch));
        writer.write(lineNumberingTransformer.transform(upperCaseCharTransformer.transform(s)));
      }

      writer.close();

    } catch (Exception ex) {
      LOG.log(Level.SEVERE, "Error while reading, writing or transforming file.", ex);
    }finally {
      try{
        Objects.requireNonNull(writer).close();
        reader.close();
      }catch (IOException ex){
        LOG.log(Level.SEVERE, "Error while closing writer or reader.", ex);
      }

    }
  }
}