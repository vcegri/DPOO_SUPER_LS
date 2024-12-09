package Persistence;

import Business.Stat;
import edu.salle.url.api.exception.ApiException;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public interface StatDAO {
    ArrayList<Stat> readAll() throws FileNotFoundException, ApiException;

    void saveStatList(ArrayList<Stat> statList);
}
