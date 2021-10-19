package ch.heigvd.api.labio.impl;

import ch.heigvd.api.labio.impl.transformers.LineNumberingCharTransformer;
import ch.heigvd.api.labio.impl.transformers.UpperCaseCharTransformer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
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

    /**
     * Reads a file, applies transformations on its content and writes the result in a new file.
     * The following transformations are applied :
     * <br>- The text is converted to upper case
     * <br>- All line endings are converted to Unix-style line endings, i.e. only '\n'.
     * <br>- A line number is added at the beginning of each line.
     *
     * @param inputFile the content of this file will be transformed and stored in a new file
     */
    public void transform(File inputFile) {
        try {
            // read whole input file text into a String
            String inputFileText = new String(new FileInputStream(inputFile).readAllBytes(), StandardCharsets.UTF_8);

            FileWriter outputFileWriter = new FileWriter(inputFile.toString() + ".out", StandardCharsets.UTF_8);
            UpperCaseCharTransformer upperCaseTransformer = new UpperCaseCharTransformer();
            LineNumberingCharTransformer lineNumberingTransformer = new LineNumberingCharTransformer();
            for (char inputChar : inputFileText.toCharArray()) {
                // convert char to string, apply the two transformations, and write the result to the output file
                outputFileWriter.write(
                        lineNumberingTransformer.transform(
                                upperCaseTransformer.transform(
                                        Character.toString(inputChar))));
            }
            outputFileWriter.close();
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Error while reading, writing or transforming file.", ex);
        }
    }
}