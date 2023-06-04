package com.inspectionSystem.mapper;

import com.inspectionSystem.entity.Thesis;
import com.inspectionSystem.entity.details.ThesisDetails;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ThesisMapper {

    @Insert("INSERT INTO thesis (type, topic, approval_status, score, department, publish_time, " +
            "discipline_category, first_level_discipline, attachment_address, journal_type, participant_number, " +
            "total_number, journal_number, journal_name, first_author, first_author_employee_id, first_author_type, employee_id) " +
            "VALUES (#{type}, #{topic}, #{approvalStatus}, #{score}, #{department}, #{publishTime}, #{disciplineCategory}, " +
            "#{firstLevelDiscipline}, #{attachmentAddress}, #{journalType}, #{participantNumber}, #{totalNumber}, " +
            "#{journalNumber}, #{journalName}, #{firstAuthor}, #{firstAuthorEmployeeId}, #{firstAuthorType}, #{employeeId})")
    void insertThesis(ThesisDetails thesisDetails);

    @Update("UPDATE thesis SET type=#{type}, topic=#{topic}, approval_status=#{approvalStatus}, score=#{score}," +
            " department=#{department}, publish_time=#{publishTime}, discipline_category=#{disciplineCategory}," +
            " first_level_discipline=#{firstLevelDiscipline}, attachment_address=#{attachmentAddress}," +
            " journal_type=#{journalType}, participant_number=#{participantNumber}, total_number=#{totalNumber}," +
            " journal_number=#{journalNumber}, journal_name=#{journalName}, first_author=#{firstAuthor}," +
            " first_author_employee_id=#{firstAuthorEmployeeId}, first_author_type=#{firstAuthorType}," +
            " employee_id = #{employeeId}" +
            " WHERE id=#{id}")
    void updateThesis(Thesis thesis);

    @Delete("DELETE FROM thesis WHERE id=#{id}")
    void deleteThesis(int id);

    @Select("SELECT * FROM thesis WHERE id=#{id}")
    Thesis selectThesisById(int id);

    @Select("SELECT * FROM thesis")
    List<Thesis> selectAllThesis();

    @Select("SELECT * FROM thesis WHERE employee_id=#{employeeId}")
    List<Thesis> selectThesisByEmployeeId(Integer employeeId);

}

