package dao;

import models.Department;
import org.sql2o.Sql2o;

import java.util.List;

public class SqlDepartmentDao implements DepartmentDao{
    private final Sql2o sql2o;

    public SqlDepartmentDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public List<Department> getAll() {
        return null;
    }

    @Override
    public void add(Department department) {

    }

    @Override
    public Department findById(int id) {
        return null;
    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public void clearAllDepartments() {

    }
}
