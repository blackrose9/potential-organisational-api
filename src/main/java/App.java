import com.google.gson.Gson;
import dao.SqlDepartmentDao;
import dao.SqlNewsDao;
import dao.SqlUserDao;
import models.Department;
import models.News;
import models.User;
import org.sql2o.*;

import static spark.Spark.*;

public class App  {
    public static void main(String[] args) {
        SqlNewsDao newsDao;
        SqlDepartmentDao departmentDao;
        SqlUserDao userDao;
        Connection conn;
        Gson gson = new Gson();

//        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:create.sql'";
        String connectionString = "jdbc:postgresql://localhost:5432/api";
        Sql2o sql2o = new Sql2o(connectionString, "sarah", "sarah");

        userDao = new SqlUserDao(sql2o);
        departmentDao = new SqlDepartmentDao(sql2o);
        newsDao = new SqlNewsDao(sql2o);
        conn = sql2o.open();

        post("/user/new", "application/json", (req, res) -> {
            User user = gson.fromJson(req.body(), User.class);
            userDao.add(user);
            res.status(201);
            return gson.toJson(user);
        });

        get("/users", "application/json", (req, res) -> {
            System.out.println(userDao.getAll());

            if(userDao.getAll().size() > 0){
                return gson.toJson(userDao.getAll());
            }
            else {
                return "{\"message\":\"I'm sorry, but no user in the database.\"}";
            }
        });

        post("/department/new", "application/json", (req, res) -> {
            Department department = gson.fromJson(req.body(), Department.class);
            departmentDao.add(department);
            res.status(201);
            return gson.toJson(department);
        });

        get("/departments", "application/json", (req, res) -> {
            System.out.println(departmentDao.getAll());

            if(departmentDao.getAll().size() > 0){
                return gson.toJson(departmentDao.getAll());
            }
            else {
                return "{\"message\":\"There are no departments in the database.\"}";
            }
        });

        post("/news/new", "application/json", (req, res) -> {
            News news = gson.fromJson(req.body(), News.class);
            newsDao.add(news);
            res.status(201);
            return gson.toJson(news);
        });

        get("/news", "application/json", (req, res) -> {
            System.out.println(newsDao.getAll());

            if(newsDao.getAll().size() > 0){
                return gson.toJson(newsDao.getAll());
            }
            else {
                return "{\"message\":\"There is no news in the database.\"}";
            }
        });
    }
}
