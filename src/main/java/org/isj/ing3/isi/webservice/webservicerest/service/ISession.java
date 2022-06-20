package org.isj.ing3.isi.webservice.webservicerest.service;

import org.isj.ing3.isi.webservice.webservicerest.exception.IsjException;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Session;

import java.util.List;

public interface ISession {
    Long saveSession(Session sessionDto);
    List<Session> listSession();
    int deleteSessionByCode(Long code) throws IsjException;
}
