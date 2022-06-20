package org.isj.ing3.isi.webservice.webservicerest.serviceImpl;

import org.isj.ing3.isi.webservice.webservicerest.exception.ErrorInfo;
import org.isj.ing3.isi.webservice.webservicerest.exception.IsjException;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Filiere;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Niveau;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Utilisateur;
import org.isj.ing3.isi.webservice.webservicerest.repositories.NiveauRepository;
import org.isj.ing3.isi.webservice.webservicerest.repositories.UtilisateurRepository;
import org.isj.ing3.isi.webservice.webservicerest.service.INiveau;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NiveauImpl implements INiveau {
    @Autowired
    NiveauRepository niveauRepository;
    @Autowired
    UtilisateurRepository utilisateurRepository;

    @Override
    public int saveNiveau(Niveau niveau) throws IsjException{

        Utilisateur createur = utilisateurRepository.findById(niveau.getCreateur().getCode()).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));
        Utilisateur modificateur = utilisateurRepository.findById(niveau.getCreateur().getCode()).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));
        niveau.setCreateur(createur);
        niveau.setModificateur(modificateur);

        return niveauRepository.save(niveau).getCode().intValue();
    }

    @Override
    public List<Niveau> listNiveaus() {
        return niveauRepository.findAll();
    }

    @Override
    public int deleteNiveau(Long code) throws IsjException {
        niveauRepository.deleteById(getNiveauByCode(code).getCode());
        return 1;
    }

    @Override
    public Niveau getNiveauByCode(Long code) throws IsjException {
        return niveauRepository.findById(code).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));
    }

    @Override
    public List<Niveau> getNiveauByNumero(long niveau) throws IsjException {
        return niveauRepository.retrouverNiveau(niveau).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND)) ;   }
}
