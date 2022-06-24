package org.isj.ing3.isi.webservice.webservicerest.api;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.isj.ing3.isi.webservice.webservicerest.exception.IsjException;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Etudiant;
import org.isj.ing3.isi.webservice.webservicerest.service.IEtudiant;
import org.isj.ing3.isi.webservice.webservicerest.service.IPrintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/etudiant")
@Slf4j

public class EtudiantRestController {
    @Autowired
    private IEtudiant iEtudiant;
    @Autowired
    IPrintService iPrintService;

    @PostMapping("/save")
    public void enregistrer(@RequestBody Etudiant create) throws IsjException {
        iEtudiant.saveEtudiant(create);
    }


    @GetMapping("/{code}/data")
    public ResponseEntity<Etudiant> getEtudiantByCode(@PathVariable("code") Long code) throws IsjException {

        return ResponseEntity.ok(iEtudiant.getEtudiantByCode(code));
    }


    @GetMapping("/all")
    public ResponseEntity<List<Etudiant>> getEtudiant() {
        return ResponseEntity.ok(iEtudiant.listEtudiants());
    }

    @GetMapping("/{code}/delete")
    public int deteleEtudiant(@PathVariable("code") Long code){
        return iEtudiant.deleteEtudiant(code);
    }

    @SneakyThrows
    @GetMapping("/get/{matricule}")
    public ResponseEntity<Etudiant> getEtudiantByMatricule(@PathVariable("matricule") String matricule) throws IsjException {
        return ResponseEntity.ok(iEtudiant.getStudentByMatricule(matricule));
    }
 }