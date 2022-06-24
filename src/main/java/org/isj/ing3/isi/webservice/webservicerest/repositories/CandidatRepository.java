package org.isj.ing3.isi.webservice.webservicerest.repositories;

import org.isj.ing3.isi.webservice.webservicerest.model.entities.Candidat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CandidatRepository extends JpaRepository<Candidat, Long> {
    public Optional<Candidat> findCandidatByEmail(String email);
    @Query(value="SELECT * FROM Candidat c WHERE c.telephone=:telephone", nativeQuery = true)
    public Candidat retrouverCandidatSms(@Param("telephone") int telephone);
}