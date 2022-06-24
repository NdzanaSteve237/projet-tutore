package org.isj.ing3.isi.webservice.webservicerest.service;

import org.isj.ing3.isi.webservice.webservicerest.exception.IsjException;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Role;

import java.util.List;

public interface IRole {
    Long saveRole(Role roleDto);
    List<Role> listRole();
    int deleteRoleByCode(Long code) throws IsjException;


}
