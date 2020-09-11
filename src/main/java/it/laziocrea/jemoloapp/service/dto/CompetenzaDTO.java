package it.laziocrea.jemoloapp.service.dto;

import io.swagger.annotations.ApiModel;
import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link it.laziocrea.jemoloapp.domain.Competenza} entity.
 */
@ApiModel(description = "Entity Competenza\n@author Marco Tassinari")
public class CompetenzaDTO implements Serializable {
    
    private Long id;

    @NotNull
    private Integer anni;


    private Long anagraficaId;

    private Long ambitoCompId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAnni() {
        return anni;
    }

    public void setAnni(Integer anni) {
        this.anni = anni;
    }

    public Long getAnagraficaId() {
        return anagraficaId;
    }

    public void setAnagraficaId(Long anagraficaCandidatoId) {
        this.anagraficaId = anagraficaCandidatoId;
    }

    public Long getAmbitoCompId() {
        return ambitoCompId;
    }

    public void setAmbitoCompId(Long ambitoCompetenzaId) {
        this.ambitoCompId = ambitoCompetenzaId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CompetenzaDTO)) {
            return false;
        }

        return id != null && id.equals(((CompetenzaDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CompetenzaDTO{" +
            "id=" + getId() +
            ", anni=" + getAnni() +
            ", anagraficaId=" + getAnagraficaId() +
            ", ambitoCompId=" + getAmbitoCompId() +
            "}";
    }
}
