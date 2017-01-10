package dao;

import entity.Car;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by PC on 10.01.2017.
 */
@Stateless
public class CarServiceImpl implements CarService {
    private static final Logger logger = LogManager.getRootLogger();
    @PersistenceContext
    EntityManager manager;

    @Override
    public void create(Car car) {
        logger.info("Create new car\n"+car);
        try {
            manager.persist(car);
        }catch (Exception e){
            logger.error("Error while creating car\n"+e);
        }
        logger.info("Car created");

    }

    @Override
    public void update(Car car) {
        logger.info("Update car\n"+car);
        try {
            manager.merge(car);
        }catch (Exception e){
            logger.error("Error while updating car\n"+e);
        }
        logger.info("Car updated");

    }

    @Override
    public Car read(long id) {
        logger.info("Get car by id"+id);
        Car car = null;
        try {
            car = manager.find(Car.class, id);
        }catch (Exception e){
            logger.error("Error while reading car\n"+e);
        }
        logger.info("Recieved car\n"+car);
        return car;
    }

    @Override
    public void delete(Car car) {
        logger.info("Remove car\n"+car);
        try {
            manager.remove(car);
        }catch (Exception e){
            logger.error("Error while removing car\n"+e);
        }
        logger.info("Car removed\n"+car);

    }

    @Override
    public List<Car> getAll() {
        logger.info("Get all cars");
        Query query = manager.createQuery("SELECT e FROM Car e");
        List<Car> cars = null;
        try {
            cars = query.getResultList();
        }catch (Exception e){
            logger.error("Error while receiving car\n"+e);
        }
        logger.info("Received cars\n"+cars);
        return cars;
    }

    @Override
    public List<Car> filterByTag(String filter) {
        logger.info("Filtering cars by tag "+filter);
        Query query = manager.createQuery("SELECT e FROM Car e WHERE e.registrationTag LIKE '%"+filter+"%'");
        List<Car> cars =null;
        try {
            cars = query.getResultList();
        }catch (Exception e){
            logger.error("Error while receiving car\n"+e);
        }
        logger.info("Recieved carlist "+cars);
        return cars;
    }
}
