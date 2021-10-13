package ch.heigvd.api.labio.impl;
import ch.heigvd.api.labio.impl.transformers.UpperCaseCharTransformer;
import ch.heigvd.api.labio.impl.transformers.LineNumberingCharTransformer;
import ch.heigvd.api.labio.impl.transformers.NoOpCharTransformer;

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
      InputStreamReader isr = new InputStreamReader(new FileInputStream(inputFile),
              StandardCharsets.UTF_8);
      File output = new File(inputFile.getParent(),inputFile.getName()+".out");
      OutputStreamWriter owr = new OutputStreamWriter(new FileOutputStream(output),
              StandardCharsets.UTF_8);

      LineNumberingCharTransformer transformer = new LineNumberingCharTransformer();
      UpperCaseCharTransformer upper = new UpperCaseCharTransformer();

      int r;

      while((r = isr.read()) != -1){
        owr.write(transformer.transform(upper.transform(Character.toString(r))));
      }

      isr.close();
      owr.flush();
      owr.close();
    } catch (Exception ex) {
      LOG.log(Level.SEVERE, "Error while reading, writing or transforming file.", ex);
    }
  }
}