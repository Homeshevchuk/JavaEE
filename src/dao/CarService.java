package dao;

import entity.Car;

import javax.ejb.Local;
import java.util.List;

/**
 * Created by PC on 10.01.2017.
 */
@Local
public interface CarService {
    void create(Car car);
    void update(Car car);
    Car read(long id);
    void delete(Car car);
    List<Car> getAll();
    List<Car> filterByTag(String filter);
}
