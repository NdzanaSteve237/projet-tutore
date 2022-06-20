package org.isj.ing3.isi.webservice.webservicerest.presentation.api;

import lombok.extern.slf4j.Slf4j;
import org.isj.ing3.isi.webservice.webservicerest.exception.IsjException;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Candidat;
import org.isj.ing3.isi.webservice.webservicerest.service.IAnonymat;
import org.isj.ing3.isi.webservice.webservicerest.service.ICandidat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/candidat")
@Slf4j
public class CandidatRestController {

	@Autowired
	private ICandidat iCandidat;

	@PostMapping("/save")
	public void enregistrer(@RequestBody Candidat create) throws IsjException {
		iCandidat.saveCandidat(create);
	}


	@GetMapping("/{code}/data")
	public ResponseEntity<Candidat> getCandidatByCode(@PathVariable("code") Long code) throws IsjException {

		return ResponseEntity.ok(iCandidat.getCandidatByCode(code));
	}


	@GetMapping("/all")
	public ResponseEntity<List<Candidat>> getAllCandidats() {
		return ResponseEntity.ok(iCandidat.listCandidats());
	}

	@GetMapping("/{code}/delete")
	public int deteleCandidat(@PathVariable("code") Long code){
		return iCandidat.deleteCandidat(code);
	}

}