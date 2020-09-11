package it.laziocrea.jemoloapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * Entity Curriculum\n@author Marco Tassinari
 */
@Entity
@Table(name = "curriculum")
public class Curriculum implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "cv", nullable = false)
    private String cv;

    @NotNull
    @Column(name = "size", nullable = false)
    private Long size;

    @NotNull
    @Column(name = "url_allegato", nullable = false)
    private String urlAllegato;

    @Column(name = "mime_type")
    private String mimeType;

    @Column(name = "note")
    private String note;

    @OneToOne(optional = false)
    @NotNull
    @JoinColumn(unique = true)
    private Allegato attach;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "curricula", allowSetters = true)
    private AnagraficaCandidato anagrafica;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCv() {
        return cv;
    }

    public Curriculum cv(String cv) {
        this.cv = cv;
        return this;
    }

    public void setCv(String cv) {
        this.cv = cv;
    }

    public Long getSize() {
        return size;
    }

    public Curriculum size(Long size) {
        this.size = size;
        return this;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getUrlAllegato() {
        return urlAllegato;
    }

    public Curriculum urlAllegato(String urlAllegato) {
        this.urlAllegato = urlAllegato;
        return this;
    }

    public void setUrlAllegato(String urlAllegato) {
        this.urlAllegato = urlAllegato;
    }

    public String getMimeType() {
        return mimeType;
    }

    public Curriculum mimeType(String mimeType) {
        this.mimeType = mimeType;
        return this;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public String getNote() {
        return note;
    }

    public Curriculum note(String note) {
        this.note = note;
        return this;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Allegato getAttach() {
        return attach;
    }

    public Curriculum attach(Allegato allegato) {
        this.attach = allegato;
        return this;
    }

    public void setAttach(Allegato allegato) {
        this.attach = allegato;
    }

    public AnagraficaCandidato getAnagrafica() {
        return anagrafica;
    }

    public Curriculum anagrafica(AnagraficaCandidato anagraficaCandidato) {
        this.anagrafica = anagraficaCandidato;
        return this;
    }

    public void setAnagrafica(AnagraficaCandidato anagraficaCandidato) {
        this.anagrafica = anagraficaCandidato;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Curriculum)) {
            return false;
        }
        return id != null && id.equals(((Curriculum) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Curriculum{" +
            "id=" + getId() +
            ", cv='" + getCv() + "'" +
            ", size=" + getSize() +
            ", urlAllegato='" + getUrlAllegato() + "'" +
            ", mimeType='" + getMimeType() + "'" +
            ", note='" + getNote() + "'" +
            "}";
    }
}
