package dao;

import models.Department;
import models.User;

import java.util.List;

public interface UserDao {
    //    C
    void add(User user);
    void addUserToDepartment(User user, Department department);
    //    R
    List<User> getAll();
    List<User> getAllUsersInDepartment(int departmentId);
    //    D
    void deleteById(int id);
    void clearAll();
}
