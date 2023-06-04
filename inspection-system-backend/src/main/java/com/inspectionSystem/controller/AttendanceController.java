package com.inspectionSystem.controller;

import com.inspectionSystem.entity.Attendance;
import com.inspectionSystem.entity.details.AttendanceDetails;
import com.inspectionSystem.entity.resp.RestBean;
import com.inspectionSystem.service.AttendanceService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
public class AttendanceController {


    @Resource
    AttendanceService service;

    @GetMapping("/attendance")//获取考勤列表
    public RestBean<?> contractList(){
        return new RestBean<>(HttpStatus.OK,"获取成功！",service.getAllAttendances());
    }


    @GetMapping("/attendance/{id}")//获取考勤
    public RestBean<?> getAttendance(@PathVariable Integer id){
        return new RestBean<>(HttpStatus.OK,"获取成功！",service.getAttendanceById(id));
    }

    @GetMapping("/attendance/employee/{employeeId}")//获取考勤
    public RestBean<?> getAttendanceByEmployeeId(@PathVariable Integer employeeId){
        return new RestBean<>(HttpStatus.OK,"获取成功！",service.getAttendanceByUserId(employeeId));
    }

    @PostMapping("/attendance/{id}")//修改考勤
    public RestBean<?> change(@RequestBody AttendanceDetails attendanceDetails, @PathVariable Integer id){
        Attendance attendance = new Attendance(attendanceDetails);
        attendance.setId(id);
        service.updateAttendance(attendance);
        return new RestBean<Void>(HttpStatus.OK,"修改成功！");
    }

    @PutMapping("/attendance")//添加考勤
    public RestBean<?> addAttendance(@RequestBody AttendanceDetails attendanceDetails){
        service.addAttendance(attendanceDetails);
        return new RestBean<Void>(HttpStatus.OK,"添加成功！");
    }


    @DeleteMapping("/attendance/{id}")//删除考勤
    public RestBean<?> remove(@PathVariable Integer id){
        service.removeAttendance(id);
        return new RestBean<Void>(HttpStatus.OK,"删除成功！");
    }


}
