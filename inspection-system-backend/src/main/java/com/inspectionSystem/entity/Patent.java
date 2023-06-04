package com.inspectionSystem.entity;

import com.inspectionSystem.entity.details.PatentDetails;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Patent {

    Integer id;
    String type;
    String name;
    Double score;
    String approvalStatus;
    String applicationNumber;
    String patentee;
    String agency;
    String patentScope;
    String schoolSignature;
    String applicationDate;
    String firstInventor;
    Integer firstInventorEmployeeId;
    String firstInventorType;
    String employeeId;

    public Patent(PatentDetails patentDetails) {
        type = patentDetails.getType();
        name = patentDetails.getName();
        score = patentDetails.getScore();
        approvalStatus = patentDetails.getApprovalStatus();
        applicationNumber = patentDetails.getApplicationNumber();
        patentee = patentDetails.getPatentee();
        agency = patentDetails.getAgency();
        patentScope = patentDetails.getPatentScope();
        schoolSignature = patentDetails.getSchoolSignature();
        applicationDate = patentDetails.getApplicationDate();
        firstInventor = patentDetails.getFirstInventor();
        firstInventorEmployeeId = patentDetails.getFirstInventorEmployeeId();
        firstInventorType = patentDetails.getFirstInventorType();
        employeeId = patentDetails.getEmployeeId();
    }


}

