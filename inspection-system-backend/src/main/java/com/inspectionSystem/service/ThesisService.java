package com.inspectionSystem.service;

import com.inspectionSystem.entity.Thesis;
import com.inspectionSystem.entity.details.ThesisDetails;

import java.util.List;

public interface ThesisService {

    Thesis getThesisById(Integer id);
    List<Thesis> getAllThesis();
    void addThesis(ThesisDetails thesisDetails);
    void updateThesis(Thesis thesis);
    void removeThesis(Integer id);

    List<Thesis> getThesisByEmployeeId(Integer employeeId);

}
