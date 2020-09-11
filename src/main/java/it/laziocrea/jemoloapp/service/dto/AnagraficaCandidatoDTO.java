package it.laziocrea.jemoloapp.service.dto;

import io.swagger.annotations.ApiModel;
import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link it.laziocrea.jemoloapp.domain.AnagraficaCandidato} entity.
 */
@ApiModel(description = "Entity AnagraficaCandidato\n@author Marco Tassinari")
public class AnagraficaCandidatoDTO implements Serializable {

    private Long id;

    @NotNull
    private String cognome;

    @NotNull
    private String nome;

    @NotNull
    private String luogoNascita;

    @NotNull
    private LocalDate dataNascita;

    @NotNull
    @Pattern(regexp = "^[a-zA-Z]{6}[0-9]{2}[abcdehlmprstABCDEHLMPRST]{1}[0-9]{2}([a-zA-Z]{1}[0-9]{3})[a-zA-Z]{1}$")
    private String codiceFiscale;

    @NotNull
    private String professione;

    @Pattern(regexp = "^[0-9]{11}$")
    private String partitaIva;

    private String numeroTelefonoFisso;

    @NotNull
    private String numeroTelefonoCellulare;

    @NotNull
    @Pattern(regexp = "^[A-z0-9.+_-]+@[A-z0-9._-]+.[A-z]{2,6}$")
    private String eMail;

    @Pattern(regexp = "^[A-z0-9.+_-]+@[A-z0-9._-]+.[A-z]{2,6}$")
    private String indirizzoPec;

    @NotNull
    private String indirizzoResidenza;

    @NotNull
    private String capResidenza;

    @NotNull
    private String comuneResidenza;

    @NotNull
    private String provinciaResidenza;

    private String note;


    private Long candidatoId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLuogoNascita() {
        return luogoNascita;
    }

    public void setLuogoNascita(String luogoNascita) {
        this.luogoNascita = luogoNascita;
    }

    public LocalDate getDataNascita() {
        return dataNascita;
    }

    public void setDataNascita(LocalDate dataNascita) {
        this.dataNascita = dataNascita;
    }

    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    public void setCodiceFiscale(String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }

    public String getProfessione() {
        return professione;
    }

    public void setProfessione(String professione) {
        this.professione = professione;
    }

    public String getPartitaIva() {
        return partitaIva;
    }

    public void setPartitaIva(String partitaIva) {
        this.partitaIva = partitaIva;
    }

    public String getNumeroTelefonoFisso() {
        return numeroTelefonoFisso;
    }

    public void setNumeroTelefonoFisso(String numeroTelefonoFisso) {
        this.numeroTelefonoFisso = numeroTelefonoFisso;
    }

    public String getNumeroTelefonoCellulare() {
        return numeroTelefonoCellulare;
    }

    public void setNumeroTelefonoCellulare(String numeroTelefonoCellulare) {
        this.numeroTelefonoCellulare = numeroTelefonoCellulare;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getIndirizzoPec() {
        return indirizzoPec;
    }

    public void setIndirizzoPec(String indirizzoPec) {
        this.indirizzoPec = indirizzoPec;
    }

    public String getIndirizzoResidenza() {
        return indirizzoResidenza;
    }

    public void setIndirizzoResidenza(String indirizzoResidenza) {
        this.indirizzoResidenza = indirizzoResidenza;
    }

    public String getCapResidenza() {
        return capResidenza;
    }

    public void setCapResidenza(String capResidenza) {
        this.capResidenza = capResidenza;
    }

    public String getComuneResidenza() {
        return comuneResidenza;
    }

    public void setComuneResidenza(String comuneResidenza) {
        this.comuneResidenza = comuneResidenza;
    }

    public String getProvinciaResidenza() {
        return provinciaResidenza;
    }

    public void setProvinciaResidenza(String provinciaResidenza) {
        this.provinciaResidenza = provinciaResidenza;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Long getCandidatoId() {
        return candidatoId;
    }

    public void setCandidatoId(Long candidatoId) {
        this.candidatoId = candidatoId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AnagraficaCandidatoDTO anagraficaCandidatoDTO = (AnagraficaCandidatoDTO) o;
        if (anagraficaCandidatoDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), anagraficaCandidatoDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AnagraficaCandidatoDTO{" +
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
            ", candidatoId=" + getCandidatoId() +
            "}";
    }
}
