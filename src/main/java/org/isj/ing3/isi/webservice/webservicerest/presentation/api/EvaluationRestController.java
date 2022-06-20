package org.isj.ing3.isi.webservice.webservicerest.presentation.api;

import lombok.extern.slf4j.Slf4j;
import org.isj.ing3.isi.webservice.webservicerest.exception.IsjException;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Evaluation;
import org.isj.ing3.isi.webservice.webservicerest.service.IAnneeAcademique;
import org.isj.ing3.isi.webservice.webservicerest.service.IEvaluation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/evaluation")
@Slf4j

public class EvaluationRestController {


        @Autowired
        private IEvaluation iEvaluation;

        @PostMapping("/save")
        public void enregistrer(@RequestBody Evaluation create) throws IsjException {
            iEvaluation.saveEvaluation(create);
        }


        @GetMapping("/{code}/data")
        public ResponseEntity<Evaluation> getEvaluationByCode(@PathVariable("code") Long code) throws IsjException {

            return ResponseEntity.ok(iEvaluation.getEvaluationByCode(code));
        }


        @GetMapping("/all")
        public ResponseEntity<List<Evaluation>> getAllEvaluation() {
            return ResponseEntity.ok(iEvaluation.listEvaluation());
        }

        @GetMapping("/{libelle}/delete")
        public int deleteEvaluation(@PathVariable("code") Long code) throws IsjException {
            return iEvaluation.deleteEvaluation(code);
        }

    }

