package org.isj.ing3.isi.webservice.webservicerest.repositories;

import org.isj.ing3.isi.webservice.webservicerest.model.entities.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {

    @Query (value = "SELECT * FROM Note n WHERE n.est_inscrit=:inscrit AND n.evaluation=:eval", nativeQuery = true)
    public Optional <List<Note>> retrouverNote(@Param("inscrit") Long inscrit, @Param("eval") Long eval);

    @Query(value = "select * from note limit 5", nativeQuery = true)
    List<Note>  listeNotes();

}