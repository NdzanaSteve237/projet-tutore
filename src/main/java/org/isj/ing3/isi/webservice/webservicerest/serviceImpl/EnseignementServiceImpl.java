package org.isj.ing3.isi.webservice.webservicerest.serviceImpl;

import org.isj.ing3.isi.webservice.webservicerest.exception.ErrorInfo;
import org.isj.ing3.isi.webservice.webservicerest.exception.IsjException;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Enseignement;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Filiere;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Utilisateur;
import org.isj.ing3.isi.webservice.webservicerest.repositories.EnseignementRepository;
import org.isj.ing3.isi.webservice.webservicerest.repositories.UtilisateurRepository;
import org.isj.ing3.isi.webservice.webservicerest.service.IEnseignement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EnseignementServiceImpl implements IEnseignement {
    @Autowired
    EnseignementRepository enseignementRepository;
    @Autowired
    UtilisateurRepository utilisateurRepository;

    @Override
    public int saveEnseignement(Enseignement enseignement) {
        Utilisateur createur = utilisateurRepository.findById(enseignement.getCreateur().getCode()).get();
        Utilisateur modificateur = utilisateurRepository.findById(enseignement.getCreateur().getCode()).get();

        enseignement.setCreateur(createur);
        enseignement.setModificateur(modificateur);
        return enseignementRepository.save(enseignement).getCode().intValue();
    }

    @Override
    /*besoin de deleteByLibelle*/
    public void deleteEnseignement(Long code) throws IsjException {
        enseignementRepository.deleteById(getEnseignementByCode(code).getCode());
    }

    @Override
    /*besoin d'un findByLibelle ici*/
    public Enseignement searchEnseignementByKeyword(String libelle) {
        return null;
    }

    @Override
    public List<Enseignement> listEnseignements() {
        return enseignementRepository.findAll();
    }
/*besoin de findByCode*/
    @Override
    public List<Enseignement> searchEnseignementByCode(String CodeUE) {
        return enseignementRepository.findAll();
                /*enseignementRepository.findById(CodeUE).get().stream()
                .map(enseignementMapper:: toDto)
                .collect(Collectors.toList());*/
    }

    @Override
    public Enseignement getEnseignementByCode(Long code) throws IsjException {
        return enseignementRepository.findById(code).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));
    }
}
