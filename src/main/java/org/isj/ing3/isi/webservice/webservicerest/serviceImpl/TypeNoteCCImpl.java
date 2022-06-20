package org.isj.ing3.isi.webservice.webservicerest.serviceImpl;

import org.isj.ing3.isi.webservice.webservicerest.exception.ErrorInfo;
import org.isj.ing3.isi.webservice.webservicerest.exception.IsjException;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.TypeNoteCC;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Utilisateur;
import org.isj.ing3.isi.webservice.webservicerest.repositories.TypeNoteCCRepository;
import org.isj.ing3.isi.webservice.webservicerest.repositories.UtilisateurRepository;
import org.isj.ing3.isi.webservice.webservicerest.service.ITypeNoteCC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service

public class TypeNoteCCImpl implements ITypeNoteCC {
    @Autowired
    TypeNoteCCRepository typeNoteCCRepository;
    @Autowired
    UtilisateurRepository utilisateurRepository;

    @Override
    public Long saveTypeNoteCC(TypeNoteCC typeNoteCC) throws IsjException {
        Utilisateur createur = utilisateurRepository.findById(typeNoteCC.getCreateur().getCode()).get();
        Utilisateur modificateur = utilisateurRepository.findById(typeNoteCC.getCreateur().getCode()).get();

        typeNoteCC.setCreateur(createur);
        typeNoteCC.setModificateur(modificateur);
        return typeNoteCCRepository.save(typeNoteCC).getCode();
    }

    @Override
    public List<TypeNoteCC> listTypeNoteCC() {
        return typeNoteCCRepository.findAll();
    }

    @Override
    public int deleteTypeNoteCC(Long code) throws IsjException {
        typeNoteCCRepository.deleteById(typeNoteCCRepository.findById(code).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND)).getCode());
        return 1;
    }
}
