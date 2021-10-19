package ch.heigvd.api.labio.impl;

import ch.heigvd.api.labio.impl.transformers.LineNumberingCharTransformer;
import ch.heigvd.api.labio.impl.transformers.NoOpCharTransformer;
import ch.heigvd.api.labio.impl.transformers.UpperCaseCharTransformer;

import java.io.*;
import java.nio.file.Paths;
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

        NoOpCharTransformer noOpCharTransformer = new NoOpCharTransformer();
        UpperCaseCharTransformer upperCaseCharTransformer = new UpperCaseCharTransformer();
        LineNumberingCharTransformer lineNumberingTransformer = new LineNumberingCharTransformer();

        BufferedReader br = null;
        BufferedWriter bw = null;
        try {
            File outPutFile = Paths.get(inputFile.getParentFile().getPath(), inputFile.getName() + ".out").toFile();

            br = new BufferedReader(new InputStreamReader(new FileInputStream(inputFile), "UTF-8"));
            bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outPutFile), "UTF-8"));

          int ch;
            String s;
            while ((ch = br.read()) != -1) {
                s = noOpCharTransformer.transform(String.valueOf((char) ch));
                bw.write(lineNumberingTransformer.transform(upperCaseCharTransformer.transform(s)));
            }

        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Error while reading, writing or transforming file.", ex);
        } finally {
            try {
              if (bw != null) {
                bw.close();
              }
              if (br != null) {
                br.close();
              }
            } catch (IOException ex) {
                LOG.log(Level.SEVERE, "Error while closing writer or reader.", ex);
            }

        }
    }

}