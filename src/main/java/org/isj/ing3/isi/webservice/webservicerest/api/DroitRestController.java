package org.isj.ing3.isi.webservice.webservicerest.api;

import lombok.extern.slf4j.Slf4j;
import org.isj.ing3.isi.webservice.webservicerest.exception.IsjException;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Droit;
import org.isj.ing3.isi.webservice.webservicerest.service.IDroit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/droit")
@Slf4j
public class DroitRestController {

	@Autowired
	private IDroit idroit;

	@PostMapping("/save")
	public void enregistrer(@RequestBody Droit create) throws IsjException {
		idroit.saveDroit(create);
	}


	@GetMapping("/{code}/data")
	public ResponseEntity<Droit> getDroitByCode(@PathVariable("code") Long code) throws IsjException {

		return ResponseEntity.ok(idroit.getDroitByCode(code));
	}


	@GetMapping("/all")
	public ResponseEntity<List<Droit>> getAllDroit() {
		return ResponseEntity.ok(idroit.listDroits());
	}

	@GetMapping("/{code}/delete")
	public int deteleDroit(@PathVariable("code") Long code){
			return idroit.deleteDroit(code);
	}

}