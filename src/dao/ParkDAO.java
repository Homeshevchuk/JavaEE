package dao;

import entity.Park;

import javax.sql.DataSource;

/**
 * Created by PC on 01.12.2016.
 */
public class ParkDAO extends AbstractDAO<Park> {
    public ParkDAO(DataSource dataSource) {
        super(dataSource);
    }
}
