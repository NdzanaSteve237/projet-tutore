package org.isj.ing3.isi.webservice.webservicerest.presentation.api;

import lombok.extern.slf4j.Slf4j;
import org.isj.ing3.isi.webservice.webservicerest.exception.IsjException;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Classe;
import org.isj.ing3.isi.webservice.webservicerest.service.ICandidat;
import org.isj.ing3.isi.webservice.webservicerest.service.IClasse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/classe")
@Slf4j
public class ClasseRestController {

	@Autowired
	private IClasse iClasse;

	@PostMapping("/save")
	public void enregistrer(@RequestBody Classe create) throws IsjException {
		iClasse.saveClasse(create);
	}


	@GetMapping("/{code}/data")
	public ResponseEntity<Classe> getClassByCode(@PathVariable("code") Long code) throws IsjException {

		return ResponseEntity.ok(iClasse.getClasseByCode(code));
	}


	@GetMapping("/all")
	public ResponseEntity<List<Classe>> getAllClass() {
		return ResponseEntity.ok(iClasse.listClasses());
	}

	@GetMapping("/{code}/delete")
	public int deteleClasse(@PathVariable("code") Long code){
		return iClasse.deleteClass(code);
	}

}