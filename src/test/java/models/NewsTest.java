package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NewsTest {
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    News setUpGeneralNews(){
        return new News("General News");
    }

    News setUpDepartmentNews(){
        return new News("Secret News", 1);
    }

    @Test
    public void instantiatesGeneralNewsCorrectly() throws Exception {
        News news = setUpGeneralNews();
        assertEquals("General News", news.getDetails());
    }

    @Test
    public void instantiatesDepartmentNewsCorrectly() throws Exception {
        News news = setUpDepartmentNews();
        assertEquals(1, news.getDepartmentId());
    }
}