package org.isj.ing3.isi.webservice.webservicerest.serviceImpl;

import org.isj.ing3.isi.webservice.webservicerest.exception.ErrorInfo;
import org.isj.ing3.isi.webservice.webservicerest.exception.IsjException;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.*;
import org.isj.ing3.isi.webservice.webservicerest.repositories.*;
import org.isj.ing3.isi.webservice.webservicerest.service.IAnonymat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnonymatServiceImpl implements IAnonymat {
    @Autowired
    AnonymatRepository anonymatRepository;
    @Autowired
    UtilisateurRepository utilisateurRepository;
    @Autowired
    NoteRepository noteRepository;
    @Autowired
    EstInscritRepository estInscritRepository;
    @Autowired
    EvaluationRepository evaluationRepository;

    @Override
    public int saveAnonymat(Anonymat anonymat) {
        Utilisateur createur = utilisateurRepository.findById(anonymat.getCreateur().getCode()).get();
        Utilisateur modificateur = utilisateurRepository.findById(anonymat.getCreateur().getCode()).get();
        Note note = noteRepository.findById(anonymat.getNote().getCode()).get();
        EstInscrit estInscrit = estInscritRepository.findById(anonymat.getEstInscrit().getCode()).get();
        Evaluation evaluation = evaluationRepository.findById(anonymat.getEvaluation().getCode()).get();

        anonymat.setCreateur(createur);
        anonymat.setModificateur(modificateur);
        anonymat.setEstInscrit(estInscrit);
        anonymat.setEvaluation(evaluation);
        anonymat.setNote(note);
        return anonymatRepository.save(anonymat).getCode().intValue();
    }

    @Override
    public List<Anonymat> listAnonymat() {
        return anonymatRepository.findAll();
    }

    @Override
    public int deleteAnonymat(Long code) {
        anonymatRepository.deleteById(anonymatRepository.findById(code).get().getCode());
        return 1;
    }

    @Override
    public Anonymat getAnonymatByCode(Long code) throws IsjException {
        return anonymatRepository.findById(code).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));
    }
}
