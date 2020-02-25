package it.laziocrea.jemoloapp.service.dto;

import io.swagger.annotations.ApiModel;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link it.laziocrea.jemoloapp.domain.StatoRegistrazione} entity.
 */
@ApiModel(description = "Entity StatoRegistrazione\n@author Marco Tassinari")
public class StatoRegistrazioneDTO implements Serializable {

    private Long id;

    @NotNull
    private String stato;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStato() {
        return stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        StatoRegistrazioneDTO statoRegistrazioneDTO = (StatoRegistrazioneDTO) o;
        if (statoRegistrazioneDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), statoRegistrazioneDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "StatoRegistrazioneDTO{" +
            "id=" + getId() +
            ", stato='" + getStato() + "'" +
            "}";
    }
}
