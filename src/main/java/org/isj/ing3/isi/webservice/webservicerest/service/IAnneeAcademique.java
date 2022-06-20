package org.isj.ing3.isi.webservice.webservicerest.service;

import org.isj.ing3.isi.webservice.webservicerest.exception.IsjException;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.AnneeAcademique;

import java.util.List;

public interface IAnneeAcademique {

    AnneeAcademique saveAnneeAcademique (AnneeAcademique anneeAcademique) throws IsjException;
    List<AnneeAcademique> listAnneeAcademique();
    int deleteAnneAcademique(Long code);
    AnneeAcademique getAnneAcademiqueByCode(Long code) throws IsjException;
}
