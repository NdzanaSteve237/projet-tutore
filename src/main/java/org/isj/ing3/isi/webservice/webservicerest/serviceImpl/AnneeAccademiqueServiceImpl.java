package org.isj.ing3.isi.webservice.webservicerest.serviceImpl;

import org.isj.ing3.isi.webservice.webservicerest.exception.ErrorInfo;
import org.isj.ing3.isi.webservice.webservicerest.exception.IsjException;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.AnneeAcademique;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Utilisateur;
import org.isj.ing3.isi.webservice.webservicerest.repositories.AnneeAcademiqueRepository;
import org.isj.ing3.isi.webservice.webservicerest.repositories.UtilisateurRepository;
import org.isj.ing3.isi.webservice.webservicerest.service.IAnneeAcademique;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnneeAccademiqueServiceImpl implements IAnneeAcademique {
    @Autowired
    AnneeAcademiqueRepository anneeAcademiqueRepository;
    @Autowired
    UtilisateurRepository utilisateurRepository;


    @Override
    public AnneeAcademique saveAnneeAcademique(AnneeAcademique annee_academique) throws IsjException {
        Utilisateur createur = utilisateurRepository.findById(annee_academique.getCreateur().getCode()).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));;
        Utilisateur modificateur = utilisateurRepository.findById(annee_academique.getCreateur().getCode()).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));;

        annee_academique.setCreateur(createur);
        annee_academique.setModificateur(modificateur);
        return anneeAcademiqueRepository.save(annee_academique);
    }

    @Override
    public List<AnneeAcademique> listAnneeAcademique() {
        return anneeAcademiqueRepository.findAll();
    }

    @Override
    public int deleteAnneAcademique(Long code) {
        anneeAcademiqueRepository.deleteById(anneeAcademiqueRepository.findById(code).get().getCode());
        return 1;
    }

    @Override
    public AnneeAcademique getAnneAcademiqueByCode(Long code) throws IsjException {
        return anneeAcademiqueRepository.findById(code).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));
    }
}
