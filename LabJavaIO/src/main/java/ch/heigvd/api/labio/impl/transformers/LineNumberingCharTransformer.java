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

  int line_number = 1 , flag = 1;

  public String transform(String c) {
    /* TODO: implement the transformation here.
     */
    String output;

    c = c.replace("\r","");
    if (c.compareTo("\n") == 0) {
      if (flag == 0) {
        output = "\n" + line_number++ + ". ";
      } else {
        output = line_number++ + ". " + "\n" + line_number++ + ". ";
        flag = 0;
      }
    } else {
      if (flag == 1) {
        output = line_number++ + ". " + c ;
        flag = 0;
      } else {
        output = c;
      }
    }

    return output;
  //  throw new UnsupportedOperationException("The student has not implemented this method yet.");
  }
}