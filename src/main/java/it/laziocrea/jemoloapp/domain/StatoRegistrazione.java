package it.laziocrea.jemoloapp.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Entity StatoRegistrazione\n@author Marco Tassinari
 */
@Entity
@Table(name = "stato_registrazione")
public class StatoRegistrazione implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "stato", nullable = false, unique = true)
    private String stato;

    @OneToMany(mappedBy = "statoRegistrazione")
    private Set<Candidato> candidatoes = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStato() {
        return stato;
    }

    public StatoRegistrazione stato(String stato) {
        this.stato = stato;
        return this;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }

    public Set<Candidato> getCandidatoes() {
        return candidatoes;
    }

    public StatoRegistrazione candidatoes(Set<Candidato> candidatoes) {
        this.candidatoes = candidatoes;
        return this;
    }

    public StatoRegistrazione addCandidato(Candidato candidato) {
        this.candidatoes.add(candidato);
        candidato.setStatoRegistrazione(this);
        return this;
    }

    public StatoRegistrazione removeCandidato(Candidato candidato) {
        this.candidatoes.remove(candidato);
        candidato.setStatoRegistrazione(null);
        return this;
    }

    public void setCandidatoes(Set<Candidato> candidatoes) {
        this.candidatoes = candidatoes;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof StatoRegistrazione)) {
            return false;
        }
        return id != null && id.equals(((StatoRegistrazione) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "StatoRegistrazione{" +
            "id=" + getId() +
            ", stato='" + getStato() + "'" +
            "}";
    }
}
