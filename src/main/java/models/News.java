package models;

import java.util.Objects;

public class News {
    private int id;
    private String details;
    private int departmentId;

    public News(String details) {
        this.details = details;
    }

    public News(String details, int departmentId) {
        this.details = details;
        this.departmentId = departmentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        News news = (News) o;
        return id == news.id &&
                Objects.equals(details, news.details);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, details);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }
}
