package ch.heigvd.api.labio.impl.transformers;

import java.util.Objects;
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
  private boolean newLine = true;
  private int lineNumber = 1;

  public String transform(String c) {
    String str = "";
    if(newLine && lineNumber == 1) {
      str += String.valueOf(lineNumber++) + ". ";
      newLine = false;
    }
    if(Objects.equals(c, "\n")) {
      newLine = true;
      str += c + lineNumber++ + ". ";
    } else if(!Objects.equals(c, "\r")){
      str += c;
    }
    return str;
  }
}