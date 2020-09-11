package it.laziocrea.jemoloapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * Entity Competenza\n@author Marco Tassinari
 */
@Entity
@Table(name = "competenza")
public class Competenza implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "anni", nullable = false)
    private Integer anni;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("competenzas")
    private AnagraficaCandidato anagrafica;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("competenzas")
    private AmbitoCompetenza ambitoComp;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAnni() {
        return anni;
    }

    public Competenza anni(Integer anni) {
        this.anni = anni;
        return this;
    }

    public void setAnni(Integer anni) {
        this.anni = anni;
    }

    public AnagraficaCandidato getAnagrafica() {
        return anagrafica;
    }

    public Competenza anagrafica(AnagraficaCandidato anagraficaCandidato) {
        this.anagrafica = anagraficaCandidato;
        return this;
    }

    public void setAnagrafica(AnagraficaCandidato anagraficaCandidato) {
        this.anagrafica = anagraficaCandidato;
    }

    public AmbitoCompetenza getAmbitoComp() {
        return ambitoComp;
    }

    public Competenza ambitoComp(AmbitoCompetenza ambitoCompetenza) {
        this.ambitoComp = ambitoCompetenza;
        return this;
    }

    public void setAmbitoComp(AmbitoCompetenza ambitoCompetenza) {
        this.ambitoComp = ambitoCompetenza;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Competenza)) {
            return false;
        }
        return id != null && id.equals(((Competenza) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Competenza{" +
            "id=" + getId() +
            ", anni=" + getAnni() +
            "}";
    }
}
