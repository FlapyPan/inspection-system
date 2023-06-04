package com.inspectionSystem.service;

import com.inspectionSystem.entity.Contract;
import com.inspectionSystem.entity.details.ContractDetails;

import java.util.List;

public interface ContractService {

    Contract getContractById(Integer id);
    List<Contract> getAllContracts();
    List<Contract> getContractsByEmployeeId(Integer employeeId);
    void addContract(ContractDetails contractDetails);
    void updateContract(Contract contract);
    void removeContract(Integer id);

    void mergeContracts(List<Integer> contracts);

}
