package com.inspectionSystem.controller;

import com.inspectionSystem.entity.SoftwareWork;
import com.inspectionSystem.entity.details.SoftwareWorkDetails;
import com.inspectionSystem.entity.resp.RestBean;
import com.inspectionSystem.service.SoftwareWorkService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
public class SoftwareWorkController {

    @Resource
    SoftwareWorkService service;

    @GetMapping("/softwareWorksWork")
    public RestBean<?> SoftwareWorkList() {
        return new RestBean<>(HttpStatus.OK, "获取成功！", service.getAllSoftwareWorks());
    }


    @GetMapping("/softwareWorksWork/{id}")
    public RestBean<?> getSoftwareWork(@PathVariable Integer id) {
        return new RestBean<>(HttpStatus.OK, "获取成功！", service.getSoftwareWorkById(id));
    }

    @GetMapping("/softwareWorksWork/employee/{employeeId}")
    public RestBean<?> getSoftwareWorksByEmployeeId(@PathVariable Integer employeeId) {
        return new RestBean<>(HttpStatus.OK, "获取成功！", service.getAllSoftwareWorksByEmployeeId(employeeId));
    }

    @PostMapping("/softwareWorksWork/{id}")
    public RestBean<?> change(@RequestBody SoftwareWorkDetails softwareWorkDetails, @PathVariable Integer id) {
        SoftwareWork SoftwareWork = new SoftwareWork(softwareWorkDetails);
        SoftwareWork.setId(id);
        service.updateSoftwareWork(SoftwareWork);
        return new RestBean<>(HttpStatus.OK, "修改成功！");
    }

    @PutMapping("/softwareWorksWork")
    public RestBean<?> addSoftwareWork(@RequestBody SoftwareWorkDetails softwareWorkDetails) {
        service.addSoftwareWork(softwareWorkDetails);
        return new RestBean<>(HttpStatus.OK, "添加成功！");
    }

    @DeleteMapping("/softwareWorksWork/{id}")
    public RestBean<?> remove(@PathVariable Integer id) {
        service.removeSoftwareWork(id);
        return new RestBean<Void>(HttpStatus.OK, "删除成功！");
    }
}
