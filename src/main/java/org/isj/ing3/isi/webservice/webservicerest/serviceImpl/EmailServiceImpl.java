package org.isj.ing3.isi.webservice.webservicerest.serviceImpl;

import org.isj.ing3.isi.webservice.webservicerest.exception.ErrorInfo;
import org.isj.ing3.isi.webservice.webservicerest.exception.IsjException;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Email;
import org.isj.ing3.isi.webservice.webservicerest.repositories.EmailRepository;
import org.isj.ing3.isi.webservice.webservicerest.service.IEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailServiceImpl implements IEmail {
    @Autowired
    EmailRepository emailRepository;

    @Override
    public int saveEmail(Email emailDto) {
        return emailRepository.save(emailDto).getCode().intValue();
    }

    @Override
    public List<Email> listEmails() {
        return emailRepository.findAll();
    }

    @Override
    public int deleteEmail(Long code) {
        emailRepository.deleteById(emailRepository.findById(code).get().getCode());
        return 1;
    }

    @Override
    public Email getByEmailCode(Long code) throws IsjException {
        return emailRepository.findById(code).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));
    }
}
