package org.isj.ing3.isi.webservice.webservicerest.service;

import org.isj.ing3.isi.webservice.webservicerest.exception.IsjException;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Email;

import java.util.List;

public interface IEmail {

    int saveEmail (Email emailDto);
    List<Email> listEmails();
    int deleteEmail(Long code);
    Email getByEmailCode(Long code) throws IsjException;
}
