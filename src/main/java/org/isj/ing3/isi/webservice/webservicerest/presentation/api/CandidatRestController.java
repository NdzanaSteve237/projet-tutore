package org.isj.ing3.isi.webservice.webservicerest.presentation.api;

import lombok.extern.slf4j.Slf4j;
import org.isj.ing3.isi.webservice.webservicerest.exception.IsjException;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Candidat;
import org.isj.ing3.isi.webservice.webservicerest.service.IAnonymat;
import org.isj.ing3.isi.webservice.webservicerest.service.ICandidat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
	public String enregistrer(@RequestBody Candidat create) throws IsjException {
		try {
			iCandidat.saveCandidat(create);
		}catch (IsjException exception) {
			return exception.getMessage();
		}

		return "enregistrement reussi";

	}


	@GetMapping("/{code}/data")
	public ResponseEntity<?> getCandidatByCode(@PathVariable("code") Long code) throws IsjException {
		try {
			return ResponseEntity.ok(iCandidat.getCandidatByCode(code));
		}catch (IsjException exception) {
			return new ResponseEntity<String>(exception.getMessage(), HttpStatus.NOT_FOUND);
		}

	}


	@GetMapping("/all")
	public ResponseEntity<List<Candidat>> getAllCandidats() {
		return ResponseEntity.ok(iCandidat.listCandidats());
	}

	@GetMapping("/{code}/delete")
	public ResponseEntity<?> deteleCandidat(@PathVariable("code") Long code) throws IsjException{
		try {
			return ResponseEntity.ok(iCandidat.deleteCandidat(code));
		}catch (IsjException exception) {
			return new ResponseEntity<String>(exception.getMessage(), HttpStatus.NOT_FOUND);
		}

	}
	@PostMapping("/update")
	public String modifier(@RequestBody Candidat create) throws IsjException {
		try {
			iCandidat.updateCandidat(create);
		}catch (IsjException exception) {
			return exception.getMessage();
		}

		return "modification reussi";

	}
	@GetMapping("/{telephone}/delete")
	public ResponseEntity<?> searchCandidatByTelephone(@PathVariable("telephone") int telephone) throws IsjException{
		try {
			return ResponseEntity.ok(iCandidat.searchCandidatBytelephone(telephone));
		}catch (IsjException exception) {
			return new ResponseEntity<String>(exception.getMessage(), HttpStatus.NOT_FOUND);
		}

	}

}