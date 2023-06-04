package com.inspectionSystem.controller;

import com.inspectionSystem.entity.Salary;
import com.inspectionSystem.entity.details.SalaryDetails;
import com.inspectionSystem.entity.resp.RestBean;
import com.inspectionSystem.service.SalaryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
public class SalaryController {

    @Resource
    SalaryService service;

    @GetMapping("/salary")
    public RestBean<?> salaryList(){
        return new RestBean<>(HttpStatus.OK,"获取成功！",service.getAllSalaries());
    }


    @GetMapping("/salary/{id}")
    public RestBean<?> getSalary(@PathVariable Integer id){
        return new RestBean<>(HttpStatus.OK,"获取成功！",service.getSalaryById(id));
    }

    @GetMapping("/salary/employee/{employeeId}")
    public RestBean<?> getSalaryByEmployeeId(@PathVariable Integer employeeId){
        return new RestBean<>(HttpStatus.OK,"获取成功！",service.getSalaryByEmployeeId(employeeId));
    }

    @PostMapping("/salary/{id}")
    public RestBean<?> change(@RequestBody SalaryDetails salaryDetails, @PathVariable Integer id){
        Salary salary = new Salary(salaryDetails);
        salary.setId(id);
        service.updateSalary(salary);
        return new RestBean<Void>(HttpStatus.OK,"修改成功！");
    }

    @PutMapping("/salary")
    public RestBean<?> addSalary(@RequestBody SalaryDetails salaryDetails){
        service.addSalary(salaryDetails);
        return new RestBean<Void>(HttpStatus.OK,"添加成功！");
    }



    @DeleteMapping("/salary/{id}")
    public RestBean<?> remove(@PathVariable Integer id){
        service.removeSalary(id);
        return new RestBean<Void>(HttpStatus.OK,"删除成功！");
    }

}
