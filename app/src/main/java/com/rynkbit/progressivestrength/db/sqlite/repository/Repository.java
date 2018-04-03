package com.rynkbit.progressivestrength.db.sqlite.repository;

import java.util.List;

public interface Repository<T> {
    T update(T entity);
    T insert(T entity);
    List<T> findAll();
    T findById(long id);
    void remove(T entity);
}
