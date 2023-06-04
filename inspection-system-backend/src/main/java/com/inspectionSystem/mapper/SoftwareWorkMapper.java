package com.inspectionSystem.mapper;

import com.inspectionSystem.entity.SoftwareWork;
import com.inspectionSystem.entity.details.SoftwareWorkDetails;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface SoftwareWorkMapper {
    @Insert("INSERT INTO software_work(type, name, approval_status, score, registration_number, affiliated_unit, primary_discipline, publication_date, author_count, first_author_name, first_author_employee_id, first_author_type, employee_id) " +
            "VALUES(#{type}, #{name}, #{approvalStatus}, #{score}, #{registrationNumber}, #{affiliatedUnit}, #{primaryDiscipline}, #{publicationDate}, #{authorCount}, #{firstAuthorName}, #{firstAuthorEmployeeId}, #{firstAuthorType}, #{employeeId})")
    void insert(SoftwareWorkDetails softwareWork);

    @Update("UPDATE software_work SET type=#{type}, name=#{name}, approval_status=#{approvalStatus}, score=#{score}, registration_number=#{registrationNumber}, affiliated_unit=#{affiliatedUnit}, primary_discipline=#{primaryDiscipline}, publication_date=#{publicationDate}, author_count=#{authorCount}, first_author_name=#{firstAuthorName}, first_author_employee_id=#{firstAuthorEmployeeId}, first_author_type=#{firstAuthorType}, employee_id = #{employeeId} WHERE id=#{id}")
    void update(SoftwareWork softwareWork);

    @Delete("DELETE FROM software_work WHERE id=#{id}")
    void delete(int id);

    @Select("SELECT * FROM software_work WHERE id=#{id}")
    SoftwareWork selectById(int id);

    @Select("SELECT * FROM software_work")
    List<SoftwareWork> selectAll();

    @Select("SELECT * FROM software_work WHERE employee_id = #{employeeId}")
    List<SoftwareWork> selectAllByEmployeeId(Integer employeeId);
}
