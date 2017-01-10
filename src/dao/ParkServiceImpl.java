package dao;

import entity.Driver;
import entity.Park;
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
public class ParkServiceImpl implements ParkService {
    private static final Logger logger = LogManager.getRootLogger();
    @PersistenceContext
    EntityManager manager;

    @Override
    public void create(Park park) {
        logger.info("Create new park\n" + park);
        try {
            manager.persist(park);
        } catch (Exception e) {
            logger.error("Error while creating park\n" + e);
        }
        logger.info("Park created");

    }

    @Override
    public void update(Park park) {
        logger.info("Update park\n" + park);
        try {
            manager.merge(park);
        } catch (Exception e) {
            logger.error("Error while updating park\n" + e);
        }
        logger.info("Park updated");

    }

    @Override
    public Park read(long id) {
        logger.info("Get park by id" + id);
        Park park = null;
        try {
            park = manager.find(Park.class, id);
        } catch (Exception e) {
            logger.error("Error while reading park\n" + e);
        }
        logger.info("Received park\n" + park);
        return park;
    }

    @Override
    public void delete(Park park) {
        logger.info("Remove park\n" + park);
        try {
            manager.remove(park);
        } catch (Exception e) {
            logger.error("Error while removing park\n" + e);
        }
        logger.info("Park removed\n" + park);
        manager.remove(park);
    }

    @Override
    public List<Park> getAll() {

        logger.info("Get all parks");
        Query query = manager.createQuery("SELECT e FROM Park e");
        List<Park> parks= null;
        try {
            parks = query.getResultList();
        } catch (Exception e) {
            logger.error("Error while receiving parks\n" + e);
        }
        logger.info("Received parks\n" + parks);
        return parks;
    }
}
