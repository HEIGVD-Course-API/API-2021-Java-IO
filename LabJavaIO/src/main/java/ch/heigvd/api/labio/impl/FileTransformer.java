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
     * Reads the given inputFile and copies the content to a corresponding output file, after applying the following
     * transformations :
     * - The text is converted to upper case
     * - All line endings are converted to Unix-style line endings, i.e. only '\n'.
     * - A line number is added at the beginning of each line.
     * @param inputFile file to be transformed
     */
    public void transform(File inputFile) {

        UpperCaseCharTransformer upperCaseTransformer = new UpperCaseCharTransformer();
        LineNumberingCharTransformer lineNumberingTransformer = new LineNumberingCharTransformer();

        try {
            // Read whole input file in a String. The bytes of the file are decoded to text using UTF-8.
            String inputFileText = new String(new FileInputStream(inputFile).readAllBytes(), StandardCharsets.UTF_8);
            // generate output file pathname using the parent directory and the name of the input file
            File outputFile = new File(inputFile.getParentFile(), inputFile.getName() + ".out");
            FileWriter outputFileWriter = new FileWriter(outputFile, StandardCharsets.UTF_8);

            // apply transformations to each char of the input, and write the result to the output file
            for (char inputChar : inputFileText.toCharArray()) {
                String inputString = Character.toString(inputChar);
                String outputString = lineNumberingTransformer.transform(upperCaseTransformer.transform(inputString));
                outputFileWriter.write(outputString);
            }
            outputFileWriter.close();
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Error while reading, writing or transforming file.", ex);
        }
    }
}