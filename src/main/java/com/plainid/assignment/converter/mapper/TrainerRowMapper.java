package com.plainid.assignment.converter.mapper;

import com.plainid.assignment.dao.Pokemon;
import com.plainid.assignment.dao.Trainer;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Omer Dekel on 03/07/2020.
 *  Helper class to map SQL results to Trainer pojo
 */
public class TrainerRowMapper implements RowMapper<Trainer> {
    @Override
    public Trainer mapRow(ResultSet resultSet, int i) throws SQLException {
        Trainer trainer = new Trainer();
        trainer.setId(resultSet.getInt("ID"));
        trainer.setLevel(resultSet.getInt("LEVEL"));
        trainer.setName(resultSet.getString("NAME"));
        return trainer;
    }
}
