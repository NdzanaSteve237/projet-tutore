package org.isj.ing3.isi.webservice.webservicerest.presentation.api;

import org.isj.ing3.isi.webservice.webservicerest.exception.IsjException;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Sms;
import org.isj.ing3.isi.webservice.webservicerest.service.ISession;
import org.isj.ing3.isi.webservice.webservicerest.service.ISms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sms")
public class SmsRestController {
    @Autowired
    private ISms iSms;

    @PostMapping("/save")
    public void enregistrer(@RequestBody Sms create) {
        iSms.saveSms(create);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Sms>> getAllNote() {
        return ResponseEntity.ok(iSms.listSms());
    }

    @GetMapping("/{code}/delete")
    public int deteleSms(@PathVariable("code") Long code) throws IsjException {
        return iSms.deleteSmsByCode(code);
    }
}
