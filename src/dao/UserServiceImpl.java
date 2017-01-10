package dao;

import entity.User;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;


/**
 * Created by PC on 16.11.2016.
 */
@Stateless
public class UserServiceImpl implements UserService {
    private static final Logger logger = LogManager.getRootLogger();

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void create(User user) {
        logger.info("Create new user\n" + user);
        try {
            entityManager.persist(user);
        } catch (Exception e) {
            logger.error("Error while creating user\n" + e);
        }
        logger.info("User created");
    }

    @Override
    public void update(User user) {
        logger.info("Update user\n" + user);
        try {
            entityManager.merge(user);
        } catch (Exception e) {
            logger.error("Error while updating user\n" + e);
        }
        logger.info("User updated");
    }

    @Override
    public User read(long id) {
        logger.info("Get user by id" + id);
        User user = null;
        try {
            user = entityManager.find(User.class, id);
        } catch (Exception e) {
            logger.error("Error while reading user\n" + e);
        }
        logger.info("Received user\n" + user);
        return user;
    }

    @Override
    public void delete(User user) {


        logger.info("Remove user\n" + user);
        try {
            entityManager.remove(user);
        } catch (Exception e) {
            logger.error("Error while removing user\n" + e);
        }
        logger.info("User removed\n" + user);
    }

    @Override
    public List<User> getAll() {
        logger.info("Get all users");
        Query query = entityManager.createQuery("SELECT e FROM User e");
        List<User> users= null;
        try {
            users = query.getResultList();
        } catch (Exception e) {
            logger.error("Error while receiving users\n" + e);
        }
        logger.info("Received users\n" + users);
        return users;

    }
}
