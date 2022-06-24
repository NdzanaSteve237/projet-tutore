package org.isj.ing3.isi.webservice.webservicerest.serviceImpl;

import org.isj.ing3.isi.webservice.webservicerest.exception.ErrorInfo;
import org.isj.ing3.isi.webservice.webservicerest.exception.IsjException;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Discipline;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Etudiant;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Semestre;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Utilisateur;
import org.isj.ing3.isi.webservice.webservicerest.repositories.DisciplineRepository;
import org.isj.ing3.isi.webservice.webservicerest.repositories.EtudiantRepository;
import org.isj.ing3.isi.webservice.webservicerest.repositories.SemestreRepository;
import org.isj.ing3.isi.webservice.webservicerest.repositories.UtilisateurRepository;
import org.isj.ing3.isi.webservice.webservicerest.service.IDiscipline;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DisciplineServiceImpl implements IDiscipline {
    @Autowired
    DisciplineRepository disciplineRepository;
    @Autowired
    UtilisateurRepository utilisateurRepository;
    @Autowired
    EtudiantRepository etudiantRepository;
    @Autowired
    SemestreRepository semestreRepository;

    @Override
    public int saveDiscipline(Discipline discipline) throws IsjException {
        Utilisateur createur = utilisateurRepository.findById(discipline.getCreateur().getCode()).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));
        Utilisateur modificateur = utilisateurRepository.findById(discipline.getCreateur().getCode()).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));
        Etudiant etudiant = etudiantRepository.findById(discipline.getEtudiant().getCode()).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));
        Semestre semestre = semestreRepository.findById(discipline.getSemestre().getCode()).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));
        discipline.setEtudiant(etudiant);
        discipline.setSemestre(semestre);
        discipline.setCreateur(createur);
        discipline.setModificateur(modificateur);

        return disciplineRepository.save(discipline).getCode().intValue();
    }

    @Override
    public List<Discipline> listDisciplines() {
        return disciplineRepository.findAll();
    }

    @Override
    public int deleteDiscipline(Long code) {
        disciplineRepository.deleteById(disciplineRepository.findById(code).get().getCode());
        return 1;
    }

    @Override
    public Discipline getDisciplineByCode(Long code) throws IsjException {
        return disciplineRepository.findById(code).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));
    }
}
