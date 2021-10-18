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

  int line_nb = 1;

  public String transform(String c) {
    /* TODO: implement the transformation here.
     */
    if (c.length() != 1)
      throw new UnsupportedOperationException("The student has not implemented this method yet.");

    else {

      // Add number 1 at the beginning of first line
      if (line_nb == 1) {

        if (c.equals("\n"))
          return line_nb++ + ". " + c + line_nb++ + ". ";

        return line_nb++ + ". " + c;
      }

      // Add number of line after \n
      if(c.equals("\n")) {
        return c + line_nb++ + ". ";
      }

      // Remove carriage return
      if (c.equals("\r"))
        return "";

      return c;
    }
  }
}