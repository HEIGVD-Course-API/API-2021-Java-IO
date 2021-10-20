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
  private int i = 0;
  private boolean firstLine = true;

  public String transform(String c) {
    c = i == 0 ? ++i + ". " + c : c; // Add the first line number

    if(c.equals("\n") || c.endsWith("\n")) // Add line numbers
      return c + ++i + ". ";
    else if(c.equals("\r") || c.endsWith("\r")) // Ignore \r
      return "";

    return c;
  }
}