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

    UpperCaseCharTransformer transformerUpper = new UpperCaseCharTransformer();
    LineNumberingCharTransformer transformerLineNumber = new LineNumberingCharTransformer();

    try {

      InputStreamReader isr = new InputStreamReader(new FileInputStream(inputFile), "UTF-8");
      OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(inputFile.getAbsolutePath() + ".out"), "UTF-8");

      while(isr.ready()){
        String b = Character.toString(isr.read());

        b = transformerUpper.transform(b);
        b = transformerLineNumber.transform(b);

        osw.write(b);
      }

      isr.close();
      osw.close();

    } catch (Exception ex) {
      LOG.log(Level.SEVERE, "Error while reading, writing or transforming file.", ex);
    }
  }
}