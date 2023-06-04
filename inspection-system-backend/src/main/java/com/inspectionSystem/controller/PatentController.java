package com.inspectionSystem.controller;

import com.inspectionSystem.entity.Patent;
import com.inspectionSystem.entity.details.PatentDetails;
import com.inspectionSystem.entity.resp.RestBean;
import com.inspectionSystem.service.PatentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
public class PatentController {
    
    @Resource
    PatentService service;

    @GetMapping("/patent")
    public RestBean<?> PatentList(){
        return new RestBean<>(HttpStatus.OK,"获取成功！",service.getAllPatents());
    }


    @GetMapping("/patent/{id}")
    public RestBean<?> getPatent(@PathVariable Integer id){
        return new RestBean<>(HttpStatus.OK,"获取成功！",service.getPatentById(id));
    }

    @GetMapping("/patent/employee/{employeeId}")
    public RestBean<?> getPatentByEmployeeId(@PathVariable Integer employeeId){
        return new RestBean<>(HttpStatus.OK,"获取成功！",service.getPatentsByEmployeeId(employeeId));
    }

    @PostMapping("/patent/{id}")
    public RestBean<?> change(@RequestBody PatentDetails patentDetails, @PathVariable Integer id){
        Patent patent = new Patent(patentDetails);
        patent.setId(id);
        service.updatePatent(patent);
        return new RestBean<Void>(HttpStatus.OK,"修改成功！");
    }

    @PutMapping("/patent")
    public RestBean<?> addPatent(@RequestBody PatentDetails patentDetails){
        service.addPatent(patentDetails);
        return new RestBean<Void>(HttpStatus.OK,"添加成功！");
    }



    @DeleteMapping("/patent/{id}")
    public RestBean<?> remove(@PathVariable Integer id){
        service.removePatent(id);
        return new RestBean<Void>(HttpStatus.OK,"删除成功！");
    }
    
}
