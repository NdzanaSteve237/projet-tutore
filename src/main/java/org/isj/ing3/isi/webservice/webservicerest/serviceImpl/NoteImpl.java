package org.isj.ing3.isi.webservice.webservicerest.serviceImpl;

import org.isj.ing3.isi.webservice.webservicerest.exception.ErrorInfo;
import org.isj.ing3.isi.webservice.webservicerest.exception.IsjException;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.EstInscrit;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Evaluation;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Note;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Utilisateur;
import org.isj.ing3.isi.webservice.webservicerest.repositories.EstInscritRepository;
import org.isj.ing3.isi.webservice.webservicerest.repositories.NoteRepository;
import org.isj.ing3.isi.webservice.webservicerest.repositories.UtilisateurRepository;
import org.isj.ing3.isi.webservice.webservicerest.service.INote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NoteImpl implements INote {
    @Autowired
    NoteRepository noteRepository;
    @Autowired
    UtilisateurRepository utilisateurRepository;

    @Override
    public int saveNote(Note note) throws IsjException {

        Utilisateur createur = utilisateurRepository.findById(note.getCreateur().getCode()).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));
        Utilisateur modificateur = utilisateurRepository.findById(note.getCreateur().getCode()).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));
        note.setCreateur(createur);
        note.setModificateur(modificateur);

        return noteRepository.save(note).getCode().intValue();
    }

    @Override
    public List<Note> listNotes() {
        return noteRepository.findAll();
    }

    @Override
    public List<Note> listNotesWithLimit() {
        return noteRepository.listeNotes();
    }

    @Override
    public int deleteNote(Long code) {
        noteRepository.deleteById(noteRepository.findById(code).get().getCode());
        return 1;
    }

    @Override
    public Note getNoteByCode(Long code) throws IsjException {
        return noteRepository.findById(code).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));
    }

    @Override
    public List<Note> getNoteByCodeInscritCodeEvaluation(Long inscrit, Long eval) throws IsjException {
        return noteRepository.retrouverNote(inscrit, eval).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND)) ;   }
}
