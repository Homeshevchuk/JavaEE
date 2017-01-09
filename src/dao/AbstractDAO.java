package dao;

import annotations.Column;
import annotations.Id;
import annotations.Table;
import dao.fields.FieldWrap;

import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.*;

//E - entity класс
public abstract class AbstractDAO<E> {

    private static final String SELECT_STATEMENT = "SELECT * FROM %s WHERE %s = %d";
    private static final String UPDATE_STATEMENT = "UPDATE %s SET %s WHERE %s = %s";
    private static final String DELETE_STATEMENT = "DELETE FROM %s WHERE %s = %s";
    private static final String CREATE_STATEMENT = "INSERT INTO %s(%s) VALUES(%s)";
    private static final String SELECT_ALL_STATEMENT = "SELECT * FROM %s";

    protected final DataSource dataSource;

    private final String tableName;

    private final Class<E> entityClass;

    private final Field[] fields;

    public AbstractDAO(DataSource dataSource) {
        this.dataSource = dataSource;

        Type type = findGenericArgumentType();
        this.entityClass = (Class<E>) ((ParameterizedType) type).getActualTypeArguments()[0];

        Table table = entityClass.getAnnotation(Table.class);
        this.tableName = table.value();


        this.fields = entityClass.getDeclaredFields();



    }

    private Type findGenericArgumentType() {
        Type type = getClass().getGenericSuperclass();

        while (!(type instanceof ParameterizedType) || ((ParameterizedType) type).getRawType() != AbstractDAO.class) {
            if (type instanceof ParameterizedType) {
                type = ((Class<?>) ((ParameterizedType) type).getRawType()).getGenericSuperclass();
            } else {
                type = ((Class<?>) type).getGenericSuperclass();
            }
        }
        return type;
    }

    public void create(E entity) {
        try (Connection connection = dataSource.getConnection()) {
            StringBuffer columns = new StringBuffer();
            for (Field field:fields){
                if(field.isAnnotationPresent(Id.class))continue;
                columns.append(field.getAnnotation(Column.class).value()+',');
            }
            columns.deleteCharAt(columns.lastIndexOf(","));
            StringBuffer values = new StringBuffer();
            for (Field field:fields){
                if(field.isAnnotationPresent(Id.class))continue;
                values.append("'"+field.get(entity)+"'"+",");
            }
            values.deleteCharAt(values.lastIndexOf(","));
            String insert = String.format(CREATE_STATEMENT, tableName, columns, values);
            try (PreparedStatement statement = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS)) {
                statement.executeUpdate();
                try (ResultSet set = statement.getGeneratedKeys()) {
                    while (set.next()) {
                        for (Field field : fields) {
                            if(field.isAnnotationPresent(Id.class)){

                                Object object = set.getObject("GENERATED_KEY");
                                field.set(entity, object);
                            }
                        }
                    }
                }
            }

        } catch (Exception e) {

        }
    }

    public E readById(Long id) {
        try (Connection connection = dataSource.getConnection()) {
            final E e = entityClass.newInstance();
            String idName = "";
            for(Field field:fields){
                if(field.isAnnotationPresent(Id.class)){
                    idName = field.getAnnotation(Column.class).value();
                }
            }
            String select = String.format(SELECT_STATEMENT, tableName, idName, id);
            try (PreparedStatement preparedStatement = connection.prepareStatement(select)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        for(Field field:fields){
                            if(field.isAnnotationPresent(Column.class)){
                                String fieldName = field.getAnnotation(Column.class).value();
                                Object object = resultSet.getObject(fieldName);
                                field.set(e, object);
                            }
                        }
                    }
                }
            }

            return e;
        } catch (IllegalAccessException | InstantiationException | SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void update(E entity) {
        try (Connection connection = dataSource.getConnection()) {
            StringBuffer values = new StringBuffer();
            for (Field field:fields){
                values.append("`"+field.getAnnotation(Column.class).value()+"`='"+field.get(entity)+"',");
            }
            values.deleteCharAt(values.lastIndexOf(","));
            String idName = "";
            Object id = null;
            for(Field field:fields){
                if(field.isAnnotationPresent(Id.class)){
                    idName = field.getAnnotation(Column.class).value();
                    id = field.get(entity);
                }
            }
            String update = String.format(UPDATE_STATEMENT, tableName, values, idName, id);
            try (PreparedStatement statement = connection.prepareStatement(update)) {
                statement.executeUpdate();
            }

        } catch (Exception e) {

        }
    }

    public void delete(E entity) {
        try (Connection connection = dataSource.getConnection()) {
            String idName = "";
            Object id = null;
            for(Field field:fields){
                if(field.isAnnotationPresent(Id.class)){
                    idName = field.getAnnotation(Column.class).value();
                    id = field.get(entity);
                }
            }
            String delete = String.format(DELETE_STATEMENT, tableName, idName, id);
            try (PreparedStatement statement = connection.prepareStatement(delete)) {
                statement.executeUpdate();
            }

        } catch (Exception e) {

        }
    }

    public List<E> getAll() {
        List<E> result = new ArrayList<E>();
        try (Connection connection = dataSource.getConnection()) {

            String selectAll = String.format(SELECT_ALL_STATEMENT, tableName);
            try (PreparedStatement statement = connection.prepareStatement(selectAll)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        E entity = entityClass.newInstance();
                        for(Field field:fields){
                            if(field.isAnnotationPresent(Column.class)){
                                String fieldName = field.getAnnotation(Column.class).value();
                                Object object = resultSet.getObject(fieldName);
                                field.set(entity, object);
                            }
                        }
                        result.add(entity);
                    }
                }
            }
        } catch (Exception e) {
        }
        return result;
    }
}
