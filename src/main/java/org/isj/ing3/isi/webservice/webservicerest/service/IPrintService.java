package org.isj.ing3.isi.webservice.webservicerest.service;

import org.isj.ing3.isi.webservice.webservicerest.model.modeletat.AttestationEtDiplome;
import org.isj.ing3.isi.webservice.webservicerest.model.modeletat.CarteEtudiant;
import org.isj.ing3.isi.webservice.webservicerest.model.modeletat.FicheAbsence;
import org.isj.ing3.isi.webservice.webservicerest.model.modeletat.Pv;

public interface IPrintService {

    byte[] generateCarteEtudiant(CarteEtudiant carteEtudiant) throws Exception;
    byte[] generateAttestationReusite(AttestationEtDiplome attestation) throws Exception;
    byte[] generateDiplome(AttestationEtDiplome diplome) throws Exception;
    byte[] generateFicheAbsences(FicheAbsence ficheAbsence) throws Exception;
    byte[] generatePv(Pv pv) throws Exception;
    byte[] generateNotes() throws Exception;

}
