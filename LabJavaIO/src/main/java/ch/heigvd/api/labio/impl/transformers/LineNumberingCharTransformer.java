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
  private Integer lineCounter = 1;
  public String transform(String c) {

    if (c.contains("\r")) {
      return "";
    }
    if(lineCounter == 1 && c.contains("\n")){
      return (lineCounter++).toString() + ". "+c +(lineCounter++).toString() + ". "; //add number to first line then append the char
    }else if(lineCounter == 1){
      return (lineCounter++).toString() + ". "+c;
    }else if(c.contains("\n")){
      return c+(lineCounter++).toString()+ ". ";//after the newLine, append the number
    }else{
      return c;             //if it's a useless char, juste return it
    }
  }
}