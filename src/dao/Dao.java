package dao;

import domain.BaseEntity;

import java.util.List;

public interface Dao<T extends BaseEntity> {

    List<T> readAll();

    int create(T entity);

    T read(Object id);

    int update(T entity);

    void delete(Object id);

}
