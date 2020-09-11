package it.laziocrea.jemoloapp.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link it.laziocrea.jemoloapp.domain.Lingua} entity.
 */
public class LinguaDTO implements Serializable {
    
    private Long id;

    @NotNull
    private String lingua;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLingua() {
        return lingua;
    }

    public void setLingua(String lingua) {
        this.lingua = lingua;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LinguaDTO)) {
            return false;
        }

        return id != null && id.equals(((LinguaDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "LinguaDTO{" +
            "id=" + getId() +
            ", lingua='" + getLingua() + "'" +
            "}";
    }
}
