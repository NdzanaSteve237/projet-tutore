package org.isj.ing3.isi.webservice.webservicerest.service;

import org.isj.ing3.isi.webservice.webservicerest.exception.IsjException;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.TypeNoteCC;

import java.util.List;

public interface ITypeNoteCC {
    Long saveTypeNoteCC(TypeNoteCC typeNoteCC) throws IsjException;
    List<TypeNoteCC> listTypeNoteCC();
    int deleteTypeNoteCC(Long code) throws IsjException;
}
