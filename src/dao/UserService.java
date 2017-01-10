package dao;

import entity.User;

import javax.ejb.Local;
import java.util.List;

/**
 * Created by PC on 09.01.2017.
 */
@Local
public interface UserService {
    void create(User user);
    void update(User user);
    User read(long id);
    void delete(User user);
    List<User> getAll();
}
