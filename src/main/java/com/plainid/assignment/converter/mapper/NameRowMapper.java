package com.plainid.assignment.converter.mapper;

import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Omer Dekel on 04/07/2020.
 * Helper class to map SQL result to String name.
 */
public class NameRowMapper implements RowMapper<String> {
    @Override
    public String mapRow(ResultSet resultSet, int i) throws SQLException {
        return resultSet.getString("NAME");
    }
}
