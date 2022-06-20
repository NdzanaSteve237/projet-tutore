package org.isj.ing3.isi.webservice.webservicerest.repositories;

import org.isj.ing3.isi.webservice.webservicerest.model.entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
}