package com.allen.jdbc.service;

import com.allen.jdbc.mapper.CustomerMapper;
import com.allen.jdbc.model.Customer;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO implements DAO<Customer> {

    private CustomerMapper customerMapper;

    public CustomerDAO(Connection connection) {
        customerMapper = new CustomerMapper(connection);
    }

    @Override
    public void create(Customer object) {
        try {
            String query =
                    "INSERT INTO customer(name, email, phone, address) VALUES (?, ?, ?, ?)";
            customerMapper.insertMapping(query, object);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Customer object) {
        try {
            String query =
                    "UPDATE customer SET name = ?, email = ?, phone = ?, address = ? WHERE customer_id = ?";
            customerMapper.updateMapping(query, object);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Customer> getAll() {
        List<Customer> customers = new ArrayList<>();
        try {
            String query = "SELECT * FROM customer";
            customers = customerMapper.select(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customers;
    }
}
