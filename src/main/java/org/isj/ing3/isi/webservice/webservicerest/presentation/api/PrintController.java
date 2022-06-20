package org.isj.ing3.isi.webservice.webservicerest.presentation.api;

import org.isj.ing3.isi.webservice.webservicerest.service.IEtudiant;
import org.isj.ing3.isi.webservice.webservicerest.service.IPrintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/print")
public class PrintController {
    @Autowired
    private IEtudiant iEtudiant;
    @Autowired
    private IPrintService iPrintService;

    @GetMapping("/generatecarteetudiant")
    public ResponseEntity<?> generateCarteEtudiant() throws Exception {
        try {
            return new ResponseEntity<byte[]>(iPrintService.generateCarteEtudiant(), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.CREATED);
        }
    }

    @GetMapping("/generateatestation")
    public ResponseEntity<?> generateAttestation() throws Exception {
        try {
            return new ResponseEntity<byte[]>(iPrintService.generateAttestationReusite(), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.CREATED);
        }
    }


    @GetMapping("/generatediplome")
    public ResponseEntity<?> generateDiplome() throws Exception {

        try {
            return new ResponseEntity<byte[]>(iPrintService.generateDiplome(), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.CREATED);
        }
    }

    @GetMapping("/generateficheabsence")
    public ResponseEntity<?> generateFicheAbsence() throws Exception {
        try {
            return new ResponseEntity<byte[]>(iPrintService.generateFicheAbsences(), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.CREATED);
        }
    }

    @GetMapping("/generatePv")
    public ResponseEntity<?> generatePv() throws Exception {
        try {
            return new ResponseEntity<byte[]>(iPrintService.generatePv(), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.CREATED);
        }
    }

    @GetMapping("/generateNotes")
    public ResponseEntity<?> generateNotes() throws Exception {
        try {
            return new ResponseEntity<byte[]>(iPrintService.generateNotes(), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.CREATED);
        }
    }
}
