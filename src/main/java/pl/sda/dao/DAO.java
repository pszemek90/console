package pl.sda.dao;

import java.util.List;
import java.util.Optional;

public interface DAO<T> {
    boolean create(T t);
    Optional<T> read(Integer id);
    List<T> readAll();
    void update(Integer id, T t);
    void delete(Integer id);
}
