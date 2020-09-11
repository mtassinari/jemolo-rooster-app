import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IAmbitoCompetenza, AmbitoCompetenza } from 'app/shared/model/ambito-competenza.model';
import { AmbitoCompetenzaService } from './ambito-competenza.service';

@Component({
  selector: 'jhi-ambito-competenza-update',
  templateUrl: './ambito-competenza-update.component.html',
})
export class AmbitoCompetenzaUpdateComponent implements OnInit {
  isSaving = false;
  ambitocompetenzas: IAmbitoCompetenza[] = [];

  editForm = this.fb.group({
    id: [],
    descrizione: [null, [Validators.required]],
    tipo: [],
    ambitoId: [],
  });

  constructor(
    protected ambitoCompetenzaService: AmbitoCompetenzaService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ ambitoCompetenza }) => {
      this.updateForm(ambitoCompetenza);

      this.ambitoCompetenzaService.query().subscribe((res: HttpResponse<IAmbitoCompetenza[]>) => (this.ambitocompetenzas = res.body || []));
    });
  }

  updateForm(ambitoCompetenza: IAmbitoCompetenza): void {
    this.editForm.patchValue({
      id: ambitoCompetenza.id,
      descrizione: ambitoCompetenza.descrizione,
      tipo: ambitoCompetenza.tipo,
      ambitoId: ambitoCompetenza.ambitoId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const ambitoCompetenza = this.createFromForm();
    if (ambitoCompetenza.id !== undefined) {
      this.subscribeToSaveResponse(this.ambitoCompetenzaService.update(ambitoCompetenza));
    } else {
      this.subscribeToSaveResponse(this.ambitoCompetenzaService.create(ambitoCompetenza));
    }
  }

  private createFromForm(): IAmbitoCompetenza {
    return {
      ...new AmbitoCompetenza(),
      id: this.editForm.get(['id'])!.value,
      descrizione: this.editForm.get(['descrizione'])!.value,
      tipo: this.editForm.get(['tipo'])!.value,
      ambitoId: this.editForm.get(['ambitoId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IAmbitoCompetenza>>): void {
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

  trackById(index: number, item: IAmbitoCompetenza): any {
    return item.id;
  }
}
