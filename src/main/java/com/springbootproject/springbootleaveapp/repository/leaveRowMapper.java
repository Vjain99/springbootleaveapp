package com.springbootproject.springbootleaveapp.repository;

import com.springbootproject.springbootleaveapp.model.leave;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class leaveRowMapper implements RowMapper<leave> {

    @Override
    public leave mapRow(ResultSet rs, int rowNum) throws SQLException {

        leave person = new leave();
        person.setId(rs.getInt("leave_id"));
        person.setReason(rs.getString("reason"));
        person.setStartDate(rs.getString("startDate"));
        person.setEndDate(rs.getString("endDate"));
        person.setIsHalfDay(rs.getInt("isHalfDay"));
        person.setLeaveType(rs.getString("leaveType"));
        person.setStatus(rs.getString("status"));
        person.setAppliedDate(rs.getString("appliedDate"));
        person.setUpdatedDate(rs.getString("updatedDate"));

        return person;

    }
}

