package com.inspectionSystem.entity.details;

import lombok.Data;

@Data
public class SalaryDetails {

    Double basicSalary;
    Double kpi;
    Double kpiCalculatesSalary;
    Double otherAllowance;
    String remark;
    String employeeId;

}
