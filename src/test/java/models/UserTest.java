package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    User setUpNewUser(){
        return new User("Sarah", "#0", "Does cool things", 9);
    }

    @Test
    public void instantiatesNewUserCorrectlyChecksName() throws Exception{
        User user = setUpNewUser();
        assertEquals("Sarah", user.getName());
    }

    @Test
    public void setsUserDepartmentIdCorrectly() throws Exception {
        User user = setUpNewUser();
        user.setDepartmentId(7);
        assertNotEquals(9, user.getDepartmentId());
    }
}