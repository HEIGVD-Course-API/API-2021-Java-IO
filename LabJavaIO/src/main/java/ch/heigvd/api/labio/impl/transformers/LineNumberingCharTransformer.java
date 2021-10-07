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

  private int line_counter = 2;
  private boolean init = true;

  public String transform(String c) {
    // Si c'est un \r, le retire
    if ((int)c.charAt(0) == 13) return "";

    // Si c'est un \n, retourne "\nNEW_LINE. "
    if ((int)c.charAt(0) == 10) c = "\n" + (line_counter++) + ". ";

    // Si c'est le premier appel, met la première ligne en place
    if (init) {
      init = false;
      c = "1. " + c;
    }

    return c;
  }
}