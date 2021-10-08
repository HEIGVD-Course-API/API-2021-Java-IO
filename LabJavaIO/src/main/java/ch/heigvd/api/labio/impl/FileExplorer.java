package ch.heigvd.api.labio.impl;

import java.io.File;
import java.util.Arrays;

import static java.util.Arrays.sort;

/**
 * The FileExplorer performs an exploration of the file system. It
 * visits the files and directory in alphabetic order.
 * When the explorer sees a directory, it recursively explores the directory.
 * When the explorer sees a file, it invokes the transformation on it.
 *
 * @author Olivier Liechti, Juergen Ehrensberger
 */
public class FileExplorer {

    public void explore(File rootDirectory) {
        // Si le File n'existe pas, sort, rien à faire
        if (!rootDirectory.exists()) return;

        // -----------------------------------------------------------------------------
        // Si c'est un fichier, le transforme
        if (rootDirectory.isFile()) {
            FileTransformer transformer = new FileTransformer();
            transformer.transform(rootDirectory);
            return;
        }

        // -----------------------------------------------------------------------------
        // Si c'est un dossier
        // Liste son conteu
        String[] children = rootDirectory.list();
        assert children != null;

        // Trie les entrées par ordre alphabétique
        // Cette ligne sert à assurer un comportement régulier et cohérent entre chaque OS !
        sort(children);

        // Parcours et traite les éléments
        for (String child: children) {
            explore(new File(rootDirectory.getAbsolutePath() + "/" + child));
        }
    }
}