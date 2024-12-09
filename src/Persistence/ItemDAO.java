package Persistence;

import Business.Item;
import edu.salle.url.api.exception.ApiException;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public interface ItemDAO {
    ArrayList<Item> readAll() throws FileNotFoundException, ApiException;
    boolean fileOK();
}
