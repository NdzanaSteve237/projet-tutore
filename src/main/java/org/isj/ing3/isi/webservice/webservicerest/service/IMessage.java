package org.isj.ing3.isi.webservice.webservicerest.service;

import org.isj.ing3.isi.webservice.webservicerest.exception.IsjException;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Message;

import java.util.List;

public interface IMessage {

    int saveMessage (Message messageDto) throws IsjException;
    List<Message> listMessages();
    int deleteMessage(Long code) throws IsjException;
    Message getMessageByCode(Long code) throws IsjException;
}
