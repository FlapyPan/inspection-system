package com.inspectionSystem.service.impl;

import com.inspectionSystem.entity.Evaluation;
import com.inspectionSystem.entity.details.EvaluationDetails;
import com.inspectionSystem.mapper.EvaluationMapper;
import com.inspectionSystem.service.EvaluationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class EvaluationServiceImpl implements EvaluationService {

    @Resource
    EvaluationMapper evaluationMapper;

    @Override
    public Evaluation getEvaluation(Integer id) {
        return evaluationMapper.selectEvaluationById(id);
    }

    @Override
    public Evaluation getEvaluation(Integer id, Integer year, Integer month) {
        return evaluationMapper.selectEvaluation(id, year, month);
    }

    @Override
    public List<Evaluation> getEvaluationListById(Integer id) {
        return evaluationMapper.selectEvaluationListById(id);
    }

    @Override
    public Boolean saveEvaluation(EvaluationDetails evaluationDetails) {
        return evaluationMapper.insertEvaluation(evaluationDetails) == 1;
    }

    @Override
    public void changeEvaluation(Evaluation evaluation) {
        evaluationMapper.updateEvaluation(evaluation);
    }
}
