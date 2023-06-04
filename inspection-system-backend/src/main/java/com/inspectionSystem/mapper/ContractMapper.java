package com.inspectionSystem.mapper;

import com.inspectionSystem.entity.Contract;
import com.inspectionSystem.entity.details.ContractDetails;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ContractMapper {

    @Select("SELECT * FROM contracts")
    List<Contract> selectAll();

    @Select("SELECT * FROM contracts WHERE employee_id = #{employeeId}")
    List<Contract> selectAllByEmployeeId(Integer employeeId);

    @Select("SELECT * FROM contracts WHERE id = #{id}")
    Contract selectById(Integer id);

    @Insert("INSERT INTO contracts(intellectual_property_type, name, approval_status, score, undertaking_unit, contract_code, contact_person, person_title, person_type, person_phone, buyer_nature, buyer_category, seller_name, buyer_region, plan_category, department, undertaking_department, contract_subcategory, financial_number, social_objective, create_time, employee_id) " +
            "VALUES (#{intellectualPropertyType}, #{name}, #{approvalStatus}, #{score}, #{undertakingUnit}, #{contractCode}, #{contactPerson}, #{personTitle}, #{personType}, #{personPhone}, #{buyerNature}, #{buyerCategory}, #{sellerName}, #{buyerRegion}, #{planCategory}, #{department}, #{undertakingDepartment}, #{contractSubcategory}, #{financialNumber}, #{socialObjective}, NOW(), #{employeeId})")
    void insert(ContractDetails contractDetails);

    @Update("UPDATE contracts SET intellectual_property_type = #{intellectualPropertyType}, name = #{name}, approval_status = #{approvalStatus}, score = #{score}, undertaking_unit = #{undertakingUnit}, contract_code = #{contractCode}, contact_person = #{contactPerson}, person_title = #{personTitle}, person_type = #{personType}, person_phone = #{personPhone}, buyer_nature = #{buyerNature}, buyer_category = #{buyerCategory}, seller_name = #{sellerName}, buyer_region = #{buyerRegion}, plan_category = #{planCategory}, department = #{department}, undertaking_department = #{undertakingDepartment}, contract_subcategory = #{contractSubcategory}, financial_number = #{financialNumber}, social_objective = #{socialObjective}, employee_id = #{employeeId} WHERE id = #{id}")
    void update(Contract contract);

    @Delete("DELETE FROM contracts WHERE id = #{id}")
    void delete(Integer id);



}
