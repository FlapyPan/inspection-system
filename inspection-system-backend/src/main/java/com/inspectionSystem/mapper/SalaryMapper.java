package com.inspectionSystem.mapper;

import com.inspectionSystem.entity.Salary;
import com.inspectionSystem.entity.details.SalaryDetails;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface SalaryMapper {
    @Insert("INSERT INTO salary (basic_salary, kpi, kpi_calculates_salary, other_allowance, remark, create_time, employee_id) VALUES (#{basicSalary}, #{kpi}, #{kpiCalculatesSalary}, #{otherAllowance}, #{remark}, NOW(), #{employeeId})")
    void insert(SalaryDetails salaryDetails);

    @Delete("DELETE FROM salary WHERE id=#{id}")
    void deleteById(Integer id);

    @Update("UPDATE salary SET basic_salary=#{basicSalary}, kpi=#{kpi}, kpi_calculates_salary=#{kpiCalculatesSalary}, other_allowance=#{otherAllowance}, remark=#{remark}, create_time=#{createTime} WHERE id=#{id}")
    void update(Salary salary);

    @Select("SELECT * FROM salary WHERE id=#{id}")
    Salary selectById(Integer id);

    @Select("SELECT * FROM salary WHERE employee_id=#{employeeId}")
    Salary selectByEmployeeIdId(Integer employeeId);

    @Select("SELECT * FROM salary")
    List<Salary> selectAll();
}
