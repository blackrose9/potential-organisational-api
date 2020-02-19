package dao;

import models.Department;
import models.News;
import models.User;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.ArrayList;
import java.util.List;

public class SqlDepartmentDao implements DepartmentDao{
    private final Sql2o sql2o;

    public SqlDepartmentDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public List<Department> getAll() {
        try (Connection conn = sql2o.open()){
            return conn.createQuery("SELECT * FROM departments")
                    .executeAndFetch(Department.class);
        }
    }

    @Override
    public void add(Department department) {
        String sql = "INSERT INTO departments(name, description, employeeNo) VALUES (:name, :description, :employeeNo)";
        try(Connection conn = sql2o.open()) {
            int id = (int) conn.createQuery(sql, true)
                    .bind(department)
                    .executeUpdate()
                    .getKey();
            department.setId(id);
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public Department findById(int id) {
        try (Connection conn = sql2o.open()){
            return conn.createQuery("SELECT * FROM departments WHERE id = :id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(Department.class);
        }
    }

    @Override
    public List<User> getAllUsersByDepartment(int departmentId) {
        List<User> users =new ArrayList<>();
        String joinQuery = "SELECT userId FROM departments_users WHERE departmentId = :departmentId";

        try (Connection conn = sql2o.open()){
            List<Integer> allUserIds = conn.createQuery(joinQuery)
                    .addParameter("departmentId", departmentId)
                    .executeAndFetch(Integer.class);
            for (Integer userId : allUserIds){
                String userQuery = "SELECT * FROM users WHERE id = :userId";
                users.add(
                        conn.createQuery(userQuery)
                        .addParameter("userId", userId)
                        .executeAndFetchFirst(User.class));
            }
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
        return users;
    }

    @Override
    public List<News> getAllNewsByDepartment(int departmentId) {
        List<News> news = new ArrayList<>();
        String joinQuery = "SELECT newsId from departments_news WHERE departmentId = :departmentId";
        try (Connection conn = sql2o.open()){
            List<Integer> allNewsIds = conn.createQuery(joinQuery)
                    .addParameter("departmentId", departmentId)
                    .executeAndFetch(Integer.class);
            for (Integer newsId : allNewsIds){
                String newsQuery = "SELECT * FROM news WHERE id = :newsId";
                news.add(
                        conn.createQuery(newsQuery)
                        .addParameter("newsId", newsId)
                        .executeAndFetchFirst(News.class));
            }
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
        return news;
    }

}
