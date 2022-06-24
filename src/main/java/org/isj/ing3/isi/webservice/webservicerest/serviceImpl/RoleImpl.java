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

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class RoleImpl implements IRole {
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UtilisateurRepository utilisateurRepository;

    @Override
    public Long saveRole(Role role) throws IsjException {
        Utilisateur createur = utilisateurRepository.findById(role.getCreateur().getCode()).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));
        Utilisateur modificateur = createur;
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
        roleRepository.deleteById(getRoleByCode(code).getCode());
        return 1;
    }

    @Override
    public Long updateRole(Role role) throws IsjException {
        Utilisateur createur = utilisateurRepository.findById(role.getCreateur().getCode()).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));
        Utilisateur modificateur = utilisateurRepository.findById(role.getCreateur().getCode()).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));

        role.setCreateur(createur);
        role.setModificateur(modificateur);
        return roleRepository.save(role).getCode();
    }

    @Override
    public Role getRoleByCode(Long code) throws IsjException {
        return roleRepository.findById(code).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));
    }
}
