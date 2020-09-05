package pl.sda.dao;

import java.util.List;

public interface DAO<T> {
    void create(T t);
    T read(int id);
    List<T> readAll();
    int update(int id);
    void delete(int id);
}
