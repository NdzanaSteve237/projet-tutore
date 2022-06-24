package org.isj.ing3.isi.webservice.webservicerest.presentation.api;

import org.isj.ing3.isi.webservice.webservicerest.exception.IsjException;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Specialite;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.TypeNoteCC;
import org.isj.ing3.isi.webservice.webservicerest.service.ISpecialite;
import org.isj.ing3.isi.webservice.webservicerest.service.ITypeNoteCC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/TypeNoteCC")

public class TypeNoteCCRestController {
    @Autowired
    private ITypeNoteCC iTypeNoteCC;

    @PostMapping("/save")
    public void enregistrer(@RequestBody TypeNoteCC create) throws IsjException {

        iTypeNoteCC.saveTypeNoteCC(create);
    }

    @GetMapping("/all")
    public ResponseEntity<List<TypeNoteCC>> getAllNote() {

        return ResponseEntity.ok(iTypeNoteCC.listTypeNoteCC());
    }

    @GetMapping("/{code}/delete")
    public int deteleSpecialite(@PathVariable("code") Long code) throws IsjException {
        System.out.println(code);
        return iTypeNoteCC.deleteTypeNoteCC(code);
    }
}