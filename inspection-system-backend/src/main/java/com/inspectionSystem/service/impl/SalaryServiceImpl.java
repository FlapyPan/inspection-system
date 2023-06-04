package com.inspectionSystem.service.impl;

import com.inspectionSystem.entity.Salary;
import com.inspectionSystem.entity.details.SalaryDetails;
import com.inspectionSystem.mapper.SalaryMapper;
import com.inspectionSystem.service.SalaryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SalaryServiceImpl implements SalaryService {

    @Resource
    SalaryMapper mapper;

    @Override
    public Salary getSalaryById(Integer id) {
        return mapper.selectById(id);
    }
    @Override
    public Salary getSalaryByEmployeeId(Integer employeeId) {
        return mapper.selectByEmployeeIdId(employeeId);
    }

    @Override
    public List<Salary> getAllSalaries() {
        return mapper.selectAll();
    }

    @Override
    public void addSalary(SalaryDetails salaryDetails) {
        mapper.insert(salaryDetails);
    }

    @Override
    public void updateSalary(Salary salary) {
        mapper.update(salary);
    }

    @Override
    public void removeSalary(Integer id) {
        mapper.deleteById(id);
    }
}
