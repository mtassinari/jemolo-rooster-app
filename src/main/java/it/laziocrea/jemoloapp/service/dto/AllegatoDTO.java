package it.laziocrea.jemoloapp.service.dto;

import io.swagger.annotations.ApiModel;
import javax.validation.constraints.*;
import java.io.Serializable;
import javax.persistence.Lob;

/**
 * A DTO for the {@link it.laziocrea.jemoloapp.domain.Allegato} entity.
 */
@ApiModel(description = "Entity Allegato\n@author Marco Tassinari")
public class AllegatoDTO implements Serializable {
    
    private Long id;

    
    @Lob
    private byte[] data;

    private String dataContentType;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public String getDataContentType() {
        return dataContentType;
    }

    public void setDataContentType(String dataContentType) {
        this.dataContentType = dataContentType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AllegatoDTO)) {
            return false;
        }

        return id != null && id.equals(((AllegatoDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AllegatoDTO{" +
            "id=" + getId() +
            ", data='" + getData() + "'" +
            "}";
    }
}
