package org.isj.ing3.isi.webservice.webservicerest.serviceImpl;

import org.isj.ing3.isi.webservice.webservicerest.exception.ErrorInfo;
import org.isj.ing3.isi.webservice.webservicerest.exception.IsjException;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.HistoriqueNote;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Utilisateur;
import org.isj.ing3.isi.webservice.webservicerest.repositories.HistoriqueNoteRepository;
import org.isj.ing3.isi.webservice.webservicerest.repositories.UtilisateurRepository;
import org.isj.ing3.isi.webservice.webservicerest.service.IHistoriqueNote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HistoriqueNoteImpl implements IHistoriqueNote {
    @Autowired
    HistoriqueNoteRepository historiqueNoteRepository;
    @Autowired
    UtilisateurRepository utilisateurRepository;

    @Override
    public int saveHistoriqueNote(HistoriqueNote historiqueNote) throws IsjException {

        Utilisateur createur = utilisateurRepository.findById(historiqueNote.getCreateur().getCode()).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));
        Utilisateur modificateur = utilisateurRepository.findById(historiqueNote.getCreateur().getCode()).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));
        historiqueNote.setCreateur(createur);
        historiqueNote.setModificateur(modificateur);

        return historiqueNoteRepository.save(historiqueNote).getCode().intValue();
    }

    @Override
    public List<HistoriqueNote> listHistoriqueNotes() {
        return historiqueNoteRepository.findAll();
    }

    @Override
    public int deleteHistoriqueNote(Long code) {
        historiqueNoteRepository.deleteById(historiqueNoteRepository.findById(code).get().getCode());
        return 1;
    }

    @Override
    public HistoriqueNote getHistoriqueNoteByCode(Long code) throws IsjException {
        return historiqueNoteRepository.findById(code).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));
    }


}
