package com.inspectionSystem.mapper;

import com.inspectionSystem.entity.Patent;
import com.inspectionSystem.entity.details.PatentDetails;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PatentMapper {

    @Insert("INSERT INTO patent(type, name, approval_status, application_number, patentee, agency, patent_scope, school_signature, application_date, first_inventor, first_inventor_employee_id, first_inventor_type, employee_id) " +
            "VALUES(#{type}, #{name}, #{approvalStatus}, #{applicationNumber}, #{patentee}, #{agency}, #{patentScope}, #{schoolSignature}, #{applicationDate}, #{firstInventor}, #{firstInventorEmployeeId}, #{firstInventorType}, #{employeeId})")
    void insert(PatentDetails patentDetails);

    @Delete("DELETE FROM patent WHERE id = #{id}")
    void delete(Integer id);

    @Update("UPDATE patent SET type = #{type}, name = #{name}, approval_status = #{approvalStatus}, application_number = #{applicationNumber}, patentee = #{patentee}, agency = #{agency}, " +
            "patent_scope = #{patentScope}, school_signature = #{schoolSignature}, application_date = #{applicationDate}, first_inventor = #{firstInventor}, first_inventor_employee_id = #{firstInventorEmployeeId}, first_inventor_type = #{firstInventorType}, employee_id = #{employeeId} " +
            "WHERE id = #{id}")
    void update(Patent patent);

    @Select("SELECT * FROM patent WHERE id = #{id}")
    Patent selectById(Integer id);

    @Select("SELECT * FROM patent")
    List<Patent> selectAll();

    @Select("SELECT * FROM patent WHERE employee_id = #{employeeId}")
    List<Patent> selectPatentsByEmployeeId(Integer employeeId);
}
