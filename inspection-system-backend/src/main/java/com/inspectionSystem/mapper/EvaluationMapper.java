package com.inspectionSystem.mapper;

import com.inspectionSystem.entity.Evaluation;
import com.inspectionSystem.entity.details.EvaluationDetails;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface EvaluationMapper {
    @Select("SELECT * FROM evaluation WHERE employee_id = #{id} AND `month` = MONTH(NOW())")
    Evaluation selectEvaluationById(Integer id);

    @Select("SELECT * FROM evaluation WHERE employee_id = #{employeeId} AND `year` = #{year} AND `month` = #{month}")
    Evaluation selectEvaluation(Integer employeeId, Integer year, Integer month);

    @Select("SELECT * FROM evaluation WHERE employee_id = #{id}")
    List<Evaluation> selectEvaluationListById(Integer id);

    @Insert("INSERT INTO evaluation (score, content, `month`, `year`,employee_id) VALUES (#{score}, #{content}, #{month}, #{year}, #{employeeId})")
    int insertEvaluation(EvaluationDetails evaluationDetails);

    @Update("UPDATE evaluation SET score = #{score}, content = #{content}, `month` = #{month}, `year` = #{year} WHERE id = #{id}")
    void updateEvaluation(Evaluation evaluation);

}

