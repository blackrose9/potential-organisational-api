package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DepartmentTest {

    Department setUpNewDept(){
        return new Department("Logistics", "Moving around", 10);
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void instantiatesNewDepartmentWithCorrectName() throws Exception{
        Department department = setUpNewDept();
        assertEquals("Logistics", department.getName());
    }

    @Test
    public void setsDepartmentEmployeeCountCorrectly() throws Exception {
        Department department = setUpNewDept();
        department.setEmployeeNumber(16);
        assertNotEquals(10, department.getEmployeeNumber());
    }
}