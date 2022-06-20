package org.isj.ing3.isi.webservice.webservicerest.service;



import org.isj.ing3.isi.webservice.webservicerest.exception.IsjException;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Evaluation;

import java.util.List;

public interface IEvaluation {
    int saveEvaluation(Evaluation evaluationDto) throws IsjException;
    int deleteEvaluation(Long code) throws IsjException;;
    Evaluation searchEvaluationByCode(Long code) throws IsjException;
    List<Evaluation> listEvaluation();

    Evaluation getEvaluationByCode(Long code) throws IsjException;
}
