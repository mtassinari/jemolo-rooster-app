package it.laziocrea.jemoloapp.service.dto;

import io.swagger.annotations.ApiModel;
import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link it.laziocrea.jemoloapp.domain.Candidato} entity.
 */
@ApiModel(description = "Entity Candidato\n@author Marco Tassinari")
public class CandidatoDTO implements Serializable {
    
    private Long id;

    @NotNull
    private String nome;

    @NotNull
    private String cognome;

    @NotNull
    @Pattern(regexp = "^[a-zA-Z]{6}[0-9]{2}[abcdehlmprstABCDEHLMPRST]{1}[0-9]{2}([a-zA-Z]{1}[0-9]{3})[a-zA-Z]{1}$")
    private String codiceFiscale;

    @NotNull
    @Pattern(regexp = "^[A-z0-9.+_-]+@[A-z0-9._-]+.[A-z]{2,6}$")
    private String eMail;


    private Long anagraficaCandidatoId;

    private Long statoRegistrazioneId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    public void setCodiceFiscale(String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public Long getAnagraficaCandidatoId() {
        return anagraficaCandidatoId;
    }

    public void setAnagraficaCandidatoId(Long anagraficaCandidatoId) {
        this.anagraficaCandidatoId = anagraficaCandidatoId;
    }

    public Long getStatoRegistrazioneId() {
        return statoRegistrazioneId;
    }

    public void setStatoRegistrazioneId(Long statoRegistrazioneId) {
        this.statoRegistrazioneId = statoRegistrazioneId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CandidatoDTO)) {
            return false;
        }

        return id != null && id.equals(((CandidatoDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CandidatoDTO{" +
            "id=" + getId() +
            ", nome='" + getNome() + "'" +
            ", cognome='" + getCognome() + "'" +
            ", codiceFiscale='" + getCodiceFiscale() + "'" +
            ", eMail='" + geteMail() + "'" +
            ", anagraficaCandidatoId=" + getAnagraficaCandidatoId() +
            ", statoRegistrazioneId=" + getStatoRegistrazioneId() +
            "}";
    }
}
