package org.isj.ing3.isi.webservice.webservicerest.serviceImpl;

import org.isj.ing3.isi.webservice.webservicerest.exception.ErrorInfo;
import org.isj.ing3.isi.webservice.webservicerest.exception.IsjException;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.AnneeAcademique;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Semestre;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Utilisateur;
import org.isj.ing3.isi.webservice.webservicerest.repositories.SemestreRepository;
import org.isj.ing3.isi.webservice.webservicerest.repositories.UtilisateurRepository;
import org.isj.ing3.isi.webservice.webservicerest.service.ISemestre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class SemestreImpl implements ISemestre {

    @Autowired
    SemestreRepository semestreRepository;
    @Autowired
    UtilisateurRepository utilisateurRepository;

    @Override
    public Long saveSemestre(Semestre semestre) throws IsjException {
        Utilisateur createur = utilisateurRepository.findById(semestre.getCreateur().getCode()).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));
        Utilisateur modificateur = createur;
        semestre.setCreateur(createur);
        semestre.setModificateur(modificateur);
        return semestreRepository.save(semestre).getCode();
    }

    @Override
    public List<Semestre> listSemestre() {
        return semestreRepository.findAll();
    }

    @Override
    public int deleteSemestreByCode(Long code) throws IsjException {
        semestreRepository.deleteById(semestreRepository.findById(code).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND)).getCode());
        return 1;
    }

    @Override
    public Semestre searchSemestreByLibelleOrAnneeAcademique(String libelle, AnneeAcademique annee_academique) {
        return semestreRepository.retrouverSemestre(libelle,annee_academique);
    }

    @Override
    public Long updateSemestre(Semestre semestre) throws IsjException {
        Utilisateur createur = utilisateurRepository.findById(semestre.getCreateur().getCode()).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));
        Utilisateur modificateur = utilisateurRepository.findById(semestre.getCreateur().getCode()).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));

        semestre.setCreateur(createur);
        semestre.setModificateur(modificateur);
        return semestreRepository.save(semestre).getCode();
    }
}
