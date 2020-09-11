package it.laziocrea.jemoloapp.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link it.laziocrea.jemoloapp.domain.Dichiarazioni} entity.
 */
public class DichiarazioniDTO implements Serializable {
    
    private Long id;

    @NotNull
    private String descrizione;

    
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DichiarazioniDTO)) {
            return false;
        }

        return id != null && id.equals(((DichiarazioniDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DichiarazioniDTO{" +
            "id=" + getId() +
            ", descrizione='" + getDescrizione() + "'" +
            "}";
    }
}
