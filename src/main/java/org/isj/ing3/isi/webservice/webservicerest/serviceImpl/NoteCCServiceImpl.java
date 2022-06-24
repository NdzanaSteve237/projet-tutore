package org.isj.ing3.isi.webservice.webservicerest.serviceImpl;

import org.isj.ing3.isi.webservice.webservicerest.exception.ErrorInfo;
import org.isj.ing3.isi.webservice.webservicerest.exception.IsjException;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Candidat;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.NoteCC;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.TypeNoteCC;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Utilisateur;
import org.isj.ing3.isi.webservice.webservicerest.repositories.MessageRepository;
import org.isj.ing3.isi.webservice.webservicerest.repositories.NoteCCRepository;
import org.isj.ing3.isi.webservice.webservicerest.repositories.UtilisateurRepository;
import org.isj.ing3.isi.webservice.webservicerest.service.INoteCC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NoteCCServiceImpl implements INoteCC {
    @Autowired
    NoteCCRepository noteCCRepository;
    @Autowired
    UtilisateurRepository utilisateurRepository;

    @Override
    public int saveNoteCC(NoteCC noteCC) throws IsjException{

        Utilisateur createur = utilisateurRepository.findById(noteCC.getCreateur().getCode()).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));
        Utilisateur modificateur = utilisateurRepository.findById(noteCC.getCreateur().getCode()).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));
        noteCC.setCreateur(createur);
        noteCC.setModificateur(modificateur);

        return noteCCRepository.save(noteCC).getCode().intValue();
    }

    @Override
    public List<NoteCC> listNoteCC() {
        List<NoteCC> noteCCDtos = noteCCRepository.findAll();
        return noteCCDtos;
    }

    @Override
    public int deleteNoteCC(Long code) throws IsjException {
        noteCCRepository.deleteById(getNoteCCByCode(code).getCode());
        return 1;
    }

    @Override
    public NoteCC getNoteCCByCode(Long code) throws IsjException {
        return noteCCRepository.findById(code).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));
    }

    @Override
    public NoteCC searchNoteCCByCandidatOrTypeNoteCC(Candidat candidat, TypeNoteCC typeNoteCC) {
        return noteCCRepository.retrouverNoteCC(candidat,typeNoteCC);
    }
}

