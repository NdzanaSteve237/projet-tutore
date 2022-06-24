package org.isj.ing3.isi.webservice.webservicerest.api;

import lombok.extern.slf4j.Slf4j;
import org.isj.ing3.isi.webservice.webservicerest.exception.IsjException;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Discipline;
import org.isj.ing3.isi.webservice.webservicerest.service.IDiscipline;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/discipline")
@Slf4j
public class DisciplineRestController {

	@Autowired
	private IDiscipline iDiscipline;

	@PostMapping("/save")
	public void enregistrer(@RequestBody Discipline create) throws IsjException {
		iDiscipline.saveDiscipline(create);
	}


	@GetMapping("/{code}/data")
	public ResponseEntity<Discipline> getDisciplineByCode(@PathVariable("code") Long code) throws IsjException {

		return ResponseEntity.ok(iDiscipline.getDisciplineByCode(code));
	}


	@GetMapping("/all")
	public ResponseEntity<List<Discipline>> getAllDiscipline() {
		return ResponseEntity.ok(iDiscipline.listDisciplines());
	}

	@GetMapping("/{code}/delete")
		public int deteleDiscipline(@PathVariable("code") Long code){
			return iDiscipline.deleteDiscipline(code);
	}

}