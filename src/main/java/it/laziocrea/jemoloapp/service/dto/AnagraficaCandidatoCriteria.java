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
import io.github.jhipster.service.filter.LocalDateFilter;

/**
 * Criteria class for the {@link it.laziocrea.jemoloapp.domain.AnagraficaCandidato} entity. This class is used
 * in {@link it.laziocrea.jemoloapp.web.rest.AnagraficaCandidatoResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /anagrafica-candidatoes?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class AnagraficaCandidatoCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter cognome;

    private StringFilter nome;

    private StringFilter luogoNascita;

    private LocalDateFilter dataNascita;

    private StringFilter codiceFiscale;

    private StringFilter professione;

    private StringFilter partitaIva;

    private StringFilter numeroTelefonoFisso;

    private StringFilter numeroTelefonoCellulare;

    private StringFilter eMail;

    private StringFilter indirizzoPec;

    private StringFilter indirizzoResidenza;

    private StringFilter capResidenza;

    private StringFilter comuneResidenza;

    private StringFilter provinciaResidenza;

    private StringFilter note;

    private LongFilter candidatoId;

    private LongFilter competenzeLngId;

    private LongFilter titoloStudioId;

    private LongFilter curriculumId;

    private LongFilter competenzaId;

    private LongFilter dichiarazioniId;

    public AnagraficaCandidatoCriteria() {
    }

    public AnagraficaCandidatoCriteria(AnagraficaCandidatoCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.cognome = other.cognome == null ? null : other.cognome.copy();
        this.nome = other.nome == null ? null : other.nome.copy();
        this.luogoNascita = other.luogoNascita == null ? null : other.luogoNascita.copy();
        this.dataNascita = other.dataNascita == null ? null : other.dataNascita.copy();
        this.codiceFiscale = other.codiceFiscale == null ? null : other.codiceFiscale.copy();
        this.professione = other.professione == null ? null : other.professione.copy();
        this.partitaIva = other.partitaIva == null ? null : other.partitaIva.copy();
        this.numeroTelefonoFisso = other.numeroTelefonoFisso == null ? null : other.numeroTelefonoFisso.copy();
        this.numeroTelefonoCellulare = other.numeroTelefonoCellulare == null ? null : other.numeroTelefonoCellulare.copy();
        this.eMail = other.eMail == null ? null : other.eMail.copy();
        this.indirizzoPec = other.indirizzoPec == null ? null : other.indirizzoPec.copy();
        this.indirizzoResidenza = other.indirizzoResidenza == null ? null : other.indirizzoResidenza.copy();
        this.capResidenza = other.capResidenza == null ? null : other.capResidenza.copy();
        this.comuneResidenza = other.comuneResidenza == null ? null : other.comuneResidenza.copy();
        this.provinciaResidenza = other.provinciaResidenza == null ? null : other.provinciaResidenza.copy();
        this.note = other.note == null ? null : other.note.copy();
        this.candidatoId = other.candidatoId == null ? null : other.candidatoId.copy();
        this.competenzeLngId = other.competenzeLngId == null ? null : other.competenzeLngId.copy();
        this.titoloStudioId = other.titoloStudioId == null ? null : other.titoloStudioId.copy();
        this.curriculumId = other.curriculumId == null ? null : other.curriculumId.copy();
        this.competenzaId = other.competenzaId == null ? null : other.competenzaId.copy();
        this.dichiarazioniId = other.dichiarazioniId == null ? null : other.dichiarazioniId.copy();
    }

    @Override
    public AnagraficaCandidatoCriteria copy() {
        return new AnagraficaCandidatoCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getCognome() {
        return cognome;
    }

    public void setCognome(StringFilter cognome) {
        this.cognome = cognome;
    }

    public StringFilter getNome() {
        return nome;
    }

    public void setNome(StringFilter nome) {
        this.nome = nome;
    }

    public StringFilter getLuogoNascita() {
        return luogoNascita;
    }

    public void setLuogoNascita(StringFilter luogoNascita) {
        this.luogoNascita = luogoNascita;
    }

    public LocalDateFilter getDataNascita() {
        return dataNascita;
    }

    public void setDataNascita(LocalDateFilter dataNascita) {
        this.dataNascita = dataNascita;
    }

    public StringFilter getCodiceFiscale() {
        return codiceFiscale;
    }

    public void setCodiceFiscale(StringFilter codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }

    public StringFilter getProfessione() {
        return professione;
    }

    public void setProfessione(StringFilter professione) {
        this.professione = professione;
    }

    public StringFilter getPartitaIva() {
        return partitaIva;
    }

    public void setPartitaIva(StringFilter partitaIva) {
        this.partitaIva = partitaIva;
    }

    public StringFilter getNumeroTelefonoFisso() {
        return numeroTelefonoFisso;
    }

    public void setNumeroTelefonoFisso(StringFilter numeroTelefonoFisso) {
        this.numeroTelefonoFisso = numeroTelefonoFisso;
    }

    public StringFilter getNumeroTelefonoCellulare() {
        return numeroTelefonoCellulare;
    }

    public void setNumeroTelefonoCellulare(StringFilter numeroTelefonoCellulare) {
        this.numeroTelefonoCellulare = numeroTelefonoCellulare;
    }

    public StringFilter geteMail() {
        return eMail;
    }

    public void seteMail(StringFilter eMail) {
        this.eMail = eMail;
    }

    public StringFilter getIndirizzoPec() {
        return indirizzoPec;
    }

    public void setIndirizzoPec(StringFilter indirizzoPec) {
        this.indirizzoPec = indirizzoPec;
    }

    public StringFilter getIndirizzoResidenza() {
        return indirizzoResidenza;
    }

    public void setIndirizzoResidenza(StringFilter indirizzoResidenza) {
        this.indirizzoResidenza = indirizzoResidenza;
    }

    public StringFilter getCapResidenza() {
        return capResidenza;
    }

    public void setCapResidenza(StringFilter capResidenza) {
        this.capResidenza = capResidenza;
    }

    public StringFilter getComuneResidenza() {
        return comuneResidenza;
    }

    public void setComuneResidenza(StringFilter comuneResidenza) {
        this.comuneResidenza = comuneResidenza;
    }

    public StringFilter getProvinciaResidenza() {
        return provinciaResidenza;
    }

    public void setProvinciaResidenza(StringFilter provinciaResidenza) {
        this.provinciaResidenza = provinciaResidenza;
    }

    public StringFilter getNote() {
        return note;
    }

    public void setNote(StringFilter note) {
        this.note = note;
    }

    public LongFilter getCandidatoId() {
        return candidatoId;
    }

    public void setCandidatoId(LongFilter candidatoId) {
        this.candidatoId = candidatoId;
    }

    public LongFilter getCompetenzeLngId() {
        return competenzeLngId;
    }

    public void setCompetenzeLngId(LongFilter competenzeLngId) {
        this.competenzeLngId = competenzeLngId;
    }

    public LongFilter getTitoloStudioId() {
        return titoloStudioId;
    }

    public void setTitoloStudioId(LongFilter titoloStudioId) {
        this.titoloStudioId = titoloStudioId;
    }

    public LongFilter getCurriculumId() {
        return curriculumId;
    }

    public void setCurriculumId(LongFilter curriculumId) {
        this.curriculumId = curriculumId;
    }

    public LongFilter getCompetenzaId() {
        return competenzaId;
    }

    public void setCompetenzaId(LongFilter competenzaId) {
        this.competenzaId = competenzaId;
    }

    public LongFilter getDichiarazioniId() {
        return dichiarazioniId;
    }

    public void setDichiarazioniId(LongFilter dichiarazioniId) {
        this.dichiarazioniId = dichiarazioniId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final AnagraficaCandidatoCriteria that = (AnagraficaCandidatoCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(cognome, that.cognome) &&
            Objects.equals(nome, that.nome) &&
            Objects.equals(luogoNascita, that.luogoNascita) &&
            Objects.equals(dataNascita, that.dataNascita) &&
            Objects.equals(codiceFiscale, that.codiceFiscale) &&
            Objects.equals(professione, that.professione) &&
            Objects.equals(partitaIva, that.partitaIva) &&
            Objects.equals(numeroTelefonoFisso, that.numeroTelefonoFisso) &&
            Objects.equals(numeroTelefonoCellulare, that.numeroTelefonoCellulare) &&
            Objects.equals(eMail, that.eMail) &&
            Objects.equals(indirizzoPec, that.indirizzoPec) &&
            Objects.equals(indirizzoResidenza, that.indirizzoResidenza) &&
            Objects.equals(capResidenza, that.capResidenza) &&
            Objects.equals(comuneResidenza, that.comuneResidenza) &&
            Objects.equals(provinciaResidenza, that.provinciaResidenza) &&
            Objects.equals(note, that.note) &&
            Objects.equals(candidatoId, that.candidatoId) &&
            Objects.equals(competenzeLngId, that.competenzeLngId) &&
            Objects.equals(titoloStudioId, that.titoloStudioId) &&
            Objects.equals(curriculumId, that.curriculumId) &&
            Objects.equals(competenzaId, that.competenzaId) &&
            Objects.equals(dichiarazioniId, that.dichiarazioniId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        cognome,
        nome,
        luogoNascita,
        dataNascita,
        codiceFiscale,
        professione,
        partitaIva,
        numeroTelefonoFisso,
        numeroTelefonoCellulare,
        eMail,
        indirizzoPec,
        indirizzoResidenza,
        capResidenza,
        comuneResidenza,
        provinciaResidenza,
        note,
        candidatoId,
        competenzeLngId,
        titoloStudioId,
        curriculumId,
        competenzaId,
        dichiarazioniId
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AnagraficaCandidatoCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (cognome != null ? "cognome=" + cognome + ", " : "") +
                (nome != null ? "nome=" + nome + ", " : "") +
                (luogoNascita != null ? "luogoNascita=" + luogoNascita + ", " : "") +
                (dataNascita != null ? "dataNascita=" + dataNascita + ", " : "") +
                (codiceFiscale != null ? "codiceFiscale=" + codiceFiscale + ", " : "") +
                (professione != null ? "professione=" + professione + ", " : "") +
                (partitaIva != null ? "partitaIva=" + partitaIva + ", " : "") +
                (numeroTelefonoFisso != null ? "numeroTelefonoFisso=" + numeroTelefonoFisso + ", " : "") +
                (numeroTelefonoCellulare != null ? "numeroTelefonoCellulare=" + numeroTelefonoCellulare + ", " : "") +
                (eMail != null ? "eMail=" + eMail + ", " : "") +
                (indirizzoPec != null ? "indirizzoPec=" + indirizzoPec + ", " : "") +
                (indirizzoResidenza != null ? "indirizzoResidenza=" + indirizzoResidenza + ", " : "") +
                (capResidenza != null ? "capResidenza=" + capResidenza + ", " : "") +
                (comuneResidenza != null ? "comuneResidenza=" + comuneResidenza + ", " : "") +
                (provinciaResidenza != null ? "provinciaResidenza=" + provinciaResidenza + ", " : "") +
                (note != null ? "note=" + note + ", " : "") +
                (candidatoId != null ? "candidatoId=" + candidatoId + ", " : "") +
                (competenzeLngId != null ? "competenzeLngId=" + competenzeLngId + ", " : "") +
                (titoloStudioId != null ? "titoloStudioId=" + titoloStudioId + ", " : "") +
                (curriculumId != null ? "curriculumId=" + curriculumId + ", " : "") +
                (competenzaId != null ? "competenzaId=" + competenzaId + ", " : "") +
                (dichiarazioniId != null ? "dichiarazioniId=" + dichiarazioniId + ", " : "") +
            "}";
    }

}
