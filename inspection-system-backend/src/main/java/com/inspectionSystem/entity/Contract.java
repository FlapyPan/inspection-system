package com.inspectionSystem.entity;

import com.inspectionSystem.entity.details.ContractDetails;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Contract {
    Integer id;
    String intellectualPropertyType;
    String name;
    String approvalStatus;
    Float score;
    String undertakingUnit;
    String contractCode;
    String contactPerson;
    String personTitle;
    String personType;
    String personPhone;
    String buyerNature;
    String buyerCategory;
    String sellerName;
    String buyerRegion;
    String planCategory;
    String department;
    String undertakingDepartment;
    String contractSubcategory;
    String financialNumber;
    String socialObjective;
    String createTime;
    String employeeId;


    public Contract(ContractDetails details) {
        intellectualPropertyType = details.getIntellectualPropertyType();
        name = details.getName();
        approvalStatus = details.getApprovalStatus();
        score = details.getScore();
        undertakingUnit = details.getUndertakingUnit();
        contractCode = details.getContractCode();
        contactPerson = details.getContactPerson();
        personTitle = details.getPersonTitle();
        personType = details.getPersonType();
        personPhone = details.getPersonPhone();
        buyerNature = details.getBuyerNature();
        buyerCategory = details.getBuyerCategory();
        sellerName = details.getSellerName();
        buyerRegion = details.getBuyerRegion();
        planCategory = details.getPlanCategory();
        department = details.getDepartment();
        undertakingDepartment = details.getUndertakingDepartment();
        contractSubcategory = details.getContractSubcategory();
        financialNumber = details.getFinancialNumber();
        socialObjective = details.getSocialObjective();
        employeeId = details.getEmployeeId();
    }


}

