import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IProvincia, Provincia } from 'app/shared/model/provincia.model';
import { ProvinciaService } from './provincia.service';

@Component({
  selector: 'jhi-provincia-update',
  templateUrl: './provincia-update.component.html',
})
export class ProvinciaUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    sigla: [null, [Validators.required]],
    nome: [null, [Validators.required]],
  });

  constructor(protected provinciaService: ProvinciaService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ provincia }) => {
      this.updateForm(provincia);
    });
  }

  updateForm(provincia: IProvincia): void {
    this.editForm.patchValue({
      id: provincia.id,
      sigla: provincia.sigla,
      nome: provincia.nome,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const provincia = this.createFromForm();
    if (provincia.id !== undefined) {
      this.subscribeToSaveResponse(this.provinciaService.update(provincia));
    } else {
      this.subscribeToSaveResponse(this.provinciaService.create(provincia));
    }
  }

  private createFromForm(): IProvincia {
    return {
      ...new Provincia(),
      id: this.editForm.get(['id'])!.value,
      sigla: this.editForm.get(['sigla'])!.value,
      nome: this.editForm.get(['nome'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IProvincia>>): void {
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
