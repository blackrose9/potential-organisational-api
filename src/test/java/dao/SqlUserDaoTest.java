package dao;

import models.Department;
import models.User;
import org.graalvm.compiler.lir.LIRInstruction;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

public class SqlUserDaoTest {
    private Connection conn;
    private SqlUserDao userDao;
    private SqlDepartmentDao departmentDao;
    private SqlNewsDao newsDao;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        userDao = new SqlUserDao(sql2o);
        departmentDao = new SqlDepartmentDao(sql2o);
        newsDao = new SqlNewsDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("flashing database");
        userDao.clearAll();
//        departmentDao.clearAll();
        newsDao.clearAll();
    }

//    @AfterClass
//    public static void afterClass() throws Exception {
//        conn.close();
//        System.out.println("connection closed");
//    }

    public User setUpNewUser(){
        User user = new User("Sarah", "#0", "cool things", 9);
        userDao.add(user);
        return user;
    }
    public Department setUpNewDept(){
        Department department = new Department("Logistics", "move stuff around", 10);
        departmentDao.add(department);
        return department;
    }

    @Test
    public void setId_addingUserSetsId() throws Exception {
        User testUser = setUpNewUser();
        int ogUserId = testUser.getId();
        userDao.add(testUser);
//        Department testDepartment = setUpNewDept();
        assertNotEquals(ogUserId, testUser.getId());
    }

    @Test
    public void getAll() throws Exception {
        User user1 = setUpNewUser();
        User user2 = setUpNewUser();
        assertEquals(2, userDao.getAll().size());
    }
}