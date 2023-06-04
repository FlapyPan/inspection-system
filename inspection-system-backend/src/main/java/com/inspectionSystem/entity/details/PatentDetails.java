package com.inspectionSystem.entity.details;

import lombok.Data;

@Data
public class PatentDetails {
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
}
