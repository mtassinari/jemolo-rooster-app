import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ICompetenzeLng, CompetenzeLng } from 'app/shared/model/competenze-lng.model';
import { CompetenzeLngService } from './competenze-lng.service';
import { ILingua } from 'app/shared/model/lingua.model';
import { LinguaService } from 'app/entities/lingua/lingua.service';
import { IAnagraficaCandidato } from 'app/shared/model/anagrafica-candidato.model';
import { AnagraficaCandidatoService } from 'app/entities/anagrafica-candidato/anagrafica-candidato.service';

type SelectableEntity = ILingua | IAnagraficaCandidato;

@Component({
  selector: 'jhi-competenze-lng-update',
  templateUrl: './competenze-lng-update.component.html',
})
export class CompetenzeLngUpdateComponent implements OnInit {
  isSaving = false;
  linguas: ILingua[] = [];
  anagraficacandidatoes: IAnagraficaCandidato[] = [];

  editForm = this.fb.group({
    id: [],
    livello: [null, [Validators.required]],
    linguaId: [null, Validators.required],
    anagraficaId: [null, Validators.required],
  });

  constructor(
    protected competenzeLngService: CompetenzeLngService,
    protected linguaService: LinguaService,
    protected anagraficaCandidatoService: AnagraficaCandidatoService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ competenzeLng }) => {
      this.updateForm(competenzeLng);

      this.linguaService.query().subscribe((res: HttpResponse<ILingua[]>) => (this.linguas = res.body || []));

      this.anagraficaCandidatoService
        .query()
        .subscribe((res: HttpResponse<IAnagraficaCandidato[]>) => (this.anagraficacandidatoes = res.body || []));
    });
  }

  updateForm(competenzeLng: ICompetenzeLng): void {
    this.editForm.patchValue({
      id: competenzeLng.id,
      livello: competenzeLng.livello,
      linguaId: competenzeLng.linguaId,
      anagraficaId: competenzeLng.anagraficaId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const competenzeLng = this.createFromForm();
    if (competenzeLng.id !== undefined) {
      this.subscribeToSaveResponse(this.competenzeLngService.update(competenzeLng));
    } else {
      this.subscribeToSaveResponse(this.competenzeLngService.create(competenzeLng));
    }
  }

  private createFromForm(): ICompetenzeLng {
    return {
      ...new CompetenzeLng(),
      id: this.editForm.get(['id'])!.value,
      livello: this.editForm.get(['livello'])!.value,
      linguaId: this.editForm.get(['linguaId'])!.value,
      anagraficaId: this.editForm.get(['anagraficaId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICompetenzeLng>>): void {
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
