package ch.heigvd.api.labio.impl.transformers;

import java.util.logging.Logger;

/**
 * This class applies a transformation to the input character (a string with a single character):
 *   1. Convert all line endings to Unix-style line endings, i.e. only '\n'.
 *   2. Add a line number at the beginning of each line.
 * Example:
 *   Using this character transformer, the following file :
 *      This is the first line.\r\n
 *      This is the second line.
 *   will be transformed to:
 *      1. This is the first line.\n
 *      2. This is the second line.
 *
 * @author Olivier Liechti, Juergen Ehrensberger
 */
public class LineNumberingCharTransformer {
  private static final Logger LOG = Logger.getLogger(UpperCaseCharTransformer.class.getName());

  private int lineNumber = 1;

  public String transform(String c) {
    /* OK TODO: implement the transformation here.
     */
    // add first line number
    if (lineNumber == 1) {
      return String.format("%d. %s", lineNumber++, c);
    }

    // delete Windows-style line endings
    if (c.equals("\r")) {
      return "";
    }

    // keep UNIX-style line ending and add next line's number
    if (c.equals("\n")) {
      return String.format("%d. %s", lineNumber++, c);
    }

    // if not a line ending, simply return the character
    return c;
  }
}