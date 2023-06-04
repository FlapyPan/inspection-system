package com.inspectionSystem.entity;

import com.inspectionSystem.entity.details.EvaluationDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Evaluation {
    Integer id;
    Float score;
    String content;
    Integer year;
    Integer month;
    String employeeId;

    public Evaluation(EvaluationDetails details) {

        score = details.getScore();
        content = details.getContent();
        year = details.getYear();
        month = details.getMonth();
        employeeId = details.getEmployeeId();

    }
}

