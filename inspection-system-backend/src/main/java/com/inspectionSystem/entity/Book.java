package com.inspectionSystem.entity;

import com.inspectionSystem.entity.details.BookDetails;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Book {
    Integer id;
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

    public Book(BookDetails details) {
        type = details.getType();
        name = details.getName();
        approvalStatus = details.getApprovalStatus();
        score = details.getScore();
        isbn = details.getIsbn();
        intellectualProperty = details.getIntellectualProperty();
        researchDept = details.getResearchDept();
        firstDiscipline = details.getFirstDiscipline();
        language = details.getLanguage();
        publisherLevel = details.getPublisherLevel();
        publisherUnit = details.getPublisherUnit();
        publishTime = details.getPublishTime();
        disciplineCategory = details.getDisciplineCategory();
        projectSource = details.getProjectSource();
        schoolSignature = details.getSchoolSignature();
        publishLocation = details.getPublishLocation();
        participantsCounts = details.getParticipantsCounts();
        totalWords = details.getTotalWords();
        firstAuthorName = details.getFirstAuthorName();
        firstAuthorEmployeeId = details.getFirstAuthorEmployeeId();
        firstAuthorType = details.getFirstAuthorType();
        employeeId = details.getEmployeeId();
    }


}
