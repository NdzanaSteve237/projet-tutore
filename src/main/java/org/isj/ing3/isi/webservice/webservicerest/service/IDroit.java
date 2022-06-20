package org.isj.ing3.isi.webservice.webservicerest.service;

import org.isj.ing3.isi.webservice.webservicerest.exception.IsjException;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Droit;

import java.util.List;

public interface IDroit {

    int saveDroit (Droit droitDto) throws IsjException;
    List<Droit> listDroits();
    int deleteDroit(Long code);
    Droit getDroitByCode(Long code) throws IsjException;
}
