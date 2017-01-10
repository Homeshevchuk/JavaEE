package dao;

import entity.Park;

import javax.ejb.Local;
import java.util.List;

/**
 * Created by PC on 10.01.2017.
 */
@Local
public interface ParkService {
    void create(Park park);
    void update(Park park);
    Park read(long id);
    void delete(Park park);
    List<Park> getAll();
}
