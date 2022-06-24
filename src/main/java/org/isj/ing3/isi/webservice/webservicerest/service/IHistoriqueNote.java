package org.isj.ing3.isi.webservice.webservicerest.service;

import org.isj.ing3.isi.webservice.webservicerest.exception.IsjException;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.HistoriqueNote;

import java.util.List;

public interface IHistoriqueNote {

    int saveHistoriqueNote (HistoriqueNote historiqueNoteDto) throws IsjException;
    List<HistoriqueNote> listHistoriqueNotes();
    int deleteHistoriqueNote(Long code);
    HistoriqueNote getHistoriqueNoteByCode(Long code) throws IsjException;
}
