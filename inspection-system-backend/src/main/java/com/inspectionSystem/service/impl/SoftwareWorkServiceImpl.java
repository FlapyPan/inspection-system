package com.inspectionSystem.service.impl;

import com.inspectionSystem.entity.SoftwareWork;
import com.inspectionSystem.entity.details.SoftwareWorkDetails;
import com.inspectionSystem.mapper.SoftwareWorkMapper;
import com.inspectionSystem.service.SoftwareWorkService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SoftwareWorkServiceImpl implements SoftwareWorkService {


    @Resource
    SoftwareWorkMapper mapper;

    @Override
    public SoftwareWork getSoftwareWorkById(Integer id) {
        return mapper.selectById(id);
    }

    @Override
    public List<SoftwareWork> getAllSoftwareWorks() {
        return mapper.selectAll();
    }

    @Override
    public List<SoftwareWork> getAllSoftwareWorksByEmployeeId(Integer employeeId) {
        return mapper.selectAllByEmployeeId(employeeId);
    }

    @Override
    public void addSoftwareWork(SoftwareWorkDetails softwareWorkDetails) {
        mapper.insert(softwareWorkDetails);
    }

    @Override
    public void updateSoftwareWork(SoftwareWork softwareWork) {
        mapper.update(softwareWork);
    }

    @Override
    public void removeSoftwareWork(Integer id) {
        mapper.delete(id);
    }
}
