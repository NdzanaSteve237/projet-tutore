package org.isj.ing3.isi.webservice.webservicerest.presentation.api;

import lombok.extern.slf4j.Slf4j;
import org.isj.ing3.isi.webservice.webservicerest.exception.IsjException;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Enseignement;
import org.isj.ing3.isi.webservice.webservicerest.service.IEnseignement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/enseignement")
public class EnseignementRestController {
    @Autowired
    private IEnseignement iEnseignement;

    @PostMapping("/save")
    public void enregistrer(@RequestBody Enseignement create) {iEnseignement.saveEnseignement(create);
    }


    @GetMapping("/{code}/data")
    public ResponseEntity<Enseignement> getEnseignementByCode(@PathVariable("code") Long code) throws IsjException {

        return ResponseEntity.ok(iEnseignement.getEnseignementByCode(code));
    }


    @GetMapping("/all")
    public ResponseEntity<List<Enseignement>> getAllEnseignements() {
        return ResponseEntity.ok(iEnseignement.listEnseignements());
    }

    @GetMapping("/{libelle}/delete")
    public void deteleEnseignements(@PathVariable("code") Long code) throws IsjException {
         iEnseignement.deleteEnseignement(code);
    }

}


