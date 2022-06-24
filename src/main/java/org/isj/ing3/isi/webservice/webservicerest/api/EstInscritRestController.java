package org.isj.ing3.isi.webservice.webservicerest.api;

import lombok.extern.slf4j.Slf4j;
import org.isj.ing3.isi.webservice.webservicerest.exception.IsjException;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.EstInscrit;
import org.isj.ing3.isi.webservice.webservicerest.service.IEstInscrit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/est-inscrit")
public class EstInscritRestController {
    @Autowired
    private IEstInscrit iEstInscrit;

    @PostMapping("/save")
    public void enregistrer(@RequestBody EstInscrit create) throws IsjException {
        iEstInscrit.saveInscrit(create);
    }


    @GetMapping("/{code}/data")
    public ResponseEntity<EstInscrit> getInscritByCodeBy(@PathVariable("code") Long code) throws IsjException {

        return ResponseEntity.ok(iEstInscrit.getInscritByCode(code));
    }


    @GetMapping("/all")
    public ResponseEntity<List<EstInscrit>> getAllEstInscrit() {
        return ResponseEntity.ok(iEstInscrit.listInscrit());
    }

    @GetMapping("/{code}/delete")
    public int deteleInscrit(@PathVariable("code") String libelle){
        return iEstInscrit.deleteInscrit(libelle);
    }

}


