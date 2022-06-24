package org.isj.ing3.isi.webservice.webservicerest.presentation.api;

import lombok.extern.slf4j.Slf4j;
import org.isj.ing3.isi.webservice.webservicerest.exception.IsjException;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Note;
import org.isj.ing3.isi.webservice.webservicerest.service.IAnonymat;
import org.isj.ing3.isi.webservice.webservicerest.service.INote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/note")
@Slf4j
public class NoteRestController {

	@Autowired
	private INote iNote;

	@PostMapping("/save")
	public void enregistrer(@RequestBody Note create) throws IsjException {
		iNote.saveNote(create);
	}


	@GetMapping("/{code}/data")
	public ResponseEntity<Note> getNoteByCode(@PathVariable("code") Long code) throws IsjException {

		return ResponseEntity.ok(iNote.getNoteByCode(code));
	}


	@GetMapping("/all")
	public ResponseEntity<List<Note>> getAllNote() {
		return ResponseEntity.ok(iNote.listNotes());
	}

	@GetMapping("/{code}/delete")
	public int deleteNote(@PathVariable("code") Long code){
		return iNote.deleteNote(code);
	}


	@GetMapping("/{inscrit}/{eval}/search")
	public ResponseEntity<List<Note>> getNoteByCodeInscritCodeEvaluation(@PathVariable("inscrit") Long inscrit, @PathVariable("eval") Long eval) throws IsjException {
		return ResponseEntity.ok(iNote.getNoteByCodeInscritCodeEvaluation(inscrit, eval));
	}
}