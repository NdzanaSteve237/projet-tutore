package org.isj.ing3.isi.webservice.webservicerest.presentation.api;

import org.isj.ing3.isi.webservice.webservicerest.exception.IsjException;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.AnneeAcademique;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Semestre;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Specialite;
import org.isj.ing3.isi.webservice.webservicerest.service.ISpecialite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/specialite")
public class SpecialiteRestController {
    @Autowired
    private ISpecialite iSpecialite;

    @PostMapping("/save")
    public void enregistrer(@RequestBody Specialite create) {

        iSpecialite.saveSpecialite(create);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Specialite>> getAllNote() {

        return ResponseEntity.ok(iSpecialite.listSpecialite());
    }

    @GetMapping("/{code}/delete")
    public int deteleSpecialite(@PathVariable("code") Long code) throws IsjException {
        return iSpecialite.deleteSpecialiteByCode(code);
    }
    @GetMapping("/{specialite}/{filiere}/recherche")
    public ResponseEntity<Specialite> searchSpecialiteBySpecialiteOrfiliere (@PathVariable("specialite") String specialite, @PathVariable("filiere") String filiere ) throws IsjException {
        return ResponseEntity.ok(iSpecialite.searchSpecialiteBySpecialiteOrfiliere(specialite,filiere));
    }
}
