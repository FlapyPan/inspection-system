package com.inspectionSystem.controller;

import com.inspectionSystem.entity.Thesis;
import com.inspectionSystem.entity.details.ThesisDetails;
import com.inspectionSystem.entity.resp.RestBean;
import com.inspectionSystem.service.ThesisService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
public class ThesisController {

    @Resource
    ThesisService service;

    @GetMapping("/thesis")
    public RestBean<?> thesisList(){
        return new RestBean<>(HttpStatus.OK,"获取成功！",service.getAllThesis());
    }


    @GetMapping("/thesis/{id}")
    public RestBean<?> getThesis(@PathVariable Integer id){
        return new RestBean<>(HttpStatus.OK,"获取成功！",service.getThesisById(id));
    }

    @GetMapping("/thesis/employee/{employeeId}")
    public RestBean<?> getThesisByEmployeeId(@PathVariable Integer employeeId){
        return new RestBean<>(HttpStatus.OK,"获取成功！",service.getThesisByEmployeeId(employeeId));
    }

    @PostMapping("/thesis/{id}")
    public RestBean<?> change(@RequestBody ThesisDetails thesisDetails, @PathVariable Integer id){
        Thesis thesis = new Thesis(thesisDetails);
        thesis.setId(id);
        service.updateThesis(thesis);
        return new RestBean<Void>(HttpStatus.OK,"修改成功！");
    }

    @PutMapping("/thesis")
    public RestBean<?> addThesis(@RequestBody ThesisDetails thesisDetails){
        service.addThesis(thesisDetails);
        return new RestBean<Void>(HttpStatus.OK,"添加成功！");
    }


    @DeleteMapping("/thesis/{id}")
    public RestBean<?> remove(@PathVariable Integer id){
        service.removeThesis(id);
        return new RestBean<Void>(HttpStatus.OK,"删除成功！");
    }
}
