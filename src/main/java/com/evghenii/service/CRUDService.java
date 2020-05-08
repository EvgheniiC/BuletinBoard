package com.evghenii.service;

import java.util.List;

public interface CRUDService<T> {
    void save(T t);

    void update(T t);

    void deleteById(int id);

    List<T> findAll();
}
