package test;

import configuration.Configuration;
import dao.ParkDAO;
import dao.UserDAO;
import entity.Park;
import entity.User;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;


import javax.sql.DataSource;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;



public class ParkDaoTest {
    static UserDAO userDAO;
    static ParkDAO parkDAO;
    static User defaultUser;
    static User defaultUser2;


    @BeforeClass
    public static void before() throws SQLException {
        DataSource dataSource = Configuration.getDataSource();
        userDAO = new UserDAO(dataSource);
        parkDAO = new ParkDAO(dataSource);
        defaultUser = new User("Default", "123123123");
        defaultUser2 = new User("Default2", "123");
        userDAO.create(defaultUser);
        userDAO.create(defaultUser2);
        clearAll();
    }

    @AfterClass
    public static void after() {
        userDAO.delete(defaultUser);
        userDAO.delete(defaultUser2);
    }

    private static void clearAll() throws SQLException {
        parkDAO.
                getAll().forEach(car -> {
            parkDAO.delete(car);
        });
    }

    @Test
    public void testAdd() throws SQLException {
        assertEquals(0, parkDAO.getAll().size());
        Park newPark = new Park("Kyiv_Park", defaultUser.getId());
        parkDAO.create(newPark);
        assertEquals(1, parkDAO.getAll().size());
        clearAll();
    }

    @Test
    public void testDelete() throws SQLException {
        Park newPark = new Park("Kyiv_Park", defaultUser.getId());
        parkDAO.create(newPark);
        assertEquals(1, parkDAO.getAll().size());
        parkDAO.delete(newPark);
        assertEquals(0, parkDAO.getAll().size());
        clearAll();
    }

    @Test
    public void testChange() throws SQLException {
        Park newPark = new Park("Kyiv_Park", defaultUser.getId());
        parkDAO.create(newPark);
        newPark.setName("Khmelnytski_Park");
        parkDAO.update(newPark);
        Park park = parkDAO.readById(newPark.getId());
        assertEquals(park, newPark);
        clearAll();
    }

    @Test
    public void testGet() throws SQLException {
        Park newPark = new Park("Kyiv_Park", defaultUser.getId());
        parkDAO.create(newPark);
        Park park = parkDAO.readById(newPark.getId());
        assertEquals(park, newPark);
        clearAll();
    }

    @Test
    public void testGetAll() throws SQLException {
        Park newPark1 = new Park("Kyiv_Park1", defaultUser.getId());
        Park newPark2 = new Park("Kyiv_Park2", defaultUser2.getId());
        parkDAO.create(newPark1);
        parkDAO.create(newPark2);
        assertEquals(2, parkDAO.getAll().size());
        clearAll();
    }
}
