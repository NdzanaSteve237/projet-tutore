package org.isj.ing3.isi.webservice.webservicerest.presentation.api;

import org.isj.ing3.isi.webservice.webservicerest.exception.IsjException;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.AnneeAcademique;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Semestre;
import org.isj.ing3.isi.webservice.webservicerest.service.ISemestre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/semestre")
public class SemestreRestController {
    @Autowired
    private ISemestre iSemestre;

    @PostMapping("/save")
    public void enregistrer(@RequestBody Semestre create) {
        iSemestre.saveSemestre(create);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Semestre>> getAllNote() {
        return ResponseEntity.ok(iSemestre.listSemestre());
    }

    @GetMapping("/{code}/delete")
    public int deteleSemestre(@PathVariable("code") Long code) throws IsjException {
        return iSemestre.deleteSemestreByCode(code);
    }
    @GetMapping("/{libelle}/{annee_academique}/recherche")
    public ResponseEntity<Semestre> searchSemestreByLibelleOrAnneeAcademique (@PathVariable("libelle") String libelle, @PathVariable("annee_academique")AnneeAcademique annee_academique ) throws IsjException {
        return ResponseEntity.ok(iSemestre.searchSemestreByLibelleOrAnneeAcademique(libelle,annee_academique));
    }
}
