package org.isj.ing3.isi.webservice.webservicerest.model.modeletat;

import lombok.Data;

@Data
public class FicheAbsence {
    private int annee;
    private String dateJury;
    private Long codeClasse;
    private String codeUser;
    private String semestre;
    private String dateDebut;
    private String dateFin;
}
