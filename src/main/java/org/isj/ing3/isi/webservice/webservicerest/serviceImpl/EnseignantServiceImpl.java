package org.isj.ing3.isi.webservice.webservicerest.serviceImpl;

import org.isj.ing3.isi.webservice.webservicerest.exception.ErrorInfo;
import org.isj.ing3.isi.webservice.webservicerest.exception.IsjException;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Enseignant;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Filiere;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Utilisateur;
import org.isj.ing3.isi.webservice.webservicerest.repositories.EnseignantRepository;
import org.isj.ing3.isi.webservice.webservicerest.repositories.UtilisateurRepository;
import org.isj.ing3.isi.webservice.webservicerest.service.IEnseignant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class EnseignantServiceImpl implements IEnseignant {
    @Autowired
    EnseignantRepository enseignantRepository;
    @Autowired
    UtilisateurRepository utilisateurRepository;

    @Override
    public int saveEnseignant(Enseignant enseignant) throws IsjException {
        Utilisateur createur = utilisateurRepository.findById(enseignant.getCreateur().getCode()).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));
        Utilisateur modificateur = utilisateurRepository.findById(enseignant.getCreateur().getCode()).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));
        enseignant.setCreateur(createur);
       enseignant.setModificateur(modificateur);
        return enseignantRepository.save(enseignant).getCode().intValue();
    }

    @Override
    public int deleteEnseignant(Long code) {
        enseignantRepository.deleteById(enseignantRepository.findById(code).get().getCode());
        return 1;
    }

    @Override
    public Enseignant searchEnseignantByCode(Long code) throws IsjException {
        return enseignantRepository.findById(code).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));
    }

    @Override
    public List<Enseignant> listEnseignants() {
        return enseignantRepository.findAll();
    }

    @Override
    public Enseignant getEnseignantByCode(Long code) throws IsjException {
        return enseignantRepository.findById(code).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));
    }


}
