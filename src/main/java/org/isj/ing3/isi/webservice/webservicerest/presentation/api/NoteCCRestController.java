package org.isj.ing3.isi.webservice.webservicerest.presentation.api;

import org.isj.ing3.isi.webservice.webservicerest.exception.IsjException;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.*;
import org.isj.ing3.isi.webservice.webservicerest.service.IFiliere;
import org.isj.ing3.isi.webservice.webservicerest.service.INoteCC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notecc")
public class NoteCCRestController {
    @Autowired
    private INoteCC iNoteCC;

    @PostMapping("/save")
    public void enregistrer(@RequestBody NoteCC create) throws IsjException {
        iNoteCC.saveNoteCC(create);
    }


    @GetMapping("/{code}/data")
    public ResponseEntity<NoteCC> getNoteCCByCode(@PathVariable("code") Long code) throws IsjException {

        return ResponseEntity.ok(iNoteCC.getNoteCCByCode(code));
    }


    @GetMapping("/all")
    public ResponseEntity<List<NoteCC>> getAllNoteCC() {

        return ResponseEntity.ok(iNoteCC.listNoteCC());
    }

    @GetMapping("/{code}/delete")
    public int deleteNoteCC(@PathVariable("code") Long code) throws IsjException {
        return iNoteCC.deleteNoteCC(code);
    }

    @GetMapping("/{candidat}/{typeNoteCC}/recherche")
    public ResponseEntity<NoteCC> searchNoteCCByCandidatOrTypeNoteCC (@PathVariable("Candidat") Candidat candidat, @PathVariable("TypeNoteCC") TypeNoteCC typeNoteCC ) throws IsjException {
        return ResponseEntity.ok(iNoteCC.searchNoteCCByCandidatOrTypeNoteCC(candidat,typeNoteCC));
    }

}
