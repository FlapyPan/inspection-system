package com.inspectionSystem.entity;

import com.inspectionSystem.entity.details.SalaryDetails;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Salary {
    Integer id;
    Double basicSalary;
    Double kpi;
    Double kpiCalculatesSalary;
    Double otherAllowance;
    String remark;
    String createTime;
    String employeeId;

    public Salary(SalaryDetails salaryDetails) {
        this.basicSalary = salaryDetails.getBasicSalary();
        this.kpi = salaryDetails.getKpi();
        this.kpiCalculatesSalary = salaryDetails.getKpiCalculatesSalary();
        this.otherAllowance = salaryDetails.getOtherAllowance();
        this.employeeId = salaryDetails.getEmployeeId();
    }

}

