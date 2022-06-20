package org.isj.ing3.isi.webservice.webservicerest.serviceImpl;

import org.isj.ing3.isi.webservice.webservicerest.model.entities.Note;
import org.isj.ing3.isi.webservice.webservicerest.service.INote;
import org.isj.ing3.isi.webservice.webservicerest.service.IPrintService;
import org.isj.ing3.isi.webservice.webservicerest.utils.etats.GeneratePDF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class PrintServiceImpl implements IPrintService {

    @Autowired
    INote iNote;

    @Autowired
    GeneratePDF generatePDF;

    @Override
    public byte[] generateCarteEtudiant() throws Exception {
        //CarteEtudiant carteEtudiant = new CarteEtudiant("Nkot-a-Nzok", "Honoré Etienne", "05-01-2002", "699278957", "690581290", "2021i321", "Edea", "Informatique et Système d'information", "ISI", "3", 2021);
        return generatePDF.genererCartesEtudiants("Ingenieur", "Informatique et systèmes d'information", 2020, 3);
    }

    @Override
    public byte[] generateAttestationReusite() throws Exception {
        List<Note> notes = iNote.listNotesWithLimit();
        List<Note> noteEnvois = new ArrayList<Note>();
        for (int i = 0; i < 4; i++) {
            noteEnvois.add(notes.get(i));
        }
        return generatePDF.genererAttestation(noteEnvois, "LIC 3", 2020, "2020-07-18", "Concepteur Développeur d’Applications pour l’Economie Numérique", "Licence Professionnelle", 3);
    }

    @Override
    public byte[] generateDiplome() throws Exception {
        List<Note> notes = iNote.listNotesWithLimit();
        List<Note> noteEnvois = new ArrayList<Note>();
        for (int i = 0; i < 4; i++) {
            noteEnvois.add(notes.get(i));
        }
        return generatePDF.genererDiplome(noteEnvois, "LIC 3", 2020, "2020-07-18", "Concepteur Développeur d’Applications pour l’Economie Numérique", "Licence Professionnelle", 3);

    }

    @Override
    public byte[] generateFicheAbsences() throws Exception {
        DateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return generatePDF.genererFicheAbsence("LIC 3", 3, "Licence Professionnelle", 2020, simpleDateFormat.parse("2020-08-29 23:00:00"), simpleDateFormat.parse("2021-08-28 23:00:0"), "Semestre 1");
    }

    @Override
    public byte[] generatePv() throws Exception {
        return generatePDF.genererPv(2020,"Semestre 1","ING 3", "Ingenieur", "Informatique et systèmes d'information", true, 3);
    }

    @Override
    public byte[] generateNotes() throws Exception {
        return generatePDF.imprimerNotes(3, "Semestre 1","2020","Semestre 1", "Informatique et systèmes d'information", "CC", 2020, "ISI3165");

    }
}
