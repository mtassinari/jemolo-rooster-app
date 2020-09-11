package it.laziocrea.jemoloapp.service.dto;

import io.swagger.annotations.ApiModel;
import javax.validation.constraints.*;
import java.io.Serializable;

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
        if (!(o instanceof StatoRegistrazioneDTO)) {
            return false;
        }

        return id != null && id.equals(((StatoRegistrazioneDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "StatoRegistrazioneDTO{" +
            "id=" + getId() +
            ", stato='" + getStato() + "'" +
            "}";
    }
}
