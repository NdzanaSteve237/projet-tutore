package org.isj.ing3.isi.webservice.webservicerest.repositories;

import org.isj.ing3.isi.webservice.webservicerest.model.entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
    @Query(value = "SELECT * FROM utilisateur u WHERE u.login=:login and u.mot_de_passe=:pw", nativeQuery = true)
    Optional<Utilisateur> getUserByLoginAndPw(@Param("login") String login, @Param("pw") String pw);


}