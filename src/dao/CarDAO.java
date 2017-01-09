package dao;

import entity.Car;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by PC on 01.12.2016.
 */
public class CarDAO extends AbstractDAO<Car> {
    private static final String FILTER_STATEMENT = "SELECT * FROM car WHERE Registration_Tag like '%s'";
    public CarDAO(DataSource dataSource) {
        super(dataSource);
    }
    public List<Car> filterByTag(String filter) {
        List<Car> result = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            filter='%'+filter+'%';
            String selectAll = String.format(FILTER_STATEMENT, filter);
            try (Statement statement = connection.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery(selectAll)) {
                    while (resultSet.next()) {
                       Car entity = new Car();
                        entity.setId(resultSet.getLong("id"));
                        entity.setRegistrationTag(resultSet.getString("Registration_tag"));
                        entity.setParkId(resultSet.getLong("park"));
                        result.add(entity);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }
}
