package it.laziocrea.jemoloapp.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link it.laziocrea.jemoloapp.domain.DichiarazioniObligatorie} entity.
 */
public class DichiarazioniObligatorieDTO implements Serializable {
    
    private Long id;

    @NotNull
    private Boolean stato;

    @NotNull
    private String dichiarazione;


    private Long anagraficaId;

    private Long dichiarazioniId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean isStato() {
        return stato;
    }

    public void setStato(Boolean stato) {
        this.stato = stato;
    }

    public String getDichiarazione() {
        return dichiarazione;
    }

    public void setDichiarazione(String dichiarazione) {
        this.dichiarazione = dichiarazione;
    }

    public Long getAnagraficaId() {
        return anagraficaId;
    }

    public void setAnagraficaId(Long anagraficaCandidatoId) {
        this.anagraficaId = anagraficaCandidatoId;
    }

    public Long getDichiarazioniId() {
        return dichiarazioniId;
    }

    public void setDichiarazioniId(Long dichiarazioniId) {
        this.dichiarazioniId = dichiarazioniId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DichiarazioniObligatorieDTO)) {
            return false;
        }

        return id != null && id.equals(((DichiarazioniObligatorieDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DichiarazioniObligatorieDTO{" +
            "id=" + getId() +
            ", stato='" + isStato() + "'" +
            ", dichiarazione='" + getDichiarazione() + "'" +
            ", anagraficaId=" + getAnagraficaId() +
            ", dichiarazioniId=" + getDichiarazioniId() +
            "}";
    }
}
