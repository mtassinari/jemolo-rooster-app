package it.laziocrea.jemoloapp.service.dto;

import java.io.Serializable;
import java.util.Objects;
import io.github.jhipster.service.Criteria;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;

/**
 * Criteria class for the {@link it.laziocrea.jemoloapp.domain.Candidato} entity. This class is used
 * in {@link it.laziocrea.jemoloapp.web.rest.CandidatoResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /candidatoes?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class CandidatoCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter nome;

    private StringFilter cognome;

    private StringFilter codiceFiscale;

    private StringFilter eMail;

    private LongFilter anagraficaCandidatoId;

    private LongFilter statoRegistrazioneId;

    public CandidatoCriteria() {
    }

    public CandidatoCriteria(CandidatoCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.nome = other.nome == null ? null : other.nome.copy();
        this.cognome = other.cognome == null ? null : other.cognome.copy();
        this.codiceFiscale = other.codiceFiscale == null ? null : other.codiceFiscale.copy();
        this.eMail = other.eMail == null ? null : other.eMail.copy();
        this.anagraficaCandidatoId = other.anagraficaCandidatoId == null ? null : other.anagraficaCandidatoId.copy();
        this.statoRegistrazioneId = other.statoRegistrazioneId == null ? null : other.statoRegistrazioneId.copy();
    }

    @Override
    public CandidatoCriteria copy() {
        return new CandidatoCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getNome() {
        return nome;
    }

    public void setNome(StringFilter nome) {
        this.nome = nome;
    }

    public StringFilter getCognome() {
        return cognome;
    }

    public void setCognome(StringFilter cognome) {
        this.cognome = cognome;
    }

    public StringFilter getCodiceFiscale() {
        return codiceFiscale;
    }

    public void setCodiceFiscale(StringFilter codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }

    public StringFilter geteMail() {
        return eMail;
    }

    public void seteMail(StringFilter eMail) {
        this.eMail = eMail;
    }

    public LongFilter getAnagraficaCandidatoId() {
        return anagraficaCandidatoId;
    }

    public void setAnagraficaCandidatoId(LongFilter anagraficaCandidatoId) {
        this.anagraficaCandidatoId = anagraficaCandidatoId;
    }

    public LongFilter getStatoRegistrazioneId() {
        return statoRegistrazioneId;
    }

    public void setStatoRegistrazioneId(LongFilter statoRegistrazioneId) {
        this.statoRegistrazioneId = statoRegistrazioneId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final CandidatoCriteria that = (CandidatoCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(nome, that.nome) &&
            Objects.equals(cognome, that.cognome) &&
            Objects.equals(codiceFiscale, that.codiceFiscale) &&
            Objects.equals(eMail, that.eMail) &&
            Objects.equals(anagraficaCandidatoId, that.anagraficaCandidatoId) &&
            Objects.equals(statoRegistrazioneId, that.statoRegistrazioneId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        nome,
        cognome,
        codiceFiscale,
        eMail,
        anagraficaCandidatoId,
        statoRegistrazioneId
        );
    }

    @Override
    public String toString() {
        return "CandidatoCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (nome != null ? "nome=" + nome + ", " : "") +
                (cognome != null ? "cognome=" + cognome + ", " : "") +
                (codiceFiscale != null ? "codiceFiscale=" + codiceFiscale + ", " : "") +
                (eMail != null ? "eMail=" + eMail + ", " : "") +
                (anagraficaCandidatoId != null ? "anagraficaCandidatoId=" + anagraficaCandidatoId + ", " : "") +
                (statoRegistrazioneId != null ? "statoRegistrazioneId=" + statoRegistrazioneId + ", " : "") +
            "}";
    }

}
