package org.isj.ing3.isi.webservice.webservicerest.presentation.api;

import lombok.extern.slf4j.Slf4j;
import org.isj.ing3.isi.webservice.webservicerest.exception.IsjException;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Filiere;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Module;
import org.isj.ing3.isi.webservice.webservicerest.service.IAnonymat;
import org.isj.ing3.isi.webservice.webservicerest.service.IModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/module")
@Slf4j
public class ModuleRestController {

	@Autowired
	private IModule iModule;

	@PostMapping("/save")
	public void enregistrer(@RequestBody Module create) throws IsjException {
		iModule.saveModule(create);
	}


	@GetMapping("/{code}/data")
	public ResponseEntity<Module> getModuleByCode(@PathVariable("code") Long code) throws IsjException {

		return ResponseEntity.ok(iModule.getModuleByCode(code));
	}


	@GetMapping("/all")
	public ResponseEntity<List<Module>> getAllModule() {
		return ResponseEntity.ok(iModule.listModules());
	}

	@GetMapping("/{code}/delete")
	public int deteleModule(@PathVariable("code") Long code) throws IsjException {
		return iModule.deleteModule(code);
	}

	@GetMapping("/{codeModule}/search")
	public ResponseEntity<List<Module>> getModuleByCodeModule(@PathVariable("codeModule") String codeModule) throws IsjException {
		return ResponseEntity.ok(iModule.getModuleByCodeModule(codeModule));
	}

}