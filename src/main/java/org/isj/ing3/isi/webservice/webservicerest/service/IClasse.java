package org.isj.ing3.isi.webservice.webservicerest.service;

import org.isj.ing3.isi.webservice.webservicerest.exception.IsjException;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Classe;

import java.util.List;

public interface IClasse {

    int saveClasse (Classe classe) throws IsjException;
    List<Classe> listClasses();
    int deleteClass(Long code) throws IsjException;
    Classe getClasseByCode(Long code) throws IsjException;
    Classe searchClasseBylibClasse(String libClasse) throws IsjException;
    int updateClasse (Classe classe) throws IsjException;
}
