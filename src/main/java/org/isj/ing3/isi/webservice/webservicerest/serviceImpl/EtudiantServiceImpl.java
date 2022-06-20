package org.isj.ing3.isi.webservice.webservicerest.serviceImpl;

import org.isj.ing3.isi.webservice.webservicerest.exception.ErrorInfo;
import org.isj.ing3.isi.webservice.webservicerest.exception.IsjException;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Etudiant;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Filiere;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Utilisateur;
import org.isj.ing3.isi.webservice.webservicerest.repositories.EtudiantRepository;
import org.isj.ing3.isi.webservice.webservicerest.repositories.UtilisateurRepository;
import org.isj.ing3.isi.webservice.webservicerest.service.IEtudiant;
import org.isj.ing3.isi.webservice.webservicerest.service.IPrintService;
import org.isj.ing3.isi.webservice.webservicerest.utils.etats.GeneratePDF;
import org.isj.ing3.isi.webservice.webservicerest.utils.printpdfclass.CarteEtudiant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EtudiantServiceImpl implements IEtudiant {
    @Autowired
    EtudiantRepository etudiantRepository;
    @Autowired
    UtilisateurRepository utilisateurRepository;

    @Override
    public int saveEtudiant(Etudiant etudiant) throws IsjException {
        Utilisateur createur = utilisateurRepository.findById(etudiant.getCreateur().getCode()).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));
        Utilisateur modificateur = utilisateurRepository.findById(etudiant.getCreateur().getCode()).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));
        etudiant.setCreateur(createur);
        etudiant.setModificateur(modificateur);
        return etudiantRepository.save(etudiant).getCode().intValue();

    }
//besoin d'un finElementByCode
    @Override
    public int deleteEtudiant(Long code) {
      // etudiantRepository.deleteById(etudiantMapper.findById(code).get().getCode());
        return 1;
    }

    @Override
    public Etudiant searchEtudiantByCode(Long code) throws IsjException {
        return etudiantRepository.findById(code).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));
    }

    @Override
    public List<Etudiant> listEtudiants() {
        return etudiantRepository.findAll();
    }

    @Override
    public Etudiant getEtudiantByCode(Long code) throws IsjException {
        return etudiantRepository.findById(code).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));
    }

    @Override
    public Etudiant getStudentByMatricule(String matricule) throws IsjException {
        return etudiantRepository.findByMatricule(matricule).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));
    }


    @Override
    public void generateAttestationReusite() throws Exception {

    }
}
