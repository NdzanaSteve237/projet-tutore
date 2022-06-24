package org.isj.ing3.isi.webservice.webservicerest.service;

public interface IPrintService {

    byte[] generateCarteEtudiant() throws Exception;
    byte[] generateAttestationReusite() throws Exception;
    byte[] generateDiplome() throws Exception;
    byte[] generateFicheAbsences() throws Exception;
    byte[] generatePv() throws Exception;
    byte[] generateNotes() throws Exception;

}
