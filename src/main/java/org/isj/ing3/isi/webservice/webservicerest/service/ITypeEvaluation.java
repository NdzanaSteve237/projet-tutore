package org.isj.ing3.isi.webservice.webservicerest.service;

import org.isj.ing3.isi.webservice.webservicerest.exception.IsjException;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.TypeEvaluation;

import java.util.List;

public interface ITypeEvaluation {

   TypeEvaluation saveTypeEvaluation(TypeEvaluation typeEvaluation ) throws IsjException;
    List<TypeEvaluation> listTypeEvaluation();
    int deleteTypeEvaluation(Long code);
    TypeEvaluation getTypeEvaluationByCode(Long code) throws IsjException;
}

