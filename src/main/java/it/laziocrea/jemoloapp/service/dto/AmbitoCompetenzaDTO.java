package it.laziocrea.jemoloapp.service.dto;

import io.swagger.annotations.ApiModel;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link it.laziocrea.jemoloapp.domain.AmbitoCompetenza} entity.
 */
@ApiModel(description = "Entity AreaCompetenza\n@author Marco Tassinari")
public class AmbitoCompetenzaDTO implements Serializable {

    private Long id;

    @NotNull
    private String descrizione;

    private String tipo;


    private Long ambitoId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Long getAmbitoId() {
        return ambitoId;
    }

    public void setAmbitoId(Long ambitoCompetenzaId) {
        this.ambitoId = ambitoCompetenzaId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AmbitoCompetenzaDTO ambitoCompetenzaDTO = (AmbitoCompetenzaDTO) o;
        if (ambitoCompetenzaDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), ambitoCompetenzaDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AmbitoCompetenzaDTO{" +
            "id=" + getId() +
            ", descrizione='" + getDescrizione() + "'" +
            ", tipo='" + getTipo() + "'" +
            ", ambitoId=" + getAmbitoId() +
            "}";
    }
}
