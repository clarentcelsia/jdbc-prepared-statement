package com.allen.jdbc.service;

import com.allen.jdbc.config.DBConnector;

import java.util.List;

public interface DAO<T> {

    void create(T object);

    void update(T object);

    List<T> getAll();
}
