package pl.sda.dao;

import java.util.List;
import java.util.Optional;

public interface DAO<T> {
    boolean create(T t);
    Optional<T> read(int id);
    List<T> readAll();
    void update(int id, T t);
    void delete(int id);
}
