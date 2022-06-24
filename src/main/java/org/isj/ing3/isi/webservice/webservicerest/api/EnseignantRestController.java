package org.isj.ing3.isi.webservice.webservicerest.api;

import lombok.extern.slf4j.Slf4j;
import org.isj.ing3.isi.webservice.webservicerest.exception.IsjException;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Enseignant;
import org.isj.ing3.isi.webservice.webservicerest.service.IEnseignant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enseignant")
@Slf4j

public class EnseignantRestController {

    @Autowired
    private IEnseignant iEnseignant;

    @PostMapping("/save")
    public void enregistrer(@RequestBody Enseignant create) throws IsjException {iEnseignant.saveEnseignant(create);
    }


    @GetMapping("/{code}/data")
    public ResponseEntity<Enseignant> getEnseignantByCode(@PathVariable("code") Long code) throws IsjException {

        return ResponseEntity.ok(iEnseignant.getEnseignantByCode(code));
    }


    @GetMapping("/all")
    public ResponseEntity<List<Enseignant>> getAllEtudiant() {
        return ResponseEntity.ok(iEnseignant.listEnseignants());
    }

    @GetMapping("/{code}/delete")
    public int deteleEnseignant(@PathVariable("code") Long code){
        return iEnseignant.deleteEnseignant(code);
    }

}


