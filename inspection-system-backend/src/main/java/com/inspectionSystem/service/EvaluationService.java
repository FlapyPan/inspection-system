package com.inspectionSystem.service;

import com.inspectionSystem.entity.Evaluation;
import com.inspectionSystem.entity.details.EvaluationDetails;

import java.util.List;

public interface EvaluationService {

    Evaluation getEvaluation(Integer id);

    Evaluation getEvaluation(Integer id, Integer year, Integer month);

    List<Evaluation> getEvaluationListById(Integer id);

    Boolean saveEvaluation(EvaluationDetails evaluationDetails);

    void changeEvaluation(Evaluation evaluation);
}
