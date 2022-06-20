package org.isj.ing3.isi.webservice.webservicerest.service;

import org.isj.ing3.isi.webservice.webservicerest.exception.IsjException;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Discipline;

import java.util.List;

public interface IDiscipline {

    int saveDiscipline (Discipline disciplineDto) throws IsjException;
    List<Discipline> listDisciplines();
    int deleteDiscipline(Long code);
    Discipline getDisciplineByCode(Long code) throws IsjException;
}
