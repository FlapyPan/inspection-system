package com.inspectionSystem.entity.details;

import lombok.Data;

@Data
public class BookDetails {
    String type;
    String name;
    String approvalStatus;
    Float score;
    String isbn;
    String intellectualProperty;
    String researchDept;
    String firstDiscipline;
    String language;
    String publisherLevel;
    String publisherUnit;
    String publishTime;
    String disciplineCategory;
    String projectSource;
    String schoolSignature;
    String publishLocation;
    Integer participantsCounts;
    Integer totalWords;
    String firstAuthorName;
    Integer firstAuthorEmployeeId;
    String firstAuthorType;
    String employeeId;
}
