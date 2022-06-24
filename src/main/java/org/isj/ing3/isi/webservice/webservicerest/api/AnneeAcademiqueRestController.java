package org.isj.ing3.isi.webservice.webservicerest.api;

import lombok.extern.slf4j.Slf4j;
import org.isj.ing3.isi.webservice.webservicerest.exception.IsjException;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.AnneeAcademique;
import org.isj.ing3.isi.webservice.webservicerest.service.IAnneeAcademique;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/annee-accademique")
@Slf4j
public class AnneeAcademiqueRestController {

	@Autowired
	private IAnneeAcademique ianneeAcademique;

	@PostMapping("/save")
	public void enregistrer(@RequestBody AnneeAcademique create) throws IsjException {
		ianneeAcademique.saveAnneeAcademique(create);
	}


	@GetMapping("/{code}/data")
	public ResponseEntity<AnneeAcademique> getAnneeAcademiqueByCode(@PathVariable("code") Long code) throws IsjException {

		return ResponseEntity.ok(ianneeAcademique.getAnneAcademiqueByCode(code));
	}


	@GetMapping("/all")
	public ResponseEntity<List<AnneeAcademique>> getAllAnneeAccademique() {
		return ResponseEntity.ok(ianneeAcademique.listAnneeAcademique());
	}

	@GetMapping("/{code}/delete")
	public int deteleAnneeAccademique(@PathVariable("code") Long code){
		return ianneeAcademique.deleteAnneAcademique(code);
	}

}