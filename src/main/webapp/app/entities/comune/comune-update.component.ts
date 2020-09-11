import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IComune, Comune } from 'app/shared/model/comune.model';
import { ComuneService } from './comune.service';

@Component({
  selector: 'jhi-comune-update',
  templateUrl: './comune-update.component.html',
})
export class ComuneUpdateComponent implements OnInit {
  isSaving = false;
  comunes: IComune[] = [];

  editForm = this.fb.group({
    id: [],
    nome: [null, [Validators.required]],
    nomeProvincia: [null, [Validators.required]],
    siglaProvincia: [null, [Validators.required]],
    provincia: [],
  });

  constructor(protected comuneService: ComuneService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ comune }) => {
      this.updateForm(comune);

      this.comuneService.query().subscribe((res: HttpResponse<IComune[]>) => (this.comunes = res.body || []));
    });
  }

  updateForm(comune: IComune): void {
    this.editForm.patchValue({
      id: comune.id,
      nome: comune.nome,
      nomeProvincia: comune.nomeProvincia,
      siglaProvincia: comune.siglaProvincia,
      provincia: comune.provincia,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const comune = this.createFromForm();
    if (comune.id !== undefined) {
      this.subscribeToSaveResponse(this.comuneService.update(comune));
    } else {
      this.subscribeToSaveResponse(this.comuneService.create(comune));
    }
  }

  private createFromForm(): IComune {
    return {
      ...new Comune(),
      id: this.editForm.get(['id'])!.value,
      nome: this.editForm.get(['nome'])!.value,
      nomeProvincia: this.editForm.get(['nomeProvincia'])!.value,
      siglaProvincia: this.editForm.get(['siglaProvincia'])!.value,
      provincia: this.editForm.get(['provincia'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IComune>>): void {
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

  trackById(index: number, item: IComune): any {
    return item.id;
  }
}
