package org.isj.ing3.isi.webservice.webservicerest.presentation.api;

import org.isj.ing3.isi.webservice.webservicerest.exception.IsjException;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Role;
import org.isj.ing3.isi.webservice.webservicerest.service.IRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/role")
public class RoleRestController {
    @Autowired
    private IRole iRole;

    @PostMapping("/save")
    public void enregistrer(@RequestBody Role create) {
        iRole.saveRole(create);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Role>> getAllNote() {
        return ResponseEntity.ok(iRole.listRole());
    }

    @GetMapping("/{code}/delete")
    public int deteleRole(@PathVariable("code") Long code) throws IsjException {
        return iRole.deleteRoleByCode(code);
    }
}
