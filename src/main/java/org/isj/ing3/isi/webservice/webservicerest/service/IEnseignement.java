package org.isj.ing3.isi.webservice.webservicerest.service;



import org.isj.ing3.isi.webservice.webservicerest.exception.IsjException;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Enseignement;

import java.util.List;

public interface IEnseignement {
    int saveEnseignement(Enseignement enseignement);
    void deleteEnseignement(Long code) throws IsjException;
    Enseignement searchEnseignementByKeyword(String libelle);
    List<Enseignement> listEnseignements();
    List<Enseignement> searchEnseignementByCode(String CodeUE);

    Enseignement getEnseignementByCode(Long code) throws IsjException;
    ///j'ai pas fait la recherche par année de debut
}
