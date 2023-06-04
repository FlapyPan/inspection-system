package com.inspectionSystem.service;

import com.inspectionSystem.entity.SoftwareWork;
import com.inspectionSystem.entity.details.SoftwareWorkDetails;

import java.util.List;

public interface SoftwareWorkService {

    SoftwareWork getSoftwareWorkById(Integer id);
    List<SoftwareWork> getAllSoftwareWorks();
    List<SoftwareWork> getAllSoftwareWorksByEmployeeId(Integer employeeId);
    void addSoftwareWork(SoftwareWorkDetails softwareWorkDetails);
    void updateSoftwareWork(SoftwareWork softwareWork);
    void removeSoftwareWork(Integer id);
}
