package org.isj.ing3.isi.webservice.webservicerest.service;

import org.isj.ing3.isi.webservice.webservicerest.exception.IsjException;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Anonymat;

import java.util.List;

public interface IAnonymat {

    int saveAnonymat (Anonymat anonymatDto);
    List<Anonymat> listAnonymat();
    int deleteAnonymat(Long code);
    Anonymat getAnonymatByCode(Long code) throws IsjException;
}
