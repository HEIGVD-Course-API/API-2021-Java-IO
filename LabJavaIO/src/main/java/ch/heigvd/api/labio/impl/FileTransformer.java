package ch.heigvd.api.labio.impl;

import ch.heigvd.api.labio.impl.transformers.LineNumberingCharTransformer;
import ch.heigvd.api.labio.impl.transformers.NoOpCharTransformer;
import ch.heigvd.api.labio.impl.transformers.UpperCaseCharTransformer;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class transforms files. The transform method receives an inputFile.
 * It writes a copy of the input file to an output file, but applies a
 * character transformer before writing each character.
 *
 * @author Juergen Ehrensberger
 */
public class FileTransformer {
    private static final Logger LOG = Logger.getLogger(FileTransformer.class.getName());

    public void transform(File inputFile) {
        /*
         * This method opens the given inputFile and copies the
         * content to an output file.
         * The output file has a file name <inputFile-Name>.out, for example:
         *   quote-2.utf --> quote-2.utf.out
         * Both files must be opened (read or write) with encoding "UTF-8".
         * Before writing each character to the output file, the transformer calls
         * a character transformer to transform the character before writing it to the output.
         */




        // ... transformer = ...
        NoOpCharTransformer transformer = new NoOpCharTransformer();
        LineNumberingCharTransformer lineNumb = new LineNumberingCharTransformer();
        UpperCaseCharTransformer upCase = new UpperCaseCharTransformer();


        try {

            FileReader fr = new FileReader( inputFile, StandardCharsets.UTF_8 );

            File outputFile = new File(inputFile.getAbsolutePath() + ".out");
            FileWriter wr = new FileWriter(outputFile, StandardCharsets.UTF_8 );

            int i;
            while( (i=fr.read()) != -1){
                char c = (char)i;
                String in = Character.toString(c);
                String out = lineNumb.transform(upCase.transform(in));
                wr.write(out);
            }
            fr.close();
            wr.close();


        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Error while reading, writing or transforming file.", ex);
        }
    }
}