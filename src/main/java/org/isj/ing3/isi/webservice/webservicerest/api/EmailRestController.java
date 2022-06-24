package org.isj.ing3.isi.webservice.webservicerest.api;

import lombok.extern.slf4j.Slf4j;
import org.isj.ing3.isi.webservice.webservicerest.exception.IsjException;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Email;
import org.isj.ing3.isi.webservice.webservicerest.service.IEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/email")
@Slf4j
public class EmailRestController {

	@Autowired
	private IEmail iemail;

	@PostMapping("/save")
	public void enregistrer(@RequestBody Email create) {
		iemail.saveEmail(create);
	}


	@GetMapping("/{code}/data")
	public ResponseEntity<Email> getEmailCode(@PathVariable("code") Long code) throws IsjException {

		return ResponseEntity.ok(iemail.getByEmailCode(code));
	}


	@GetMapping("/all")
	public ResponseEntity<List<Email>> getAllEmail() {
		return ResponseEntity.ok(iemail.listEmails());
	}

	@GetMapping("/{code}/delete")
	public int deteleEmail(@PathVariable("code") Long code){
			return iemail.deleteEmail(code);
	}

}