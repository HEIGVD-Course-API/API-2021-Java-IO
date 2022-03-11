package ch.heigvd.api.labio.impl;

import ch.heigvd.api.labio.impl.transformers.LineNumberingCharTransformer;
import ch.heigvd.api.labio.impl.transformers.UpperCaseCharTransformer;

import java.io.*;
import java.nio.CharBuffer;
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
    LineNumberingCharTransformer lineNumberingCharTransformer = new LineNumberingCharTransformer();
    UpperCaseCharTransformer upperCaseCharTransformer = new UpperCaseCharTransformer();
    try (
            InputStreamReader isr = new InputStreamReader(new FileInputStream(inputFile.getAbsolutePath()), StandardCharsets.UTF_8);
            OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(inputFile.getAbsolutePath() + ".out"), StandardCharsets.UTF_8);
            ){
      int i;
      while((i = isr.read()) != -1){
        byte[] byte_ = new byte[1];
        byte_[0] = Integer.valueOf(i).byteValue();
        String toTransform = new String(byte_, StandardCharsets.UTF_8);
        String transformed = upperCaseCharTransformer.transform(lineNumberingCharTransformer.transform(toTransform));
        System.out.println(transformed);
        osw.write(transformed);
      }
    } catch (Exception ex) {
      LOG.log(Level.SEVERE, "Error while reading, writing or transforming file.", ex);
    }
  }
}