package it.laziocrea.jemoloapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * Entity AnagraficaCandidato\n@author Marco Tassinari
 */
@Entity
@Table(name = "anagrafica_candidato")
public class AnagraficaCandidato implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "cognome", nullable = false)
    private String cognome;

    @NotNull
    @Column(name = "nome", nullable = false)
    private String nome;

    @NotNull
    @Column(name = "luogo_nascita", nullable = false)
    private String luogoNascita;

    @NotNull
    @Column(name = "data_nascita", nullable = false)
    private LocalDate dataNascita;

    @NotNull
    @Pattern(regexp = "^[a-zA-Z]{6}[0-9]{2}[abcdehlmprstABCDEHLMPRST]{1}[0-9]{2}([a-zA-Z]{1}[0-9]{3})[a-zA-Z]{1}$")
    @Column(name = "codice_fiscale", nullable = false)
    private String codiceFiscale;

    @NotNull
    @Column(name = "professione", nullable = false)
    private String professione;

    @Pattern(regexp = "^[0-9]{11}$")
    @Column(name = "partita_iva", unique = true)
    private String partitaIva;

    @Column(name = "numero_telefono_fisso")
    private String numeroTelefonoFisso;

    @NotNull
    @Column(name = "numero_telefono_cellulare", nullable = false)
    private String numeroTelefonoCellulare;

    @NotNull
    @Pattern(regexp = "^[A-z0-9.+_-]+@[A-z0-9._-]+.[A-z]{2,6}$")
    @Column(name = "e_mail", nullable = false, unique = true)
    private String eMail;

    @Pattern(regexp = "^[A-z0-9.+_-]+@[A-z0-9._-]+.[A-z]{2,6}$")
    @Column(name = "indirizzo_pec", unique = true)
    private String indirizzoPec;

    @NotNull
    @Column(name = "indirizzo_residenza", nullable = false)
    private String indirizzoResidenza;

    @NotNull
    @Column(name = "cap_residenza", nullable = false)
    private String capResidenza;

    @NotNull
    @Column(name = "comune_residenza", nullable = false)
    private String comuneResidenza;

    @NotNull
    @Column(name = "provincia_residenza", nullable = false)
    private String provinciaResidenza;

    @Column(name = "note")
    private String note;

    @OneToMany(mappedBy = "anagrafica")
    private Set<CompetenzeLng> competenzeLngs = new HashSet<>();

    @OneToMany(mappedBy = "anagrafica")
    private Set<TitoloStudio> titoloStudios = new HashSet<>();

    @OneToMany(mappedBy = "anagrafica")
    private Set<Curriculum> curricula = new HashSet<>();

    @OneToMany(mappedBy = "anagrafica")
    private Set<Competenza> competenzas = new HashSet<>();

    @OneToMany(mappedBy = "anagrafica")
    private Set<DichiarazioniObligatorie> dichiarazionis = new HashSet<>();

    @OneToOne(mappedBy = "anagraficaCandidato")
    @JsonIgnore
    private Candidato candidato;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCognome() {
        return cognome;
    }

    public AnagraficaCandidato cognome(String cognome) {
        this.cognome = cognome;
        return this;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getNome() {
        return nome;
    }

    public AnagraficaCandidato nome(String nome) {
        this.nome = nome;
        return this;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLuogoNascita() {
        return luogoNascita;
    }

    public AnagraficaCandidato luogoNascita(String luogoNascita) {
        this.luogoNascita = luogoNascita;
        return this;
    }

    public void setLuogoNascita(String luogoNascita) {
        this.luogoNascita = luogoNascita;
    }

    public LocalDate getDataNascita() {
        return dataNascita;
    }

    public AnagraficaCandidato dataNascita(LocalDate dataNascita) {
        this.dataNascita = dataNascita;
        return this;
    }

    public void setDataNascita(LocalDate dataNascita) {
        this.dataNascita = dataNascita;
    }

    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    public AnagraficaCandidato codiceFiscale(String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
        return this;
    }

    public void setCodiceFiscale(String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }

    public String getProfessione() {
        return professione;
    }

    public AnagraficaCandidato professione(String professione) {
        this.professione = professione;
        return this;
    }

    public void setProfessione(String professione) {
        this.professione = professione;
    }

    public String getPartitaIva() {
        return partitaIva;
    }

    public AnagraficaCandidato partitaIva(String partitaIva) {
        this.partitaIva = partitaIva;
        return this;
    }

    public void setPartitaIva(String partitaIva) {
        this.partitaIva = partitaIva;
    }

    public String getNumeroTelefonoFisso() {
        return numeroTelefonoFisso;
    }

    public AnagraficaCandidato numeroTelefonoFisso(String numeroTelefonoFisso) {
        this.numeroTelefonoFisso = numeroTelefonoFisso;
        return this;
    }

    public void setNumeroTelefonoFisso(String numeroTelefonoFisso) {
        this.numeroTelefonoFisso = numeroTelefonoFisso;
    }

    public String getNumeroTelefonoCellulare() {
        return numeroTelefonoCellulare;
    }

    public AnagraficaCandidato numeroTelefonoCellulare(String numeroTelefonoCellulare) {
        this.numeroTelefonoCellulare = numeroTelefonoCellulare;
        return this;
    }

    public void setNumeroTelefonoCellulare(String numeroTelefonoCellulare) {
        this.numeroTelefonoCellulare = numeroTelefonoCellulare;
    }

    public String geteMail() {
        return eMail;
    }

    public AnagraficaCandidato eMail(String eMail) {
        this.eMail = eMail;
        return this;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getIndirizzoPec() {
        return indirizzoPec;
    }

    public AnagraficaCandidato indirizzoPec(String indirizzoPec) {
        this.indirizzoPec = indirizzoPec;
        return this;
    }

    public void setIndirizzoPec(String indirizzoPec) {
        this.indirizzoPec = indirizzoPec;
    }

    public String getIndirizzoResidenza() {
        return indirizzoResidenza;
    }

    public AnagraficaCandidato indirizzoResidenza(String indirizzoResidenza) {
        this.indirizzoResidenza = indirizzoResidenza;
        return this;
    }

    public void setIndirizzoResidenza(String indirizzoResidenza) {
        this.indirizzoResidenza = indirizzoResidenza;
    }

    public String getCapResidenza() {
        return capResidenza;
    }

    public AnagraficaCandidato capResidenza(String capResidenza) {
        this.capResidenza = capResidenza;
        return this;
    }

    public void setCapResidenza(String capResidenza) {
        this.capResidenza = capResidenza;
    }

    public String getComuneResidenza() {
        return comuneResidenza;
    }

    public AnagraficaCandidato comuneResidenza(String comuneResidenza) {
        this.comuneResidenza = comuneResidenza;
        return this;
    }

    public void setComuneResidenza(String comuneResidenza) {
        this.comuneResidenza = comuneResidenza;
    }

    public String getProvinciaResidenza() {
        return provinciaResidenza;
    }

    public AnagraficaCandidato provinciaResidenza(String provinciaResidenza) {
        this.provinciaResidenza = provinciaResidenza;
        return this;
    }

    public void setProvinciaResidenza(String provinciaResidenza) {
        this.provinciaResidenza = provinciaResidenza;
    }

    public String getNote() {
        return note;
    }

    public AnagraficaCandidato note(String note) {
        this.note = note;
        return this;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Set<CompetenzeLng> getCompetenzeLngs() {
        return competenzeLngs;
    }

    public AnagraficaCandidato competenzeLngs(Set<CompetenzeLng> competenzeLngs) {
        this.competenzeLngs = competenzeLngs;
        return this;
    }

    public AnagraficaCandidato addCompetenzeLng(CompetenzeLng competenzeLng) {
        this.competenzeLngs.add(competenzeLng);
        competenzeLng.setAnagrafica(this);
        return this;
    }

    public AnagraficaCandidato removeCompetenzeLng(CompetenzeLng competenzeLng) {
        this.competenzeLngs.remove(competenzeLng);
        competenzeLng.setAnagrafica(null);
        return this;
    }

    public void setCompetenzeLngs(Set<CompetenzeLng> competenzeLngs) {
        this.competenzeLngs = competenzeLngs;
    }

    public Set<TitoloStudio> getTitoloStudios() {
        return titoloStudios;
    }

    public AnagraficaCandidato titoloStudios(Set<TitoloStudio> titoloStudios) {
        this.titoloStudios = titoloStudios;
        return this;
    }

    public AnagraficaCandidato addTitoloStudio(TitoloStudio titoloStudio) {
        this.titoloStudios.add(titoloStudio);
        titoloStudio.setAnagrafica(this);
        return this;
    }

    public AnagraficaCandidato removeTitoloStudio(TitoloStudio titoloStudio) {
        this.titoloStudios.remove(titoloStudio);
        titoloStudio.setAnagrafica(null);
        return this;
    }

    public void setTitoloStudios(Set<TitoloStudio> titoloStudios) {
        this.titoloStudios = titoloStudios;
    }

    public Set<Curriculum> getCurricula() {
        return curricula;
    }

    public AnagraficaCandidato curricula(Set<Curriculum> curricula) {
        this.curricula = curricula;
        return this;
    }

    public AnagraficaCandidato addCurriculum(Curriculum curriculum) {
        this.curricula.add(curriculum);
        curriculum.setAnagrafica(this);
        return this;
    }

    public AnagraficaCandidato removeCurriculum(Curriculum curriculum) {
        this.curricula.remove(curriculum);
        curriculum.setAnagrafica(null);
        return this;
    }

    public void setCurricula(Set<Curriculum> curricula) {
        this.curricula = curricula;
    }

    public Set<Competenza> getCompetenzas() {
        return competenzas;
    }

    public AnagraficaCandidato competenzas(Set<Competenza> competenzas) {
        this.competenzas = competenzas;
        return this;
    }

    public AnagraficaCandidato addCompetenza(Competenza competenza) {
        this.competenzas.add(competenza);
        competenza.setAnagrafica(this);
        return this;
    }

    public AnagraficaCandidato removeCompetenza(Competenza competenza) {
        this.competenzas.remove(competenza);
        competenza.setAnagrafica(null);
        return this;
    }

    public void setCompetenzas(Set<Competenza> competenzas) {
        this.competenzas = competenzas;
    }

    public Set<DichiarazioniObligatorie> getDichiarazionis() {
        return dichiarazionis;
    }

    public AnagraficaCandidato dichiarazionis(Set<DichiarazioniObligatorie> dichiarazioniObligatories) {
        this.dichiarazionis = dichiarazioniObligatories;
        return this;
    }

    public AnagraficaCandidato addDichiarazioni(DichiarazioniObligatorie dichiarazioniObligatorie) {
        this.dichiarazionis.add(dichiarazioniObligatorie);
        dichiarazioniObligatorie.setAnagrafica(this);
        return this;
    }

    public AnagraficaCandidato removeDichiarazioni(DichiarazioniObligatorie dichiarazioniObligatorie) {
        this.dichiarazionis.remove(dichiarazioniObligatorie);
        dichiarazioniObligatorie.setAnagrafica(null);
        return this;
    }

    public void setDichiarazionis(Set<DichiarazioniObligatorie> dichiarazioniObligatories) {
        this.dichiarazionis = dichiarazioniObligatories;
    }

    public Candidato getCandidato() {
        return candidato;
    }

    public AnagraficaCandidato candidato(Candidato candidato) {
        this.candidato = candidato;
        return this;
    }

    public void setCandidato(Candidato candidato) {
        this.candidato = candidato;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AnagraficaCandidato)) {
            return false;
        }
        return id != null && id.equals(((AnagraficaCandidato) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AnagraficaCandidato{" +
            "id=" + getId() +
            ", cognome='" + getCognome() + "'" +
            ", nome='" + getNome() + "'" +
            ", luogoNascita='" + getLuogoNascita() + "'" +
            ", dataNascita='" + getDataNascita() + "'" +
            ", codiceFiscale='" + getCodiceFiscale() + "'" +
            ", professione='" + getProfessione() + "'" +
            ", partitaIva='" + getPartitaIva() + "'" +
            ", numeroTelefonoFisso='" + getNumeroTelefonoFisso() + "'" +
            ", numeroTelefonoCellulare='" + getNumeroTelefonoCellulare() + "'" +
            ", eMail='" + geteMail() + "'" +
            ", indirizzoPec='" + getIndirizzoPec() + "'" +
            ", indirizzoResidenza='" + getIndirizzoResidenza() + "'" +
            ", capResidenza='" + getCapResidenza() + "'" +
            ", comuneResidenza='" + getComuneResidenza() + "'" +
            ", provinciaResidenza='" + getProvinciaResidenza() + "'" +
            ", note='" + getNote() + "'" +
            "}";
    }
}
