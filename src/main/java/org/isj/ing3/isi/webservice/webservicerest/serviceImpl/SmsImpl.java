package org.isj.ing3.isi.webservice.webservicerest.serviceImpl;

import org.isj.ing3.isi.webservice.webservicerest.exception.ErrorInfo;
import org.isj.ing3.isi.webservice.webservicerest.exception.IsjException;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Sms;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Utilisateur;
import org.isj.ing3.isi.webservice.webservicerest.repositories.SmsRepository;
import org.isj.ing3.isi.webservice.webservicerest.repositories.UtilisateurRepository;
import org.isj.ing3.isi.webservice.webservicerest.service.ISms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SmsImpl implements ISms {
    @Autowired
    SmsRepository smsRepository;
    @Autowired
    UtilisateurRepository utilisateurRepository;

    @Override
    public Long saveSms(Sms sms) {
        Utilisateur createur = utilisateurRepository.findById(sms.getCreateur().getCode()).get();
        Utilisateur modificateur = utilisateurRepository.findById(sms.getCreateur().getCode()).get();

        sms.setCreateur(createur);
        sms.setModificateur(modificateur);
        return  smsRepository.save(sms).getCode();
    }

    @Override
    public List<Sms> listSms() {
        return smsRepository.findAll();
    }

    @Override
    public int deleteSmsByCode(Long code) throws IsjException {
        smsRepository.deleteById(smsRepository.findById(code).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND)).getCode());
        return 1;
    }
}
