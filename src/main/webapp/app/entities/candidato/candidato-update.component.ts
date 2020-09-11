import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { ICandidato, Candidato } from 'app/shared/model/candidato.model';
import { CandidatoService } from './candidato.service';
import { IAnagraficaCandidato } from 'app/shared/model/anagrafica-candidato.model';
import { AnagraficaCandidatoService } from 'app/entities/anagrafica-candidato/anagrafica-candidato.service';
import { IStatoRegistrazione } from 'app/shared/model/stato-registrazione.model';
import { StatoRegistrazioneService } from 'app/entities/stato-registrazione/stato-registrazione.service';

type SelectableEntity = IAnagraficaCandidato | IStatoRegistrazione;

@Component({
  selector: 'jhi-candidato-update',
  templateUrl: './candidato-update.component.html',
})
export class CandidatoUpdateComponent implements OnInit {
  isSaving = false;
  anagraficacandidatoes: IAnagraficaCandidato[] = [];
  statoregistraziones: IStatoRegistrazione[] = [];

  editForm = this.fb.group({
    id: [],
    nome: [null, [Validators.required]],
    cognome: [null, [Validators.required]],
    codiceFiscale: [
      null,
      [
        Validators.required,
        Validators.pattern('^[a-zA-Z]{6}[0-9]{2}[abcdehlmprstABCDEHLMPRST]{1}[0-9]{2}([a-zA-Z]{1}[0-9]{3})[a-zA-Z]{1}$'),
      ],
    ],
    eMail: [null, [Validators.required, Validators.pattern('^[A-z0-9.+_-]+@[A-z0-9._-]+.[A-z]{2,6}$')]],
    anagraficaCandidatoId: [],
    statoRegistrazioneId: [],
  });

  constructor(
    protected candidatoService: CandidatoService,
    protected anagraficaCandidatoService: AnagraficaCandidatoService,
    protected statoRegistrazioneService: StatoRegistrazioneService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ candidato }) => {
      this.updateForm(candidato);

      this.anagraficaCandidatoService
        .query({ 'candidatoId.specified': 'false' })
        .pipe(
          map((res: HttpResponse<IAnagraficaCandidato[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IAnagraficaCandidato[]) => {
          if (!candidato.anagraficaCandidatoId) {
            this.anagraficacandidatoes = resBody;
          } else {
            this.anagraficaCandidatoService
              .find(candidato.anagraficaCandidatoId)
              .pipe(
                map((subRes: HttpResponse<IAnagraficaCandidato>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IAnagraficaCandidato[]) => (this.anagraficacandidatoes = concatRes));
          }
        });

      this.statoRegistrazioneService
        .query()
        .subscribe((res: HttpResponse<IStatoRegistrazione[]>) => (this.statoregistraziones = res.body || []));
    });
  }

  updateForm(candidato: ICandidato): void {
    this.editForm.patchValue({
      id: candidato.id,
      nome: candidato.nome,
      cognome: candidato.cognome,
      codiceFiscale: candidato.codiceFiscale,
      eMail: candidato.eMail,
      anagraficaCandidatoId: candidato.anagraficaCandidatoId,
      statoRegistrazioneId: candidato.statoRegistrazioneId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const candidato = this.createFromForm();
    if (candidato.id !== undefined) {
      this.subscribeToSaveResponse(this.candidatoService.update(candidato));
    } else {
      this.subscribeToSaveResponse(this.candidatoService.create(candidato));
    }
  }

  private createFromForm(): ICandidato {
    return {
      ...new Candidato(),
      id: this.editForm.get(['id'])!.value,
      nome: this.editForm.get(['nome'])!.value,
      cognome: this.editForm.get(['cognome'])!.value,
      codiceFiscale: this.editForm.get(['codiceFiscale'])!.value,
      eMail: this.editForm.get(['eMail'])!.value,
      anagraficaCandidatoId: this.editForm.get(['anagraficaCandidatoId'])!.value,
      statoRegistrazioneId: this.editForm.get(['statoRegistrazioneId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICandidato>>): void {
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

  trackById(index: number, item: SelectableEntity): any {
    return item.id;
  }
}
