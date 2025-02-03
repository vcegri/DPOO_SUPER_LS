package Persistence;

import Business.Stat;
import edu.salle.url.api.exception.ApiException;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Is used to read and write stat data.
 */
public interface StatDAO {

    /**
     * Reads all stats.
     *
     * @return A list of stats read.
     * @throws FileNotFoundException if the JSON file can't be found
     * @throws ApiException if there is an error with the API
     */
    ArrayList<Stat> readAll() throws FileNotFoundException, ApiException;

    void saveStatList(ArrayList<Stat> statList);
}
