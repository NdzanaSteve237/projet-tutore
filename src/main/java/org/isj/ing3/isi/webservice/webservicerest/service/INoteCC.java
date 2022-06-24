package org.isj.ing3.isi.webservice.webservicerest.service;

import org.isj.ing3.isi.webservice.webservicerest.exception.IsjException;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Candidat;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.NoteCC;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.TypeNoteCC;

import java.util.List;

public interface INoteCC {
    int saveNoteCC(NoteCC noteCC) throws IsjException;
    List<NoteCC>listNoteCC();
    int deleteNoteCC(Long code) throws IsjException;
    NoteCC getNoteCCByCode(Long code) throws IsjException;
    NoteCC searchNoteCCByCandidatOrTypeNoteCC(Candidat candidat, TypeNoteCC typeNoteCC) throws IsjException;
    int updateNoteCC(NoteCC noteCC) throws IsjException;

}
