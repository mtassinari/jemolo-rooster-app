import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ICompetenza, Competenza } from 'app/shared/model/competenza.model';
import { CompetenzaService } from './competenza.service';
import { IAnagraficaCandidato } from 'app/shared/model/anagrafica-candidato.model';
import { AnagraficaCandidatoService } from 'app/entities/anagrafica-candidato/anagrafica-candidato.service';
import { IAmbitoCompetenza } from 'app/shared/model/ambito-competenza.model';
import { AmbitoCompetenzaService } from 'app/entities/ambito-competenza/ambito-competenza.service';

type SelectableEntity = IAnagraficaCandidato | IAmbitoCompetenza;

@Component({
  selector: 'jhi-competenza-update',
  templateUrl: './competenza-update.component.html'
})
export class CompetenzaUpdateComponent implements OnInit {
  isSaving = false;
  anagraficacandidatoes: IAnagraficaCandidato[] = [];
  ambitocompetenzas: IAmbitoCompetenza[] = [];

  editForm = this.fb.group({
    id: [],
    anni: [null, [Validators.required]],
    anagraficaId: [null, Validators.required],
    ambitoCompId: [null, Validators.required]
  });

  constructor(
    protected competenzaService: CompetenzaService,
    protected anagraficaCandidatoService: AnagraficaCandidatoService,
    protected ambitoCompetenzaService: AmbitoCompetenzaService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ competenza }) => {
      this.updateForm(competenza);

      this.anagraficaCandidatoService
        .query()
        .subscribe((res: HttpResponse<IAnagraficaCandidato[]>) => (this.anagraficacandidatoes = res.body || []));

      this.ambitoCompetenzaService.query().subscribe((res: HttpResponse<IAmbitoCompetenza[]>) => (this.ambitocompetenzas = res.body || []));
    });
  }

  updateForm(competenza: ICompetenza): void {
    this.editForm.patchValue({
      id: competenza.id,
      anni: competenza.anni,
      anagraficaId: competenza.anagraficaId,
      ambitoCompId: competenza.ambitoCompId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const competenza = this.createFromForm();
    if (competenza.id !== undefined) {
      this.subscribeToSaveResponse(this.competenzaService.update(competenza));
    } else {
      this.subscribeToSaveResponse(this.competenzaService.create(competenza));
    }
  }

  private createFromForm(): ICompetenza {
    return {
      ...new Competenza(),
      id: this.editForm.get(['id'])!.value,
      anni: this.editForm.get(['anni'])!.value,
      anagraficaId: this.editForm.get(['anagraficaId'])!.value,
      ambitoCompId: this.editForm.get(['ambitoCompId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICompetenza>>): void {
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
