package com.inspectionSystem.service.impl;

import com.inspectionSystem.entity.Thesis;
import com.inspectionSystem.entity.details.ThesisDetails;
import com.inspectionSystem.mapper.ThesisMapper;
import com.inspectionSystem.service.ThesisService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ThesisServiceImpl implements ThesisService {

    @Resource
    ThesisMapper mapper;

    @Override
    public Thesis getThesisById(Integer id) {
        return mapper.selectThesisById(id);
    }

    @Override
    public List<Thesis> getAllThesis() {
        return mapper.selectAllThesis();
    }

    @Override
    public void addThesis(ThesisDetails thesisDetails) {
        mapper.insertThesis(thesisDetails);
    }

    @Override
    public void updateThesis(Thesis thesis) {
        mapper.updateThesis(thesis);
    }

    @Override
    public void removeThesis(Integer id) {
        mapper.deleteThesis(id);
    }

    @Override
    public List<Thesis> getThesisByEmployeeId(Integer employeeId) {
        return mapper.selectThesisByEmployeeId(employeeId);
    }
}
