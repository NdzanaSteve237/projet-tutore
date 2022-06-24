package org.isj.ing3.isi.webservice.webservicerest.service;

import org.isj.ing3.isi.webservice.webservicerest.exception.IsjException;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Candidat;

import java.util.List;

public interface ICandidat {

    Candidat saveCandidat (Candidat candidatDto) throws IsjException;
    List<Candidat> listCandidats();
    int deleteCandidat(Long code);
    Candidat getCandidatByCode(Long code) throws IsjException;
    void deleteByEmail(String email);
}
