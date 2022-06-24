package org.isj.ing3.isi.webservice.webservicerest.api;

import lombok.extern.slf4j.Slf4j;
import org.isj.ing3.isi.webservice.webservicerest.exception.IsjException;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.TypeEvaluation;
import org.isj.ing3.isi.webservice.webservicerest.service.ITypeEvaluation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/type-evaluation")
@Slf4j
public class TypeEvaluationRestController {

    @Autowired
    ITypeEvaluation iTypeEvaluation;


    @PostMapping("/save")
    public void enregistrer(@RequestBody TypeEvaluation create) throws IsjException {
        iTypeEvaluation.saveTypeEvaluation(create);
    }


    @GetMapping("/{code}/data")
    public ResponseEntity<TypeEvaluation> getTypeEvaluationByCode(@PathVariable("code") Long code) throws IsjException {

        return ResponseEntity.ok(iTypeEvaluation.getTypeEvaluationByCode(code));
    }


    @GetMapping("/all")
    public ResponseEntity<List<TypeEvaluation>> getAllTypeEvaluation() {
        return ResponseEntity.ok(iTypeEvaluation.listTypeEvaluation());
    }

    @GetMapping("/{code}/delete")
    public int deteleAnneeAccademique(@PathVariable("code") Long code){
        return iTypeEvaluation.deleteTypeEvaluation(code);
    }

}
