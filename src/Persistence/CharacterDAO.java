package Persistence;

import Business.Character;
import edu.salle.url.api.exception.ApiException;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Is used to read and write characters data.
 */
public interface CharacterDAO {

    /**
     * Reads all characters.
     *
     * @return A list of characters read.
     */
    ArrayList<Character> readAll() throws FileNotFoundException, ApiException;

    int fileOK();
}
