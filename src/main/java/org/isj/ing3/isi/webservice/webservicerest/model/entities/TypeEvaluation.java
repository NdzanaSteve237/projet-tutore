package org.isj.ing3.isi.webservice.webservicerest.model.entities;


/**
 * importation des classes
 */
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Enseignement;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Evaluation;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Securite;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * cette classe crée la table TypeEvaluation dans la base de données
 * cette classe herite de la classe Securite
 * @author traitement metier
 */

@Entity
@Table(name ="type_evaluation")


public class TypeEvaluation extends Securite implements Serializable {

    @Column(name ="pourcentage")
    private float pourcentage;

    @ManyToOne
    @JoinColumn(name="enseignement")
    private Enseignement enseignement;
/*
    @OneToMany(mappedBy = "typeEvaluation",cascade = {CascadeType.REFRESH,CascadeType.PERSIST,CascadeType.MERGE})
    private List <Evaluation> evaluations = new ArrayList<>();*/

    public TypeEvaluation(String libelle, String description, float pourcentage, Enseignement enseignement) {
        super(libelle, description);
        this.pourcentage = pourcentage;
        this.enseignement = enseignement;
    }

    public TypeEvaluation() {
    }

    public float getPourcentage() {
        return pourcentage;
    }

    public void setPourcentage(float pourcentage) {
        this.pourcentage = pourcentage;
    }

    public Enseignement getEnseignement() {
        return enseignement;
    }

    public void setEnseignement(Enseignement enseignement) {
        this.enseignement = enseignement;
    }



    /*@Override
    public String getLibelle(){
        return enseignement.getLibelle() + "-" + pourcentage;
    }*/
    @Override
    public void setSignature() {
        setSignature(String.valueOf(hashCode()));
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getPourcentage(), getEnseignement());
    }

    @Override
    public String toString() {
        return getLibelle()+" - "+getPourcentage()+"% - "+getEnseignement();
    }
}

