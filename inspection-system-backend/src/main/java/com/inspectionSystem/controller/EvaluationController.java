package com.inspectionSystem.controller;

import com.inspectionSystem.entity.Evaluation;
import com.inspectionSystem.entity.details.EvaluationDetails;
import com.inspectionSystem.entity.resp.RestBean;
import com.inspectionSystem.service.EvaluationService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/evaluation")
public class EvaluationController {

    @Resource
    EvaluationService service;

    @GetMapping("/{userId}")//获取当月自评
    public RestBean<?> getEvaluation(@PathVariable Integer userId){
        return new RestBean<>(HttpStatus.OK,"获取成功!",service.getEvaluation(userId));
    }


    @GetMapping("/{userId}/{year}/{month}")//获取指定月份自评
    public RestBean<?> getEvaluation(@PathVariable Integer userId, @PathVariable Integer year, @PathVariable Integer month){
        return new RestBean<>(HttpStatus.OK,"获取成功!",service.getEvaluation(userId, year, month));
    }


    @GetMapping("/{userId}/list")
    public RestBean<?> getEvaluationList(@PathVariable Integer userId){
        return new RestBean<>(HttpStatus.OK,"获取成功!",service.getEvaluationListById(userId));
    }

    @PostMapping("/{userId}")
    public RestBean<?> saveEvaluation(@PathVariable Integer userId, @RequestBody EvaluationDetails evaluationDetails) {
        Evaluation evaluation = service.getEvaluation(userId);
        if (evaluation != null){
            Evaluation evaluation1 = new Evaluation(evaluationDetails);
            evaluation1.setId(evaluation.getId());
            service.changeEvaluation(evaluation1);
            return new RestBean<>(HttpStatus.OK, "修改成功！");
        }
        service.saveEvaluation(evaluationDetails);
        return new RestBean<>(HttpStatus.OK, "提交成功！");
    }

}
