import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ILingua, Lingua } from 'app/shared/model/lingua.model';
import { LinguaService } from './lingua.service';

@Component({
  selector: 'jhi-lingua-update',
  templateUrl: './lingua-update.component.html'
})
export class LinguaUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    lingua: [null, [Validators.required]]
  });

  constructor(protected linguaService: LinguaService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ lingua }) => {
      this.updateForm(lingua);
    });
  }

  updateForm(lingua: ILingua): void {
    this.editForm.patchValue({
      id: lingua.id,
      lingua: lingua.lingua
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const lingua = this.createFromForm();
    if (lingua.id !== undefined) {
      this.subscribeToSaveResponse(this.linguaService.update(lingua));
    } else {
      this.subscribeToSaveResponse(this.linguaService.create(lingua));
    }
  }

  private createFromForm(): ILingua {
    return {
      ...new Lingua(),
      id: this.editForm.get(['id'])!.value,
      lingua: this.editForm.get(['lingua'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ILingua>>): void {
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
