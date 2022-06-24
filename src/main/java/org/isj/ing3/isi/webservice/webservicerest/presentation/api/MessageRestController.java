package org.isj.ing3.isi.webservice.webservicerest.presentation.api;

import lombok.extern.slf4j.Slf4j;
import org.isj.ing3.isi.webservice.webservicerest.exception.IsjException;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Message;
import org.isj.ing3.isi.webservice.webservicerest.service.IAnonymat;
import org.isj.ing3.isi.webservice.webservicerest.service.IMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/message")
@Slf4j
public class MessageRestController {

	@Autowired
	private IMessage iMessage;

	@PostMapping("/save")
	public void enregistrer(@RequestBody Message create) throws IsjException {
		iMessage.saveMessage(create);
	}


	@GetMapping("/{code}/data")
	public ResponseEntity<Message> getMessageByCode(@PathVariable("code") Long code) throws IsjException {

		return ResponseEntity.ok(iMessage.getMessageByCode(code));
	}


	@GetMapping("/all")
	public ResponseEntity<List<Message>> getAllMessage() {
		return ResponseEntity.ok(iMessage.listMessages());
	}

	@GetMapping("/{code}/delete")
	public int deteleMessage(@PathVariable("code") Long code) throws IsjException {
		return iMessage.deleteMessage(code);
	}

}