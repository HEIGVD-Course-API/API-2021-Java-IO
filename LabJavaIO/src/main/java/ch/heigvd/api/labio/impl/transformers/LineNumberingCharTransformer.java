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
  int lineNbr = 0;
  boolean wasEnd = true;

  private static final Logger LOG = Logger.getLogger(UpperCaseCharTransformer.class.getName());

  public String transform(String c) {
    if(Objects.equals(c, "\r")){
      c = "";
      wasEnd = true;
    }
    else if(wasEnd){
      c = String.valueOf(++lineNbr) + ". " + c;
      wasEnd = false;
    }
    return c;
  }
}