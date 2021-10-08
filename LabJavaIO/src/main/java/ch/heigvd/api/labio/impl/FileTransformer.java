package ch.heigvd.api.labio.impl;

import ch.heigvd.api.labio.impl.transformers.LineNumberingCharTransformer;
import ch.heigvd.api.labio.impl.transformers.UpperCaseCharTransformer;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class transforms files. The transform method receives an inputFile.
 * It writes a copy of the input file to an output file, but applies a
 * character transformer before writing each character.
 *
 * @author Juergen Ehrensberger, Melissa Gehring
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

        // Transformers
        UpperCaseCharTransformer upperCaseCharTransformer = new UpperCaseCharTransformer();
        LineNumberingCharTransformer lineNumberingCharTransformer = new LineNumberingCharTransformer();

        try {
            // Open input and output Files, both use UTF-8 by default. Output file = <inputFile-Name>.out
            String outputPath = inputFile.getPath() + ".out";
            InputStreamReader isr = new InputStreamReader(new FileInputStream(inputFile));
            OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(outputPath));

            int charRead;
            String charTransformed;
            charRead = isr.read();
            while (charRead != -1) {
                //For all char in file, apply the two transformations
                String charToTransform = String.valueOf((char) charRead);
                charTransformed = lineNumberingCharTransformer.transform(upperCaseCharTransformer.transform(charToTransform));
                osw.write(charTransformed);

                charRead = isr.read();
            }

            isr.close();
            osw.close();
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Error while reading, writing or transforming file.", ex);
        }
    }
}