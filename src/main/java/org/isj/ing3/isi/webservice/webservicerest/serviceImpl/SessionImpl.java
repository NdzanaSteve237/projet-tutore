package org.isj.ing3.isi.webservice.webservicerest.serviceImpl;

import org.isj.ing3.isi.webservice.webservicerest.exception.ErrorInfo;
import org.isj.ing3.isi.webservice.webservicerest.exception.IsjException;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Session;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Utilisateur;
import org.isj.ing3.isi.webservice.webservicerest.repositories.SessionRepository;
import org.isj.ing3.isi.webservice.webservicerest.repositories.UtilisateurRepository;
import org.isj.ing3.isi.webservice.webservicerest.service.ISession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SessionImpl implements ISession {
    @Autowired
    SessionRepository sessionRepository;
    @Autowired
    UtilisateurRepository utilisateurRepository;

    @Override
    public Long saveSession(Session session) {
        Utilisateur createur = utilisateurRepository.findById(session.getCreateur().getCode()).get();
        Utilisateur modificateur = utilisateurRepository.findById(session.getCreateur().getCode()).get();

        session.setCreateur(createur);
        session.setModificateur(modificateur);
        return sessionRepository.save(session).getCode();
    }

    @Override
    public List<Session> listSession() {
        return sessionRepository.findAll();
    }

    @Override
    public int deleteSessionByCode(Long code) throws IsjException {
        sessionRepository.deleteById(sessionRepository.findById(code).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND)).getCode());
        return 1;
    }
}
