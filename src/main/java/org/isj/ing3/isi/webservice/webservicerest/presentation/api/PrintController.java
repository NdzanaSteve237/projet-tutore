package org.isj.ing3.isi.webservice.webservicerest.presentation.api;

import org.isj.ing3.isi.webservice.webservicerest.service.IEtudiant;
import org.isj.ing3.isi.webservice.webservicerest.service.IPrintService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void generateCarteEtudiant() throws Exception {
        iPrintService.generateCarteEtudiant();
    }

    @GetMapping("/generateatestation")
    public void generateAttestation() throws Exception {
        iPrintService.generateAttestationReusite();
    }


    @GetMapping("/generatediplome")
    public void generateDiplome() throws Exception {
        iPrintService.generateDiplome();
    }

    @GetMapping("/generateficheabsence")
    public void generateFicheAbsence() throws Exception {
        iPrintService.generateFicheAbsences();
    }

    @GetMapping("/generatePv")
    public void generatePv() throws Exception {
        iPrintService.generatePv();
    }

    @GetMapping("/generateNotes")
    public void generateNotes() throws Exception {
        iPrintService.generateNotes();
    }
}
