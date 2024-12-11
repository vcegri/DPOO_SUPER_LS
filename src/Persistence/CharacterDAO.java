package Persistence;

import Business.Character;
import edu.salle.url.api.exception.ApiException;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public interface CharacterDAO {

    ArrayList<Character> readAll() throws FileNotFoundException, ApiException;

    int fileOK();
}
