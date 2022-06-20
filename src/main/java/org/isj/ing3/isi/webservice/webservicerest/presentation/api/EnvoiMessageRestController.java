package org.isj.ing3.isi.webservice.webservicerest.presentation.api;

import lombok.extern.slf4j.Slf4j;
import org.isj.ing3.isi.webservice.webservicerest.exception.IsjException;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.EnvoiMessage;
import org.isj.ing3.isi.webservice.webservicerest.service.IEnvoiMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/envoi-message")
@Slf4j
public class EnvoiMessageRestController {
    @Autowired
    private IEnvoiMessage iEnvoiMessage;

    @PostMapping("/save")
    public void enregistrer(@RequestBody EnvoiMessage create) throws IsjException {iEnvoiMessage.saveEnvoiMessage(create);
    }


    @GetMapping("/{code}/data")
    public ResponseEntity<EnvoiMessage> getEnvoiMessageByCode(@PathVariable("code") Long code) throws IsjException {

        return ResponseEntity.ok(iEnvoiMessage.getEnvoiMessageByCode(code));
    }


    @GetMapping("/all")
    public ResponseEntity<List<EnvoiMessage>> getAllEnvoiMessage() {
        return ResponseEntity.ok(iEnvoiMessage.listEnvoiMessage());
    }

    @GetMapping("/{libelle}/delete")
    public int deteleEnvoiMessage(@PathVariable("libelle") String libelle){
        return iEnvoiMessage.deleteEnvoiMessage(libelle);
    }

}

