package com.inspectionSystem.entity;

import com.inspectionSystem.entity.details.ThesisDetails;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Thesis {
    Integer id;
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

    public Thesis(ThesisDetails thesisDetails) {
        this.type = thesisDetails.getType();
        this.topic = thesisDetails.getTopic();
        this.approvalStatus = thesisDetails.getApprovalStatus();
        this.score = thesisDetails.getScore();
        this.department = thesisDetails.getDepartment();
        this.publishTime = thesisDetails.getPublishTime();
        this.disciplineCategory = thesisDetails.getDisciplineCategory();
        this.firstLevelDiscipline = thesisDetails.getFirstLevelDiscipline();
        this.attachmentAddress = thesisDetails.getAttachmentAddress();
        this.journalType = thesisDetails.getJournalType();
        this.participantNumber = thesisDetails.getParticipantNumber();
        this.totalNumber = thesisDetails.getTotalNumber();
        this.journalNumber = thesisDetails.getJournalNumber();
        this.journalName = thesisDetails.getJournalName();
        this.firstAuthor = thesisDetails.getFirstAuthor();
        this.firstAuthorEmployeeId = thesisDetails.getFirstAuthorEmployeeId();
        this.firstAuthorType = thesisDetails.getFirstAuthorType();
        this.employeeId = thesisDetails.getEmployeeId();
    }


}

