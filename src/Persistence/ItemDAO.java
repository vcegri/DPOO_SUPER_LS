package Persistence;

import Business.Item;
import edu.salle.url.api.exception.ApiException;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Is used to read and write item data.
 */
public interface ItemDAO {
    /**
     * Reads all items.
     *
     * @return A list of items read.
     */
    ArrayList<Item> readAll() throws FileNotFoundException, ApiException;
    int fileOK();
}
