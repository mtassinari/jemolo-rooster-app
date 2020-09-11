import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IAnagraficaCandidato, AnagraficaCandidato } from 'app/shared/model/anagrafica-candidato.model';
import { AnagraficaCandidatoService } from './anagrafica-candidato.service';

@Component({
  selector: 'jhi-anagrafica-candidato-update',
  templateUrl: './anagrafica-candidato-update.component.html',
})
export class AnagraficaCandidatoUpdateComponent implements OnInit {
  isSaving = false;
  dataNascitaDp: any;

  editForm = this.fb.group({
    id: [],
    cognome: [null, [Validators.required]],
    nome: [null, [Validators.required]],
    luogoNascita: [null, [Validators.required]],
    dataNascita: [null, [Validators.required]],
    codiceFiscale: [
      null,
      [
        Validators.required,
        Validators.pattern('^[a-zA-Z]{6}[0-9]{2}[abcdehlmprstABCDEHLMPRST]{1}[0-9]{2}([a-zA-Z]{1}[0-9]{3})[a-zA-Z]{1}$'),
      ],
    ],
    professione: [null, [Validators.required]],
    partitaIva: [null, [Validators.pattern('^[0-9]{11}$')]],
    numeroTelefonoFisso: [],
    numeroTelefonoCellulare: [null, [Validators.required]],
    eMail: [null, [Validators.required, Validators.pattern('^[A-z0-9.+_-]+@[A-z0-9._-]+.[A-z]{2,6}$')]],
    indirizzoPec: [null, [Validators.pattern('^[A-z0-9.+_-]+@[A-z0-9._-]+.[A-z]{2,6}$')]],
    indirizzoResidenza: [null, [Validators.required]],
    capResidenza: [null, [Validators.required]],
    comuneResidenza: [null, [Validators.required]],
    provinciaResidenza: [null, [Validators.required]],
    note: [],
  });

  constructor(
    protected anagraficaCandidatoService: AnagraficaCandidatoService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ anagraficaCandidato }) => {
      this.updateForm(anagraficaCandidato);
    });
  }

  updateForm(anagraficaCandidato: IAnagraficaCandidato): void {
    this.editForm.patchValue({
      id: anagraficaCandidato.id,
      cognome: anagraficaCandidato.cognome,
      nome: anagraficaCandidato.nome,
      luogoNascita: anagraficaCandidato.luogoNascita,
      dataNascita: anagraficaCandidato.dataNascita,
      codiceFiscale: anagraficaCandidato.codiceFiscale,
      professione: anagraficaCandidato.professione,
      partitaIva: anagraficaCandidato.partitaIva,
      numeroTelefonoFisso: anagraficaCandidato.numeroTelefonoFisso,
      numeroTelefonoCellulare: anagraficaCandidato.numeroTelefonoCellulare,
      eMail: anagraficaCandidato.eMail,
      indirizzoPec: anagraficaCandidato.indirizzoPec,
      indirizzoResidenza: anagraficaCandidato.indirizzoResidenza,
      capResidenza: anagraficaCandidato.capResidenza,
      comuneResidenza: anagraficaCandidato.comuneResidenza,
      provinciaResidenza: anagraficaCandidato.provinciaResidenza,
      note: anagraficaCandidato.note,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const anagraficaCandidato = this.createFromForm();
    if (anagraficaCandidato.id !== undefined) {
      this.subscribeToSaveResponse(this.anagraficaCandidatoService.update(anagraficaCandidato));
    } else {
      this.subscribeToSaveResponse(this.anagraficaCandidatoService.create(anagraficaCandidato));
    }
  }

  private createFromForm(): IAnagraficaCandidato {
    return {
      ...new AnagraficaCandidato(),
      id: this.editForm.get(['id'])!.value,
      cognome: this.editForm.get(['cognome'])!.value,
      nome: this.editForm.get(['nome'])!.value,
      luogoNascita: this.editForm.get(['luogoNascita'])!.value,
      dataNascita: this.editForm.get(['dataNascita'])!.value,
      codiceFiscale: this.editForm.get(['codiceFiscale'])!.value,
      professione: this.editForm.get(['professione'])!.value,
      partitaIva: this.editForm.get(['partitaIva'])!.value,
      numeroTelefonoFisso: this.editForm.get(['numeroTelefonoFisso'])!.value,
      numeroTelefonoCellulare: this.editForm.get(['numeroTelefonoCellulare'])!.value,
      eMail: this.editForm.get(['eMail'])!.value,
      indirizzoPec: this.editForm.get(['indirizzoPec'])!.value,
      indirizzoResidenza: this.editForm.get(['indirizzoResidenza'])!.value,
      capResidenza: this.editForm.get(['capResidenza'])!.value,
      comuneResidenza: this.editForm.get(['comuneResidenza'])!.value,
      provinciaResidenza: this.editForm.get(['provinciaResidenza'])!.value,
      note: this.editForm.get(['note'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IAnagraficaCandidato>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }
}
