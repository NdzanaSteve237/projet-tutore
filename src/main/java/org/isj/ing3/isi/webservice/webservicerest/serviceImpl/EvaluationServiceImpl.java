package org.isj.ing3.isi.webservice.webservicerest.serviceImpl;

import org.isj.ing3.isi.webservice.webservicerest.exception.ErrorInfo;
import org.isj.ing3.isi.webservice.webservicerest.exception.IsjException;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Evaluation;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Filiere;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Utilisateur;
import org.isj.ing3.isi.webservice.webservicerest.repositories.EvaluationRepository;
import org.isj.ing3.isi.webservice.webservicerest.repositories.UtilisateurRepository;
import org.isj.ing3.isi.webservice.webservicerest.service.IEvaluation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EvaluationServiceImpl implements IEvaluation {
    @Autowired
    EvaluationRepository evaluationRepository;
    @Autowired
    UtilisateurRepository utilisateurRepository;
    @Override
    public int saveEvaluation(Evaluation evaluation) throws IsjException {
        Utilisateur createur = utilisateurRepository.findById(evaluation.getCreateur().getCode()).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));
        Utilisateur modificateur = utilisateurRepository.findById(evaluation.getCreateur().getCode()).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));
        evaluation.setCreateur(createur);
        evaluation.setModificateur(modificateur);
        return evaluationRepository.save(evaluation).getCode().intValue();

    }
//besoin de deleteById
    @Override
    public int deleteEvaluation(Long code) throws IsjException {
        evaluationRepository.deleteById(searchEvaluationByCode(code).getCode());
        return 1;
    }

    @Override
    public Evaluation searchEvaluationByCode(Long code) throws IsjException {
        return evaluationRepository.findById(code).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));
    }

    @Override
    public List<Evaluation> listEvaluation() {
        return evaluationRepository.findAll();
    }

    @Override
    public Evaluation getEvaluationByCode(Long code) throws IsjException {
        return evaluationRepository.findById(code).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));
    }
}
