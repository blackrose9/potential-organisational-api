package dao;

import models.Department;
import models.User;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class SqlUserDao implements UserDao{
    private final Sql2o sql2o;
    public SqlUserDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void add(User user) {
        String sql = "INSERT INTO users (name, position, role, departmentId) VALUES (:name, :position, :role, :departmentId)";
        try (Connection conn = sql2o.open()){
            int id = (int) conn.createQuery(sql, true)
                    .bind(user)
                    .executeUpdate()
                    .getKey();
            user.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void addUserToDepartment(User user, Department department) {
        String sql = "INSERT INTO departments_users (departmentId, userId) VALUES (:departmentId, :userId)";
        try (Connection conn = sql2o.open()){
            conn.createQuery(sql)
                    .addParameter("departmentId", department.getId())
                    .addParameter("userId", user.getId())
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public List<User> getAll() {
        try (Connection conn = sql2o.open()){
            return conn.createQuery("SELECT * FROM users")
                    .executeAndFetch(User.class);
        }
    }

    @Override
    public List<User> getAllUsersInDepartment(int departmentId) {
        try (Connection conn = sql2o.open()){
            return conn.createQuery("SELECT * FROM users WHERE departmentId = :departmentId")
                    .addParameter("departmentId", departmentId)
                    .executeAndFetch(User.class);
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE from users WHERE id = :id";
        String deleteJoin = "DELETE from restaurants_users WHERE userId = :userId";
        try (Connection conn = sql2o.open()){
            conn.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
            conn.createQuery(deleteJoin)
                    .addParameter("userId", id)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void clearAll() {
        String sql = "DELETE from users";
        try (Connection conn = sql2o.open()){
            conn.createQuery(sql).executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }
}
