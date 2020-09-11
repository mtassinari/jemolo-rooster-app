package it.laziocrea.jemoloapp.service.dto;

import io.swagger.annotations.ApiModel;
import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link it.laziocrea.jemoloapp.domain.Curriculum} entity.
 */
@ApiModel(description = "Entity Curriculum\n@author Marco Tassinari")
public class CurriculumDTO implements Serializable {
    
    private Long id;

    @NotNull
    private String cv;

    @NotNull
    private Long size;

    @NotNull
    private String urlAllegato;

    private String mimeType;

    private String note;


    private Long attachId;

    private Long anagraficaId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCv() {
        return cv;
    }

    public void setCv(String cv) {
        this.cv = cv;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getUrlAllegato() {
        return urlAllegato;
    }

    public void setUrlAllegato(String urlAllegato) {
        this.urlAllegato = urlAllegato;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Long getAttachId() {
        return attachId;
    }

    public void setAttachId(Long allegatoId) {
        this.attachId = allegatoId;
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
        if (!(o instanceof CurriculumDTO)) {
            return false;
        }

        return id != null && id.equals(((CurriculumDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CurriculumDTO{" +
            "id=" + getId() +
            ", cv='" + getCv() + "'" +
            ", size=" + getSize() +
            ", urlAllegato='" + getUrlAllegato() + "'" +
            ", mimeType='" + getMimeType() + "'" +
            ", note='" + getNote() + "'" +
            ", attachId=" + getAttachId() +
            ", anagraficaId=" + getAnagraficaId() +
            "}";
    }
}
