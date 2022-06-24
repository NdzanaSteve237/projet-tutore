package org.isj.ing3.isi.webservice.webservicerest.serviceImpl;

import org.isj.ing3.isi.webservice.webservicerest.model.entities.Classe;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Note;
import org.isj.ing3.isi.webservice.webservicerest.model.modeletat.AttestationEtDiplome;
import org.isj.ing3.isi.webservice.webservicerest.model.modeletat.CarteEtudiant;
import org.isj.ing3.isi.webservice.webservicerest.model.modeletat.FicheAbsence;
import org.isj.ing3.isi.webservice.webservicerest.model.modeletat.Pv;
import org.isj.ing3.isi.webservice.webservicerest.service.IClasse;
import org.isj.ing3.isi.webservice.webservicerest.service.IFiliere;
import org.isj.ing3.isi.webservice.webservicerest.service.INote;
import org.isj.ing3.isi.webservice.webservicerest.service.IPrintService;
import org.isj.ing3.isi.webservice.webservicerest.utils.etats.GeneratePDF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class PrintServiceImpl implements IPrintService {

    @Autowired
    INote iNote;
    @Autowired
    IClasse iClasse;
    @Autowired
    IFiliere iFiliere;

    @Autowired
    GeneratePDF generatePDF;

    @Override
    public byte[] generateCarteEtudiant(CarteEtudiant carteEtudiant) throws Exception {

        try {
            Classe classe = iClasse.getClasseByCode(carteEtudiant.getCodeClasse());
            //CarteEtudiant carteEtudiant = new CarteEtudiant("Nkot-a-Nzok", "Honoré Etienne", "05-01-2002", "699278957", "690581290", "2021i321", "Edea", "Informatique et Système d'information", "ISI", "3", 2021);
            //return generatePDF.genererCartesEtudiants("Ingenieur", "Informatique et systèmes d'information", 2020, 3);
            return generatePDF.genererCartesEtudiants(classe.getSpecialite().getFiliere().getLibelle(), classe.getSpecialite().getLibelle(), Integer.valueOf(carteEtudiant.getAnnee().substring(0, carteEtudiant.getAnnee().indexOf("/"))), classe.getNiveau().getNumero());

        } catch (Exception e) {
            throw new Exception("Erreur lors de l'impression des cartes d'étudiants");
        }
    }

    @Override
    public byte[] generateAttestationReusite(AttestationEtDiplome attestation) throws Exception {
        try {

            Classe classe = iClasse.getClasseByCode(attestation.getCodeClasse());
            List<Note> notes = iNote.getNotesByFiliereAndSpecialiteAndNiveauAndAnneDebutOrderByCandidat(classe.getSpecialite().getFiliere().getLibelle(), classe.getSpecialite().getLibelle(), classe.getNiveau().getNumero(), attestation.getAnnee());

            //generatePDF.genererDiplome(noteEnvois, "LIC 3", 2020, "2020-07-18", "Concepteur Développeur d’Applications pour l’Economie Numérique", "Licence Professionnelle", 3);
            return generatePDF.genererAttestation(notes, classe.getLibelle(), attestation.getAnnee(), attestation.getDateJury(), classe.getSpecialite().getLibelle(), classe.getSpecialite().getFiliere().getLibelle(), classe.getNiveau().getNumero());

        }catch (Exception e) {
            throw new Exception("Erreur lors de l'impression des attestations");
        }
    }

    @Override
    public byte[] generateDiplome(AttestationEtDiplome diplome) throws Exception {

        try {

            Classe classe = iClasse.getClasseByCode(diplome.getCodeClasse());
            List<Note> notes = iNote.getNotesByFiliereAndSpecialiteAndNiveauAndAnneDebutOrderByCandidat(classe.getSpecialite().getFiliere().getLibelle(), classe.getSpecialite().getLibelle(), classe.getNiveau().getNumero(), diplome.getAnnee());

            //return generatePDF.genererDiplome(noteEnvois, "LIC 3", 2020, "2020-07-18", "Concepteur Développeur d’Applications pour l’Economie Numérique", "Licence Professionnelle", 3);
            return generatePDF.genererDiplome(notes, classe.getLibelle(), diplome.getAnnee(), diplome.getDateJury(), classe.getSpecialite().getLibelle(), classe.getSpecialite().getFiliere().getLibelle(), classe.getNiveau().getNumero());

        }catch (Exception e) {
            throw new Exception("Erreur lors de l'impression des diplomes");
        }
    }

    @Override
    public byte[] generateFicheAbsences(FicheAbsence ficheAbsence) throws Exception {
        try{
            Classe classe = iClasse.getClasseByCode(ficheAbsence.getCodeClasse());
            DateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return generatePDF.genererFicheAbsence(classe.getLibelle(), classe.getNiveau().getNumero(), classe.getSpecialite().getFiliere().getLibelle(), ficheAbsence.getAnnee(), simpleDateFormat.parse(ficheAbsence.getDateDebut()+" 23:00:00"), simpleDateFormat.parse(ficheAbsence.getDateFin()+" 23:00:0"), ficheAbsence.getSemestre());

        }catch (Exception e) {
            throw new Exception("Erreur lors de l'impression des fiches d'absences");
        }
    }

    @Override
    public byte[] generatePv(Pv pv) throws Exception {
        try {
            Classe classe = iClasse.getClasseByCode(pv.getCodeClasse());
            return generatePDF.genererPv(Integer.valueOf(pv.getAnnee().substring(0, pv.getAnnee().indexOf("/"))),pv.getSemestre(),classe.getLibelle(), classe.getSpecialite().getFiliere().getLibelle(), classe.getSpecialite().getLibelle(), pv.isFormat(), classe.getNiveau().getNumero());

        } catch (Exception e) {
            throw new Exception("Erreur lors de l'impression des pv");
        }
    }

    @Override
    public byte[] generateNotes() throws Exception {
        try {
            return generatePDF.imprimerNotes(3, "Semestre 1","2020","Semestre 1", "Informatique et systèmes d'information", "CC", 2020, "ISI3165");
        }catch (Exception e) {
            throw new Exception("Erreur lors de l'impression des notes");
        }
    }
}
