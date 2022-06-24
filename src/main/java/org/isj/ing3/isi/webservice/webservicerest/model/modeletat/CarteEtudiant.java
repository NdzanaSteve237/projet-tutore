package org.isj.ing3.isi.webservice.webservicerest.model.modeletat;

import lombok.Data;

import java.io.Serializable;

@Data
public class CarteEtudiant implements Serializable {
    private String annee;
    private String codeUser;
    private Long codeClasse;

}
