package it.laziocrea.jemoloapp.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

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
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        LinguaDTO linguaDTO = (LinguaDTO) o;
        if (linguaDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), linguaDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "LinguaDTO{" +
            "id=" + getId() +
            ", lingua='" + getLingua() + "'" +
            "}";
    }
}
