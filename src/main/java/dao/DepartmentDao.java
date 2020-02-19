package dao;

import models.Department;
import models.News;
import models.User;

import java.util.List;

public interface DepartmentDao {
//    C
    void add(Department department);
//    R
    Department findById(int id);
    List<Department> getAll();
    List<User> getAllUsersByDepartment(int departmentId);
    List<News> getAllNewsByDepartment(int departmentId);

//    D
//    void deleteById(int id);
//    void clearAllDepartments();
}
