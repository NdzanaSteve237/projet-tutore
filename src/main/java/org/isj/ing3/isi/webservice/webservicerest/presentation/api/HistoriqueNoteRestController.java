package org.isj.ing3.isi.webservice.webservicerest.presentation.api;

import lombok.extern.slf4j.Slf4j;
import org.isj.ing3.isi.webservice.webservicerest.exception.IsjException;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.HistoriqueNote;
import org.isj.ing3.isi.webservice.webservicerest.service.IAnonymat;
import org.isj.ing3.isi.webservice.webservicerest.service.IHistoriqueNote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/historiqueNote")
@Slf4j
public class HistoriqueNoteRestController {

	@Autowired
	private IHistoriqueNote iHistoriqueNote;

	@PostMapping("/save")
	public void enregistrer(@RequestBody HistoriqueNote create) throws IsjException {
		iHistoriqueNote.saveHistoriqueNote(create);
	}


	@GetMapping("/{code}/data")
	public ResponseEntity<HistoriqueNote> getHistoriqueNoteByCode(@PathVariable("code") Long code) throws IsjException {

		return ResponseEntity.ok(iHistoriqueNote.getHistoriqueNoteByCode(code));
	}


	@GetMapping("/all")
	public ResponseEntity<List<HistoriqueNote>> getAllHistoriqueNote() {
		return ResponseEntity.ok(iHistoriqueNote.listHistoriqueNotes());
	}

	@GetMapping("/{code}/delete")
	public int deteleHistoriqueNote(@PathVariable("code") Long code){
		return iHistoriqueNote.deleteHistoriqueNote(code);
	}

}