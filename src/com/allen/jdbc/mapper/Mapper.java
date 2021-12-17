package com.allen.jdbc.mapper;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public abstract class Mapper<T>{

    protected Connection connection;

    Mapper(Connection connection){
        this.connection = connection;
    }

    public abstract void insertMapping(String query, T data) throws Exception;

    public abstract void updateMapping(String query, T data) throws Exception;

    public abstract List<T> select(String query) throws SQLException;
}
