package org.isj.ing3.isi.webservice.webservicerest.serviceImpl;

import org.isj.ing3.isi.webservice.webservicerest.exception.ErrorInfo;
import org.isj.ing3.isi.webservice.webservicerest.exception.IsjException;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Specialite;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Utilisateur;
import org.isj.ing3.isi.webservice.webservicerest.repositories.SpecialiteRepository;
import org.isj.ing3.isi.webservice.webservicerest.repositories.UtilisateurRepository;
import org.isj.ing3.isi.webservice.webservicerest.service.ISpecialite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SpecialiteImpl implements ISpecialite {
    @Autowired
    SpecialiteRepository specialiteRepository;
    @Autowired
    UtilisateurRepository utilisateurRepository;

    @Override
    public Long saveSpecialite(Specialite specialiteDto) {
        Utilisateur createur = utilisateurRepository.findById(specialiteDto.getCreateur().getCode()).get();
        Utilisateur modificateur = utilisateurRepository.findById(specialiteDto.getCreateur().getCode()).get();

        specialiteDto.setCreateur(createur);
        specialiteDto.setModificateur(modificateur);
        return specialiteRepository.save(specialiteDto).getCode();
    }

    @Override
    public List<Specialite> listSpecialite() {

        return specialiteRepository.findAll();
    }

    @Override
    public int deleteSpecialiteByCode(Long code) throws IsjException {
        specialiteRepository.deleteById(specialiteRepository.findById(code).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND)).getCode());
        return 1;
    }

    @Override
    public Specialite searchSpecialiteBySpecialiteOrfiliere (String specialite, String filiere) {
        return specialiteRepository.retrouverSpecialite(specialite,filiere );
    }
}
