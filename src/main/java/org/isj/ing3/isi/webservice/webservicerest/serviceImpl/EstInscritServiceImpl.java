package org.isj.ing3.isi.webservice.webservicerest.serviceImpl;

import org.isj.ing3.isi.webservice.webservicerest.exception.ErrorInfo;
import org.isj.ing3.isi.webservice.webservicerest.exception.IsjException;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.EstInscrit;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Utilisateur;
import org.isj.ing3.isi.webservice.webservicerest.repositories.EstInscritRepository;
import org.isj.ing3.isi.webservice.webservicerest.repositories.UtilisateurRepository;
import org.isj.ing3.isi.webservice.webservicerest.service.IEstInscrit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstInscritServiceImpl implements IEstInscrit {
    @Autowired
    EstInscritRepository estInscritRepository;
    @Autowired
    UtilisateurRepository utilisateurRepository;

    @Override
    public int saveInscrit(EstInscrit estInscrit) throws IsjException {
        Utilisateur createur = utilisateurRepository.findById(estInscrit.getCreateur().getCode()).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));
        Utilisateur modificateur = utilisateurRepository.findById(estInscrit.getCreateur().getCode()).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));

        estInscrit.setCreateur(createur);
        estInscrit.setModificateur(modificateur);
        return estInscritRepository.save(estInscrit).getCode().intValue();
    }

    //need delete by libelle
    @Override
    public int deleteInscrit(String libelle) {
        return 0;
    }

    @Override
    public EstInscrit searchInscritByCode(Long code) throws IsjException {
        return estInscritRepository.findById(code).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));
    }

    @Override
    public List<EstInscrit> listInscrit() {
        return estInscritRepository.findAll();
    }

    @Override
    public EstInscrit getInscritByCode(Long code) throws IsjException {
        return estInscritRepository.findById(code).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));
    }

}
