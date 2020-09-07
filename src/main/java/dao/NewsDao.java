package dao;

import models.Department;
import models.News;

import java.util.List;

public interface NewsDao {
//    C
    void add(News news);
    void addNewsToDepartment(News news, Department department);
//    R
    List<News> getAll();
    News findById(int id);
//    D
    void deleteById(int id);
    void clearAll();
}
