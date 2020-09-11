package it.laziocrea.jemoloapp.service.dto;

import io.swagger.annotations.ApiModel;
import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link it.laziocrea.jemoloapp.domain.CompetenzeLng} entity.
 */
@ApiModel(description = "Entity CompetenzeLinguistiche\n@author Marco Tassinari")
public class CompetenzeLngDTO implements Serializable {
    
    private Long id;

    @NotNull
    private Integer livello;


    private Long linguaId;

    private Long anagraficaId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getLivello() {
        return livello;
    }

    public void setLivello(Integer livello) {
        this.livello = livello;
    }

    public Long getLinguaId() {
        return linguaId;
    }

    public void setLinguaId(Long linguaId) {
        this.linguaId = linguaId;
    }

    public Long getAnagraficaId() {
        return anagraficaId;
    }

    public void setAnagraficaId(Long anagraficaCandidatoId) {
        this.anagraficaId = anagraficaCandidatoId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CompetenzeLngDTO)) {
            return false;
        }

        return id != null && id.equals(((CompetenzeLngDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CompetenzeLngDTO{" +
            "id=" + getId() +
            ", livello=" + getLivello() +
            ", linguaId=" + getLinguaId() +
            ", anagraficaId=" + getAnagraficaId() +
            "}";
    }
}
