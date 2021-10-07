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
  private Integer lineCount = 1;
  private boolean firstChar = true;

  public String transform(String c) {
    /* TODO: implement the transformation here.
     */

    String stringToReturn = "";

    if (firstChar) { //First char management
      stringToReturn += "1. "; //first line number
      firstChar = false;
    }

    if (c.compareTo("\r") == 0) { //nothing to return if \r
      return stringToReturn + "";
    } else if (c.compareTo("\n") == 0) { //new line management
      ++lineCount;
      stringToReturn += c + lineCount.toString() + ". "; //saves the \n and the new line print
    } else {
      stringToReturn += c; //standard symbol to print
    }
    return stringToReturn;
  }
}