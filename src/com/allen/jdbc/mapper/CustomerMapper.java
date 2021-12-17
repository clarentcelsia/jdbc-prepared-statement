package com.allen.jdbc.mapper;

import com.allen.jdbc.model.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerMapper extends Mapper<Customer> {

    public CustomerMapper(Connection connection) {
        super(connection);
    }

    @Override
    public void insertMapping(String query, Customer data) throws SQLException {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, data.getName());
            preparedStatement.setString(2, data.getEmail());
            preparedStatement.setString(3, data.getPhone());
            preparedStatement.setString(4, data.getAddress());
            if (preparedStatement.executeUpdate() == 1) {
                System.out.println("Insert succeed!");
                connection.commit();
            } else System.out.println("Insert failed!");
        } catch (SQLException e) {
            connection.rollback();
            e.printStackTrace();
        }finally {
            preparedStatement.close();
            connection.close();
        }
    }

    @Override
    public void updateMapping(String query, Customer data) throws SQLException {

        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(query);
            statement.setString(1, data.getName());
            statement.setString(2, data.getEmail());
            statement.setString(3, data.getPhone());
            statement.setString(4, data.getAddress());
            if (statement.executeUpdate() == 1) {
                System.out.println("update succeed!");
                connection.commit();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();
        } finally {
            statement.close();
            connection.close();
        }

    }

    @Override
    public List<Customer> select(String query) throws SQLException {
        List<Customer> customers = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.executeQuery();

            while (preparedStatement.getResultSet().next()){
                String name = preparedStatement.getResultSet().getString("name");
                String email = preparedStatement.getResultSet().getString("email");
                String phone = preparedStatement.getResultSet().getString("phone");
                String address = preparedStatement.getResultSet().getString("address");

                customers.add(new Customer(name, email, phone, address));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            preparedStatement.close();
            connection.close();
        }

        return customers;
    }
}
