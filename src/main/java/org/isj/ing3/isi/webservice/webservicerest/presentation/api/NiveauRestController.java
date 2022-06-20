package org.isj.ing3.isi.webservice.webservicerest.presentation.api;

import lombok.extern.slf4j.Slf4j;
import org.isj.ing3.isi.webservice.webservicerest.exception.IsjException;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Filiere;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Niveau;
import org.isj.ing3.isi.webservice.webservicerest.service.IAnonymat;
import org.isj.ing3.isi.webservice.webservicerest.service.INiveau;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/niveau")
@Slf4j
public class NiveauRestController {

	@Autowired
	private INiveau iNiveau;

	@PostMapping("/save")
	public void enregistrer(@RequestBody Niveau create) throws IsjException {
		iNiveau.saveNiveau(create);
	}


	@GetMapping("/{code}/data")
	public ResponseEntity<Niveau> getNiveauByCode(@PathVariable("code") Long code) throws IsjException {

		return ResponseEntity.ok(iNiveau.getNiveauByCode(code));
	}


	@GetMapping("/all")
	public ResponseEntity<List<Niveau>> getAllNiveau() {
		return ResponseEntity.ok(iNiveau.listNiveaus());
	}

	@GetMapping("/{code}/delete")
	public int deteleNiveau(@PathVariable("code") Long code) throws IsjException {
		return iNiveau.deleteNiveau(code);
	}

	@GetMapping("/{niveau}/search")
	public ResponseEntity<List<Niveau>> getNiveauByNumero(@PathVariable("niveau") long niveau) throws IsjException {
		return ResponseEntity.ok(iNiveau.getNiveauByNumero(niveau));
	}

}