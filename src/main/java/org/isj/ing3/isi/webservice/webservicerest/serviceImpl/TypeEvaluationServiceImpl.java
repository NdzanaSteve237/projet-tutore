package org.isj.ing3.isi.webservice.webservicerest.serviceImpl;

import org.isj.ing3.isi.webservice.webservicerest.exception.ErrorInfo;
import org.isj.ing3.isi.webservice.webservicerest.exception.IsjException;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.TypeEvaluation;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Utilisateur;
import org.isj.ing3.isi.webservice.webservicerest.repositories.TypeEvaluationRepository;
import org.isj.ing3.isi.webservice.webservicerest.repositories.UtilisateurRepository;
import org.isj.ing3.isi.webservice.webservicerest.service.ITypeEvaluation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeEvaluationServiceImpl implements ITypeEvaluation {
    @Autowired
    TypeEvaluationRepository typeEvaluationRepository;
    @Autowired
    UtilisateurRepository utilisateurRepository;


    @Override
    public TypeEvaluation saveTypeEvaluation(TypeEvaluation typeEvaluation) throws IsjException {
        Utilisateur createur = utilisateurRepository.findById(typeEvaluation.getCreateur().getCode()).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));;
        Utilisateur modificateur = utilisateurRepository.findById(typeEvaluation.getCreateur().getCode()).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));;

       typeEvaluation.setCreateur(createur);
        typeEvaluation.setModificateur(modificateur);
        return typeEvaluationRepository.save(typeEvaluation);
    }

    @Override
    public List<TypeEvaluation> listTypeEvaluation() {
        return typeEvaluationRepository.findAll();
    }

    @Override
    public int deleteTypeEvaluation(Long code) {
       typeEvaluationRepository.deleteById(typeEvaluationRepository.findById(code).get().getCode());
        return 1;
    }

    @Override
    public TypeEvaluation getTypeEvaluationByCode(Long code) throws IsjException {
        return typeEvaluationRepository.findById(code).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));
    }
}
