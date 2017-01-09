package test;

import configuration.Configuration;
import dao.UserDAO;
import entity.User;
import org.junit.Before;
import org.junit.Test;

import javax.sql.DataSource;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;


public class UserDaoTest {
    UserDAO dao;

    @Before
    public void before() throws SQLException {

        DataSource dataSource = Configuration.getDataSource();
        dao = new UserDAO(dataSource);
        clearAll();
    }

    private void clearAll() throws SQLException {
        dao.
                getAll().forEach(user -> {
            dao.delete(user);
        });
    }

    @Test
    public void testAdd() throws SQLException {
        assertEquals(0, dao.getAll().size());
        User newUser = new User("User", "Password");
        dao.create(newUser);
        assertEquals(1, dao.getAll().size());
        clearAll();
    }

    @Test
    public void testDelete() throws SQLException {
        User newUser = new User("User", "Password");
        dao.create(newUser);
        assertEquals(1, dao.getAll().size());
        dao.delete(newUser);
        assertEquals(0, dao.getAll().size());
        clearAll();
    }

    @Test
    public void testChange() throws SQLException {
        User newUser = new User("User", "Password");
        dao.create(newUser);
        newUser.setName("test");
        newUser.setPassword("test");
        dao.update(newUser);
        User user = dao.readById(newUser.getId());
        assertEquals(user, newUser);
        clearAll();
    }

    @Test
    public void testGet() throws SQLException {
        User newUser = new User("User", "Password");
        dao.create(newUser);
        User user = dao.readById(newUser.getId());
        assertEquals(user, newUser);
        clearAll();
    }

    @Test
    public void testGetAll() throws SQLException {
        User newUser1 = new User("User1", "Password1");
        User newUser2 = new User("User2", "Password2");
        User newUser3 = new User("User3", "Password3");
        dao.create(newUser1);
        dao.create(newUser2);
        dao.create(newUser3);
        assertEquals(3, dao.getAll().size());
        clearAll();
    }
}
