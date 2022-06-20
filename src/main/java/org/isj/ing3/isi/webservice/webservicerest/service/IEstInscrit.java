package org.isj.ing3.isi.webservice.webservicerest.service;


import org.isj.ing3.isi.webservice.webservicerest.exception.IsjException;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.EstInscrit;

import java.util.List;


public interface IEstInscrit {
    int saveInscrit(EstInscrit estInscritDto) throws IsjException;
    int deleteInscrit(String libelle);
    EstInscrit searchInscritByCode(Long code) throws IsjException;
    List<EstInscrit> listInscrit();


    EstInscrit getInscritByCode(Long code) throws IsjException;
}
