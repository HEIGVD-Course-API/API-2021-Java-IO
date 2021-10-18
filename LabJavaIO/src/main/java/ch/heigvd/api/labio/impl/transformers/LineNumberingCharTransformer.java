package ch.heigvd.api.labio.impl.transformers;

import java.util.logging.Logger;

/**
 * This class applies a transformation to the input character (a string with a single character):
 * 1. Convert all line endings to Unix-style line endings, i.e. only '\n'.
 * 2. Add a line number at the beginning of each line.
 * Example:
 * Using this character transformer, the following file :
 * This is the first line.\r\n
 * This is the second line.
 * will be transformed to:
 * 1. This is the first line.\n
 * 2. This is the second line.
 *
 * @author Olivier Liechti, Juergen Ehrensberger
 */
public class LineNumberingCharTransformer {
    private static final Logger LOG = Logger.getLogger(UpperCaseCharTransformer.class.getName());
    private int currentLineNumber = 1;
    /**
     * Stores text that has not been outputted yet and that will be the output of the next transformation
     */
    private String transformationOutput = generateNextLineNumberingText();

    /**
     * Generates the line numbering text that must appear at the beginning of the current line.
     * The line number is incremented each time this method is called.
     *
     * @return line numbering text
     */
    private String generateNextLineNumberingText() {
        String LineNumberingText = currentLineNumber + ". ";
        currentLineNumber++;
        return LineNumberingText;
    }

    /**
     * Transforms a single char.
     * If we are at the beginning of a line, the output will include a line numbering.
     * If the input is "\r", the output will be an empty string.
     *
     * @param inputChar transformation input, must be at most 1 char long
     * @return transformation output
     */
    public String transform(String inputChar) {
        if (inputChar == null || inputChar.length() > 1)
            throw new IllegalArgumentException();
        if (!inputChar.equals("\r")) {
            transformationOutput += inputChar;
            // if we are at the beginning of a new line, append line numbering to output
            if (inputChar.equals("\n"))
                transformationOutput += generateNextLineNumberingText();
        }
        // we must empty transformationOutput before returning
        String returnValue = transformationOutput;
        transformationOutput = "";
        return returnValue;
    }
}