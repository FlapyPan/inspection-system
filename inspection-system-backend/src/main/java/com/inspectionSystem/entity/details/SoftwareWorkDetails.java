package com.inspectionSystem.entity.details;

import lombok.Data;

@Data
public class SoftwareWorkDetails {
    String type;
    String name;
    String approvalStatus;
    Float score;
    String registrationNumber;
    String affiliatedUnit;
    String primaryDiscipline;
    String publicationDate;
    Integer authorCount;
    String firstAuthorName;
    String firstAuthorEmployeeId;
    String firstAuthorType;
    String employeeId;
}
