package com.inspectionSystem.controller;

import com.inspectionSystem.entity.Contract;
import com.inspectionSystem.entity.details.ContractDetails;
import com.inspectionSystem.entity.resp.RestBean;
import com.inspectionSystem.service.ContractService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class ContractController {

    @Resource
    ContractService service;

    @GetMapping("/project")//获取合同列表
    public RestBean<?> contractList(){
        return new RestBean<>(HttpStatus.OK,"获取成功！",service.getAllContracts());
    }


    @GetMapping("/project/{id}")//获取合同
    public RestBean<?> getContract(@PathVariable Integer id){
        return new RestBean<>(HttpStatus.OK,"获取成功！",service.getContractById(id));
    }

    @GetMapping("/project/employee/{employeeId}")//获取合同
    public RestBean<?> getContractByEmployeeId(@PathVariable Integer employeeId){
        return new RestBean<>(HttpStatus.OK,"获取成功！",service.getContractsByEmployeeId(employeeId));
    }

    @PostMapping("/project/{id}")//修改合同
    public RestBean<?> change(@RequestBody ContractDetails contractDetails, @PathVariable Integer id){
        Contract contract = new Contract(contractDetails);
        contract.setId(id);
        service.updateContract(contract);
        return new RestBean<Void>(HttpStatus.OK,"修改成功！");
    }

    @PutMapping("/project")//添加合同
    public RestBean<?> addContract(@RequestBody ContractDetails contractDetails){
        service.addContract(contractDetails);
        return new RestBean<Void>(HttpStatus.OK,"添加成功！");
    }



    @DeleteMapping("/project/{id}")//删除合同
    public RestBean<?> remove(@PathVariable Integer id){
        service.removeContract(id);
        return new RestBean<Void>(HttpStatus.OK,"删除成功！");
    }

    @PostMapping("/project")
    public RestBean<?> mergeProjects(@RequestBody List<Integer> projectIds){
        service.mergeContracts(projectIds);
        return new RestBean<Void>(HttpStatus.OK,"合并成功！");
    }

}
