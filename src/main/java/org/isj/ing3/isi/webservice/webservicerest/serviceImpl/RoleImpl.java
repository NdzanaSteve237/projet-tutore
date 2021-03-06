package org.isj.ing3.isi.webservice.webservicerest.serviceImpl;


import org.isj.ing3.isi.webservice.webservicerest.exception.ErrorInfo;
import org.isj.ing3.isi.webservice.webservicerest.exception.IsjException;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Role;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Utilisateur;
import org.isj.ing3.isi.webservice.webservicerest.repositories.RoleRepository;
import org.isj.ing3.isi.webservice.webservicerest.repositories.UtilisateurRepository;
import org.isj.ing3.isi.webservice.webservicerest.service.IRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleImpl implements IRole {
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UtilisateurRepository utilisateurRepository;

    @Override
    public Long saveRole(Role role) {
        Utilisateur createur = utilisateurRepository.findById(role.getCreateur().getCode()).get();
        Utilisateur modificateur = utilisateurRepository.findById(role.getCreateur().getCode()).get();

        role.setCreateur(createur);
        role.setModificateur(modificateur);

        return roleRepository.save(role).getCode();
    }

    @Override
    public List<Role> listRole() {
       return roleRepository.findAll();
    }

    @Override
    public int deleteRoleByCode(Long code) throws IsjException {
        roleRepository.deleteById(roleRepository.findById(code).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND)).getCode());
        return 1;
    }
}
