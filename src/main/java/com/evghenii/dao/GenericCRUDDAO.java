package com.evghenii.dao;

import java.util.List;

public interface GenericCRUDDAO<T> {
    void save(T t);

    void update(T t);

    void deleteById(int id);

    List<T> findAll();

}
