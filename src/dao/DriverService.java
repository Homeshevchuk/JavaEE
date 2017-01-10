package dao;

import entity.Driver;

import javax.ejb.Local;
import java.util.List;

/**
 * Created by PC on 10.01.2017.
 */
@Local
public interface DriverService {
    void create(Driver driver);
    void update(Driver driver);
    Driver read(long id);
    void delete(Driver park);
    List<Driver> getAll();
}
