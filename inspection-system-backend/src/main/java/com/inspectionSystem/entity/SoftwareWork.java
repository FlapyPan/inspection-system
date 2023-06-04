package com.inspectionSystem.entity;

import com.inspectionSystem.entity.details.SoftwareWorkDetails;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SoftwareWork {
    Integer id;
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

    public SoftwareWork(SoftwareWorkDetails details) {
        this.type = details.getType();
        this.name = details.getName();
        this.approvalStatus = details.getApprovalStatus();
        this.score = details.getScore();
        this.registrationNumber = details.getRegistrationNumber();
        this.affiliatedUnit = details.getAffiliatedUnit();
        this.primaryDiscipline = details.getPrimaryDiscipline();
        this.publicationDate = details.getPublicationDate();
        this.authorCount = details.getAuthorCount();
        this.firstAuthorName = details.getFirstAuthorName();
        this.firstAuthorEmployeeId = details.getFirstAuthorEmployeeId();
        this.firstAuthorType = details.getFirstAuthorType();
        this.employeeId = details.getEmployeeId();
    }

}

