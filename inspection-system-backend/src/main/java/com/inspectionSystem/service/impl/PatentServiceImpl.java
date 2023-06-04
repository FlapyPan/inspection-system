package com.inspectionSystem.service.impl;

import com.inspectionSystem.entity.Patent;
import com.inspectionSystem.entity.details.PatentDetails;
import com.inspectionSystem.mapper.PatentMapper;
import com.inspectionSystem.service.PatentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PatentServiceImpl implements PatentService {

    @Resource
    PatentMapper mapper;

    @Override
    public Patent getPatentById(Integer id) {
        return mapper.selectById(id);
    }

    @Override
    public List<Patent> getAllPatents() {
        return mapper.selectAll();
    }

    @Override
    public List<Patent> getPatentsByEmployeeId(Integer employeeId) {
        return mapper.selectPatentsByEmployeeId(employeeId);
    }

    @Override
    public void addPatent(PatentDetails patentDetails) {
        mapper.insert(patentDetails);
    }

    @Override
    public void updatePatent(Patent patent) {
        mapper.update(patent);
    }

    @Override
    public void removePatent(Integer id) {
        mapper.delete(id);
    }
}
