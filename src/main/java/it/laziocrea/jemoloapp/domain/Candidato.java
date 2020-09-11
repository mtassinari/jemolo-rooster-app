package it.laziocrea.jemoloapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * Entity Candidato\n@author Marco Tassinari
 */
@Entity
@Table(name = "candidato")
public class Candidato implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "nome", nullable = false)
    private String nome;

    @NotNull
    @Column(name = "cognome", nullable = false)
    private String cognome;

    @NotNull
    @Pattern(regexp = "^[a-zA-Z]{6}[0-9]{2}[abcdehlmprstABCDEHLMPRST]{1}[0-9]{2}([a-zA-Z]{1}[0-9]{3})[a-zA-Z]{1}$")
    @Column(name = "codice_fiscale", nullable = false)
    private String codiceFiscale;

    @NotNull
    @Pattern(regexp = "^[A-z0-9.+_-]+@[A-z0-9._-]+.[A-z]{2,6}$")
    @Column(name = "e_mail", nullable = false, unique = true)
    private String eMail;

    @OneToOne(mappedBy = "candidato")
    @JsonIgnore
    private AnagraficaCandidato anagraficaCandidato;

    @ManyToOne
    @JsonIgnoreProperties(value = "candidatoes", allowSetters = true)
    private StatoRegistrazione statoRegistrazione;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public Candidato nome(String nome) {
        this.nome = nome;
        return this;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public Candidato cognome(String cognome) {
        this.cognome = cognome;
        return this;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    public Candidato codiceFiscale(String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
        return this;
    }

    public void setCodiceFiscale(String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }

    public String geteMail() {
        return eMail;
    }

    public Candidato eMail(String eMail) {
        this.eMail = eMail;
        return this;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public AnagraficaCandidato getAnagraficaCandidato() {
        return anagraficaCandidato;
    }

    public Candidato anagraficaCandidato(AnagraficaCandidato anagraficaCandidato) {
        this.anagraficaCandidato = anagraficaCandidato;
        return this;
    }

    public void setAnagraficaCandidato(AnagraficaCandidato anagraficaCandidato) {
        this.anagraficaCandidato = anagraficaCandidato;
    }

    public StatoRegistrazione getStatoRegistrazione() {
        return statoRegistrazione;
    }

    public Candidato statoRegistrazione(StatoRegistrazione statoRegistrazione) {
        this.statoRegistrazione = statoRegistrazione;
        return this;
    }

    public void setStatoRegistrazione(StatoRegistrazione statoRegistrazione) {
        this.statoRegistrazione = statoRegistrazione;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Candidato)) {
            return false;
        }
        return id != null && id.equals(((Candidato) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Candidato{" +
            "id=" + getId() +
            ", nome='" + getNome() + "'" +
            ", cognome='" + getCognome() + "'" +
            ", codiceFiscale='" + getCodiceFiscale() + "'" +
            ", eMail='" + geteMail() + "'" +
            "}";
    }
}
