package com.inspectionSystem.service;

import com.inspectionSystem.entity.Salary;
import com.inspectionSystem.entity.details.SalaryDetails;

import java.util.List;

public interface SalaryService {

    Salary getSalaryById(Integer id);
    Salary getSalaryByEmployeeId(Integer id);
    List<Salary> getAllSalaries();
    void addSalary(SalaryDetails salaryDetails);
    void updateSalary(Salary salary);
    void removeSalary(Integer id);

}
