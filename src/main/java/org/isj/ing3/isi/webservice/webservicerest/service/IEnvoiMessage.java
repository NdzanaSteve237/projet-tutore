package org.isj.ing3.isi.webservice.webservicerest.service;


import org.isj.ing3.isi.webservice.webservicerest.exception.IsjException;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.EnvoiMessage;

import java.util.List;

public interface IEnvoiMessage {
    int saveEnvoiMessage(EnvoiMessage envoiMessageDto) throws IsjException;
    int deleteEnvoiMessage(String libelle);
    EnvoiMessage searchEnvoiMessageByCode(Long code) throws IsjException;
    List<EnvoiMessage> listEnvoiMessage();

    EnvoiMessage getEnvoiMessageByCode(Long code) throws IsjException;
}
