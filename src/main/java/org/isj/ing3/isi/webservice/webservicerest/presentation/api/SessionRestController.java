package org.isj.ing3.isi.webservice.webservicerest.presentation.api;

import org.isj.ing3.isi.webservice.webservicerest.exception.IsjException;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Session;
import org.isj.ing3.isi.webservice.webservicerest.service.ISession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/session")
public class SessionRestController {
    @Autowired
    private ISession iSession;

    @PostMapping("/save")
    public void enregistrer(@RequestBody Session create) {
        iSession.saveSession(create);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Session>> getAllNote() {
        return ResponseEntity.ok(iSession.listSession());
    }

    @GetMapping("/{code}/delete")
    public int deteleSession(@PathVariable("code") Long code) throws IsjException {
        return iSession.deleteSessionByCode(code);
    }
}
