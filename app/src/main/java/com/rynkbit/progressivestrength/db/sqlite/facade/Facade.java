package com.rynkbit.progressivestrength.db.sqlite.facade;

import java.util.List;

/**
 * Created by michael on 24.03.18.
 */

public interface Facade<T> {
    List<T> findAll();
    T findById(int id);
    void merge(T entity);
    void remove(int id);
}
