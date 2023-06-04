package com.inspectionSystem.service;


import com.inspectionSystem.entity.Patent;
import com.inspectionSystem.entity.details.PatentDetails;

import java.util.List;

public interface PatentService {

    Patent getPatentById(Integer id);
    List<Patent> getAllPatents();
    List<Patent> getPatentsByEmployeeId(Integer employeeId);
    void addPatent(PatentDetails patentDetails);
    void updatePatent(Patent patent);
    void removePatent(Integer id);
}
