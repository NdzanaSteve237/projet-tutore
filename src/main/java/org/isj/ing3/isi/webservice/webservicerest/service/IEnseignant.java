package org.isj.ing3.isi.webservice.webservicerest.service;

import org.isj.ing3.isi.webservice.webservicerest.exception.IsjException;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Enseignant;

import java.util.List;

public interface IEnseignant {
    int saveEnseignant(Enseignant enseignantDto) throws IsjException;
    int deleteEnseignant(Long code);
    Enseignant searchEnseignantByCode(Long code) throws IsjException;
    List<Enseignant> listEnseignants();

    Enseignant getEnseignantByCode(Long code) throws IsjException;
}
