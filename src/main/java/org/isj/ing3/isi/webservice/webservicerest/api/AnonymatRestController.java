package org.isj.ing3.isi.webservice.webservicerest.api;

import lombok.extern.slf4j.Slf4j;
import org.isj.ing3.isi.webservice.webservicerest.exception.IsjException;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Anonymat;
import org.isj.ing3.isi.webservice.webservicerest.service.IAnonymat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/anonymat")
@Slf4j
public class AnonymatRestController {

	@Autowired
	private IAnonymat ianonymat;

	@PostMapping("/save")
	public void enregistrer(@RequestBody Anonymat create) {
		ianonymat.saveAnonymat(create);
	}


	@GetMapping("/{code}/data")
	public ResponseEntity<Anonymat> getAnonymatByCode(@PathVariable("code") Long code) throws IsjException {

		return ResponseEntity.ok(ianonymat.getAnonymatByCode(code));
	}


	@GetMapping("/all")
	public ResponseEntity<List<Anonymat>> getAllAnonymat() {
		return ResponseEntity.ok(ianonymat.listAnonymat());
	}

	@GetMapping("/{code}/delete")
	public int deteleAnonymat(@PathVariable("code") Long code){
		return ianonymat.deleteAnonymat(code);
	}

}