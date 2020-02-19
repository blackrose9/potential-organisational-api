package dao;

import models.Department;

import java.util.List;

public interface DepartmentDao {
//    C
    void add(Department department);
//    R
    List<Department> getAll();
    Department findById(int id);
//    D
    void deleteById(int id);
    void clearAllDepartments();
}
