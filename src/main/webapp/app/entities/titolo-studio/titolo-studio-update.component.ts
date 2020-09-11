import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ITitoloStudio, TitoloStudio } from 'app/shared/model/titolo-studio.model';
import { TitoloStudioService } from './titolo-studio.service';
import { IAnagraficaCandidato } from 'app/shared/model/anagrafica-candidato.model';
import { AnagraficaCandidatoService } from 'app/entities/anagrafica-candidato/anagrafica-candidato.service';

@Component({
  selector: 'jhi-titolo-studio-update',
  templateUrl: './titolo-studio-update.component.html',
})
export class TitoloStudioUpdateComponent implements OnInit {
  isSaving = false;
  anagraficacandidatoes: IAnagraficaCandidato[] = [];

  editForm = this.fb.group({
    id: [],
    tipologia: [null, [Validators.required]],
    descrizione: [null, [Validators.required]],
    conseguimento: [null, [Validators.required]],
    anno: [null, [Validators.required]],
    voto: [null, [Validators.required]],
    anagraficaId: [null, Validators.required],
  });

  constructor(
    protected titoloStudioService: TitoloStudioService,
    protected anagraficaCandidatoService: AnagraficaCandidatoService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ titoloStudio }) => {
      this.updateForm(titoloStudio);

      this.anagraficaCandidatoService
        .query()
        .subscribe((res: HttpResponse<IAnagraficaCandidato[]>) => (this.anagraficacandidatoes = res.body || []));
    });
  }

  updateForm(titoloStudio: ITitoloStudio): void {
    this.editForm.patchValue({
      id: titoloStudio.id,
      tipologia: titoloStudio.tipologia,
      descrizione: titoloStudio.descrizione,
      conseguimento: titoloStudio.conseguimento,
      anno: titoloStudio.anno,
      voto: titoloStudio.voto,
      anagraficaId: titoloStudio.anagraficaId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const titoloStudio = this.createFromForm();
    if (titoloStudio.id !== undefined) {
      this.subscribeToSaveResponse(this.titoloStudioService.update(titoloStudio));
    } else {
      this.subscribeToSaveResponse(this.titoloStudioService.create(titoloStudio));
    }
  }

  private createFromForm(): ITitoloStudio {
    return {
      ...new TitoloStudio(),
      id: this.editForm.get(['id'])!.value,
      tipologia: this.editForm.get(['tipologia'])!.value,
      descrizione: this.editForm.get(['descrizione'])!.value,
      conseguimento: this.editForm.get(['conseguimento'])!.value,
      anno: this.editForm.get(['anno'])!.value,
      voto: this.editForm.get(['voto'])!.value,
      anagraficaId: this.editForm.get(['anagraficaId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITitoloStudio>>): void {
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

  trackById(index: number, item: IAnagraficaCandidato): any {
    return item.id;
  }
}
