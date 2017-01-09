package test;

import configuration.Configuration;
import dao.CarDAO;
import dao.ParkDAO;
import dao.UserDAO;
import entity.Car;
import entity.Park;
import entity.User;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;


public class CarDaoTest {
    static CarDAO carDAO;
    static UserDAO userDAO;
    static ParkDAO parkDAO;
    static User defaultUser;
    static Park defaultPark;

    @BeforeClass
    public static void before() throws SQLException, IOException, PropertyVetoException {

        DataSource dataSource = Configuration.getDataSource();
        carDAO = new CarDAO(dataSource);
        userDAO = new UserDAO(dataSource);
        parkDAO = new ParkDAO(dataSource);
        defaultUser = new User("Default", "123123123");
        userDAO.create(defaultUser);
        defaultPark = new Park("Kyiv_Park", defaultUser.getId());
        parkDAO.create(defaultPark);
        clearAll();
    }

    @AfterClass
    public static void after() {
        parkDAO.delete(defaultPark);
        userDAO.delete(defaultUser);
    }

    private static void clearAll() throws SQLException {
        carDAO.
                getAll().forEach(car -> {
            carDAO.delete(car);
        });
    }

    @Test
    public void testAdd() throws SQLException {
        assertEquals(0, carDAO.getAll().size());
        Car newCar = new Car("BX0333BB", defaultPark.getId());
        carDAO.create(newCar);
        assertEquals(1, carDAO.getAll().size());
        clearAll();
    }

    @Test
    public void testDelete() throws SQLException {
        Car newCar = new Car("BX0333BB", defaultPark.getId());
        carDAO.create(newCar);
        assertEquals(1, carDAO.getAll().size());
        carDAO.delete(newCar);
        assertEquals(0, carDAO.getAll().size());
        clearAll();
    }

    @Test
    public void testChange() throws SQLException {
        Car newCar = new Car("BX0333BB", defaultPark.getId());
        carDAO.create(newCar);
        newCar.setRegistrationTag("BX0000BB");
        carDAO.update(newCar);
        Car car = carDAO.readById(newCar.getId());
        assertEquals(car, newCar);
        clearAll();
    }

    @Test
    public void testGet() throws SQLException {
        Car newCar = new Car("BX0333BB", defaultPark.getId());
        carDAO.create(newCar);
        Car car = carDAO.readById(newCar.getId());
        assertEquals(car, newCar);
        clearAll();
    }

    @Test
    public void testGetAll() throws SQLException {
        Car newCar1 = new Car("BX0000BB", defaultPark.getId());
        Car newCar2 = new Car("BX0001BB", defaultPark.getId());
        Car newCar3 = new Car("BX0002BB", defaultPark.getId());
        carDAO.create(newCar1);
        carDAO.create(newCar2);
        carDAO.create(newCar3);
        assertEquals(3, carDAO.getAll().size());
        clearAll();
    }
}
