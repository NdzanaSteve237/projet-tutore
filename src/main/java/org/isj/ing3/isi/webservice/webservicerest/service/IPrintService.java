package org.isj.ing3.isi.webservice.webservicerest.service;

public interface IPrintService {

    void generateCarteEtudiant() throws Exception;
    void generateAttestationReusite() throws Exception;
    void generateDiplome() throws Exception;
    void generateFicheAbsences() throws Exception;
    void generatePv() throws Exception;
    void generateNotes() throws Exception;

}
