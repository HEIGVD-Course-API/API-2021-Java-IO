package ch.heigvd.api.labio.impl;

import ch.heigvd.api.labio.impl.transformers.LineNumberingCharTransformer;
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
    try {
      UpperCaseCharTransformer upper = new UpperCaseCharTransformer();
      LineNumberingCharTransformer line = new LineNumberingCharTransformer();
      Reader reader = new InputStreamReader(new FileInputStream(inputFile), StandardCharsets.UTF_8);
      Writer writer = new OutputStreamWriter(new FileOutputStream(inputFile.getAbsolutePath() + ".out"), StandardCharsets.UTF_8);

      int data = reader.read();
      while(data != -1) {
        String c = String.valueOf((char)data);
        writer.write(upper.transform(line.transform(c)));
        data = reader.read();
      }

      reader.close();
      writer.close();

    } catch (Exception ex) {
      LOG.log(Level.SEVERE, "Error while reading, writing or transforming file.", ex);
    }
  }
}