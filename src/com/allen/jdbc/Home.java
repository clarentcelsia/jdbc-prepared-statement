package com.allen.jdbc;

import com.allen.jdbc.config.DBConnector;
import com.allen.jdbc.model.Customer;
import com.allen.jdbc.service.CustomerDAO;

import java.sql.Connection;

public class Home {

    public static void main(String[] args) {
        Home shopee = new Home();
        try {
            DBConnector dbConnector = new DBConnector();
            Connection connection = dbConnector.connect();
            shopee.customers(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void customers(Connection connection){
//        Customer customer = new Customer(
//                "Magdalena",
//                "magdalena@gmail.com",
//                "0284953023",
//                "Jl.Mayor 21"
//        );
//
        CustomerDAO customerDAO = new CustomerDAO(connection);
//        customerDAO.create(customer);
        System.out.println(customerDAO.getAll());
    }


}
