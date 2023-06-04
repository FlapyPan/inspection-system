package com.inspectionSystem.entity.details;

import lombok.Data;

@Data
public class ThesisDetails {
    String type;
    String topic;
    String approvalStatus;
    Float score;
    String department;
    String publishTime;
    String disciplineCategory;
    String firstLevelDiscipline;
    String attachmentAddress;
    String journalType;
    Integer participantNumber;
    Integer totalNumber;
    Integer journalNumber;
    String journalName;
    String firstAuthor;
    Integer firstAuthorEmployeeId;
    String firstAuthorType;
    String employeeId;
}
