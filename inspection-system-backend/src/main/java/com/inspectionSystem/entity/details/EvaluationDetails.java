package com.inspectionSystem.entity.details;

import lombok.Data;

@Data
public class EvaluationDetails {
    Float score;
    String content;
    Integer year;
    Integer month;
    String employeeId;
}
