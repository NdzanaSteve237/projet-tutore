package org.isj.ing3.isi.webservice.webservicerest.serviceImpl;

import org.isj.ing3.isi.webservice.webservicerest.exception.ErrorInfo;
import org.isj.ing3.isi.webservice.webservicerest.exception.IsjException;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.AnneeAcademique;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Note;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.NoteCC;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Utilisateur;
import org.isj.ing3.isi.webservice.webservicerest.repositories.*;
import org.isj.ing3.isi.webservice.webservicerest.service.IAnneeAcademique;
import org.isj.ing3.isi.webservice.webservicerest.service.INoteCC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnneeAccademiqueServiceImpl implements IAnneeAcademique {
    @Autowired
    AnneeAcademiqueRepository anneeAcademiqueRepository;
    @Autowired
    UtilisateurRepository utilisateurRepository;


    @Override
    public AnneeAcademique saveAnneeAcademique(AnneeAcademique anneeAcademique) throws IsjException {
        Utilisateur createur = utilisateurRepository.findById(anneeAcademique.getCreateur().getCode()).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));;
        Utilisateur modificateur = utilisateurRepository.findById(anneeAcademique.getCreateur().getCode()).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));;

        anneeAcademique.setCreateur(createur);
        anneeAcademique.setModificateur(modificateur);
        return anneeAcademiqueRepository.save(anneeAcademique);
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
