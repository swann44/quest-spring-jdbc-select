package com.wildcodeschool.wildandwizard.repository;

import com.wildcodeschool.wildandwizard.entity.School;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SchoolRepository {

    private final static String DB_URL = "jdbc:mysql://localhost:3306/spring_jdbc_quest?serverTimezone=GMT";
    private final static String DB_USER = "h4rryp0tt3r";
    private final static String DB_PASSWORD = "Horcrux4life!";

    public List<School> findAll() {

        // TODO : find all schools

        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            String request = "SELECT * FROM school;";
            PreparedStatement statement = connection.prepareStatement(request);
            ResultSet resultSet = statement.executeQuery();

            List<School> schools = new ArrayList<>();

            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                long capacity = resultSet.getLong("capacity");
                String country = resultSet.getString("country");
                schools.add(new School(id, name, capacity, country));
            }

            return schools;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public School findById(Long id) {

        // TODO : find a school by id
        List<School> schools = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            String request = "SELECT * FROM school WHERE id = ?;";
            PreparedStatement statement = connection.prepareStatement(request);
            statement.setLong(1, id);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String name = resultSet.getString("name");
                long capacity = resultSet.getLong("capacity");
                String country = resultSet.getString("country");
                return new School(id, name, capacity, country);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<School> findByCountry(String country) {

        // TODO : search schools by country
        List<School> schools = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            String request = "SELECT * FROM school WHERE country LIKE ?;";
            PreparedStatement statement = connection.prepareStatement(request);
            statement.setString(1, country);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                long capacity = resultSet.getLong("capacity");
                schools.add(new School(id, name, capacity, country));
            }
            return schools;


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
