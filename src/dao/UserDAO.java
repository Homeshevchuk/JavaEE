package dao;

import entity.User;

import javax.sql.DataSource;


/**
 * Created by PC on 16.11.2016.
 */
public class UserDAO extends AbstractDAO<User> {

    public UserDAO(DataSource dataSource) {
        super(dataSource);
    }
}
