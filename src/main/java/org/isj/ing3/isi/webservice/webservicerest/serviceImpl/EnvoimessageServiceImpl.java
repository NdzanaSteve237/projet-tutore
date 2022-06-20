package org.isj.ing3.isi.webservice.webservicerest.serviceImpl;


import org.isj.ing3.isi.webservice.webservicerest.exception.ErrorInfo;
import org.isj.ing3.isi.webservice.webservicerest.exception.IsjException;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.EnvoiMessage;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Filiere;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Utilisateur;
import org.isj.ing3.isi.webservice.webservicerest.repositories.EnvoiMessageRepository;
import org.isj.ing3.isi.webservice.webservicerest.repositories.UtilisateurRepository;
import org.isj.ing3.isi.webservice.webservicerest.service.IEnvoiMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EnvoimessageServiceImpl implements IEnvoiMessage {
    @Autowired
    EnvoiMessageRepository envoiMessageRepository;
    @Autowired
    UtilisateurRepository utilisateurRepository;

    @Override
    public int saveEnvoiMessage(EnvoiMessage envoiMessage) throws IsjException {
        Utilisateur createur = utilisateurRepository.findById(envoiMessage.getCreateur().getCode()).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));
        Utilisateur modificateur = utilisateurRepository.findById(envoiMessage.getCreateur().getCode()).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));

        envoiMessage.setCreateur(createur);
        envoiMessage.setModificateur(modificateur);
        return envoiMessageRepository.save(envoiMessage).getCode().intValue();
    }
/**besoin de deleteByLibelle**/
    @Override
    public int deleteEnvoiMessage(String libelle) {
        return 0;
    }


    @Override
    public EnvoiMessage searchEnvoiMessageByCode(Long code) throws IsjException {
        return envoiMessageRepository.findById(code).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));
    }

    @Override
    public List<EnvoiMessage> listEnvoiMessage() {
        return envoiMessageRepository.findAll();
    }

    @Override
    public EnvoiMessage getEnvoiMessageByCode(Long code) throws IsjException {
        return envoiMessageRepository.findById(code).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));
    }
}
