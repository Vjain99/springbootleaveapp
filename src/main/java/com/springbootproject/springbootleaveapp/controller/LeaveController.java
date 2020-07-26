package com.springbootproject.springbootleaveapp.controller;

import com.springbootproject.springbootleaveapp.model.leave;
import com.springbootproject.springbootleaveapp.repository.leaveRowMapper;
import net.minidev.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
public class LeaveController {

    @Autowired
    JdbcTemplate jdbc;

    Logger logger= LoggerFactory.getLogger(LeaveController.class);

    @PostMapping(value = "/leave")
    public String apply(@RequestBody leave l) {

        try {
            System.out.println("insert into java.leave()  values(" + l.getId() + ",'" + l.getReason() + "','" + l.getStartDate() + "','" + l.getEndDate() + "'," + l.getIsHalfDay() + ",'" + l.getLeaveType() + "','" + l.getStatus() + "','" + l.getAppliedDate() + "','" + l.getUpdatedDate() + "')");
            jdbc.execute("insert into java.leave()  values(" + l.getId() + ",'" + l.getReason() + "','" + l.getStartDate() + "','" + l.getEndDate() + "'," + l.getIsHalfDay() + ",'" + l.getLeaveType() + "','" + l.getStatus() + "','" + l.getAppliedDate() + "'," + l.getUpdatedDate() + ")");
            System.out.println("data inserted Successfully");
            logger.info("Data inserted successfully");
        }
        catch(Exception e){
            System.out.println(e);
        }
        return "data inserted";
    }
    @PutMapping(value = "/leave/{leaveid}")
    public void update(@RequestBody leave l,@PathVariable int leaveid) {
        try {
            String sql = "update java.leave set status=? where leave_id=?";
            int status = jdbc.update(sql, l.getStatus(), leaveid);
            if (status == 0)
                logger.error("Some Error occured,zero rows affected.");
            else
                System.out.println("Leave updated successfully");
        }
        catch (Exception e){
            System.out.println(e);
        }


    }
    @GetMapping(value = "/leave")
    public String displayall()
    {String sql="Select * from java.leave";
        List<leave> select=jdbc.query(sql,new leaveRowMapper());
        String sr= JSONArray.toJSONString(select);
        if(sr.length()==0){
            logger.info("No leave request in the Database");
        }
        return sr;

    }
    @GetMapping(value = "/leave/{status}")
    public String status(@PathVariable String status)
    {
        String sql="select * from java.leave where status=?";
        System.out.println(status);
        List<leave> query=jdbc.query(sql,new Object[]{status},new leaveRowMapper());
        if(query.isEmpty()){
            logger.info("No leave present with status {}",status);
        }
        return JSONArray.toJSONString(query);
    }
    @DeleteMapping(value = "/leave/{leaveid}")
    public String delete(@PathVariable int leaveid)
    {
        String sql="select * from java.leave where leave_id=?";
        List<leave> query=jdbc.query(sql,new Object[]{leaveid},new leaveRowMapper());
        String qy="delete from java.leave where leave_id=?";
        int status= jdbc.update(qy,leaveid);
        if( status==0){
            logger.error(" leave id {} does not exist",leaveid);
        }
        return JSONArray.toJSONString(query);


    }

}
