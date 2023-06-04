package com.inspectionSystem.service.impl;

import com.inspectionSystem.entity.Contract;
import com.inspectionSystem.entity.details.ContractDetails;
import com.inspectionSystem.mapper.ContractMapper;
import com.inspectionSystem.service.ContractService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ContractServiceImpl implements ContractService {

    @Resource
    ContractMapper mapper;

    @Override
    public Contract getContractById(Integer id) {
        return mapper.selectById(id);
    }

    @Override
    public List<Contract> getAllContracts() {
        return mapper.selectAll();
    }

    @Override
    public List<Contract> getContractsByEmployeeId(Integer employeeId) {
        return mapper.selectAllByEmployeeId(employeeId);
    }

    @Override
    public void addContract(ContractDetails contractDetails) {
        mapper.insert(contractDetails);
    }

    @Override
    public void updateContract(Contract contract) {
        mapper.update(contract);
    }

    @Override
    public void removeContract(Integer id) {
        mapper.delete(id);
    }

    @Override
    public void mergeContracts(List<Integer> contracts) {
        for (Integer id : contracts.subList(1, contracts.size())) {
            mapper.delete(id);
        }
    }
}
