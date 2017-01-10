package dao;

import entity.Driver;
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
public class DriverServiceImpl implements DriverService {
    private static final Logger logger = LogManager.getRootLogger();
    @PersistenceContext
    EntityManager manager;

    @Override
    public void create(Driver driver) {
        logger.info("Create new driver\n" + driver);
        try {
            manager.persist(driver);
        } catch (Exception e) {
            logger.error("Error while creating driver\n" + e);
        }
        logger.info("Driver created");

    }

    @Override
    public void update(Driver driver) {
        logger.info("Update driver\n" + driver);
        try {
            manager.merge(driver);
        } catch (Exception e) {
            logger.error("Error while updating driver\n" + e);
        }
        logger.info("Driver updated");
    }

    @Override
    public Driver read(long id) {
        logger.info("Get driver by id" + id);
        Driver driver = null;
        try {
            driver = manager.find(Driver.class, id);
        } catch (Exception e) {
            logger.error("Error while reading driver\n" + e);
        }
        logger.info("Received driver\n" + driver);
        return driver;
    }

    @Override
    public void delete(Driver driver) {

        logger.info("Remove driver\n" + driver);
        try {
            manager.remove(driver);
        } catch (Exception e) {
            logger.error("Error while removing driver\n" + e);
        }
        logger.info("Driver removed\n" + driver);
    }

    @Override
    public List<Driver> getAll() {
        logger.info("Get all drivers");
        Query query = manager.createQuery("SELECT e FROM Driver e");
        List<Driver> drivers = null;
        try {
            drivers = query.getResultList();
        } catch (Exception e) {
            logger.error("Error while receiving drivers\n" + e);
        }
        logger.info("Received drivers\n" + drivers);
        return drivers;
    }
}
