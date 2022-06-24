package org.isj.ing3.isi.webservice.webservicerest.service;

import org.isj.ing3.isi.webservice.webservicerest.exception.IsjException;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Candidat;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Classe;

import java.util.List;

public interface ICandidat {

    Candidat saveCandidat (Candidat candidatDto) throws IsjException;
    List<Candidat> listCandidats();
    int deleteCandidat(Long code) throws IsjException;
    Candidat getCandidatByCode(Long code) throws IsjException;
    void deleteByEmail(String email);
    Candidat searchCandidatBytelephone(int telephone) throws IsjException;
    int updateCandidat (Candidat candidat) throws IsjException;
}
