package it.laziocrea.jemoloapp.service.dto;

import io.swagger.annotations.ApiModel;
import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link it.laziocrea.jemoloapp.domain.TitoloStudio} entity.
 */
@ApiModel(description = "Entity TitoloStudio\n@author Marco Tassinari")
public class TitoloStudioDTO implements Serializable {
    
    private Long id;

    @NotNull
    private String tipologia;

    @NotNull
    private String descrizione;

    @NotNull
    private String conseguimento;

    @NotNull
    private String anno;

    @NotNull
    private String voto;


    private Long anagraficaId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipologia() {
        return tipologia;
    }

    public void setTipologia(String tipologia) {
        this.tipologia = tipologia;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getConseguimento() {
        return conseguimento;
    }

    public void setConseguimento(String conseguimento) {
        this.conseguimento = conseguimento;
    }

    public String getAnno() {
        return anno;
    }

    public void setAnno(String anno) {
        this.anno = anno;
    }

    public String getVoto() {
        return voto;
    }

    public void setVoto(String voto) {
        this.voto = voto;
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
        if (!(o instanceof TitoloStudioDTO)) {
            return false;
        }

        return id != null && id.equals(((TitoloStudioDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TitoloStudioDTO{" +
            "id=" + getId() +
            ", tipologia='" + getTipologia() + "'" +
            ", descrizione='" + getDescrizione() + "'" +
            ", conseguimento='" + getConseguimento() + "'" +
            ", anno='" + getAnno() + "'" +
            ", voto='" + getVoto() + "'" +
            ", anagraficaId=" + getAnagraficaId() +
            "}";
    }
}
