import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IStatoRegistrazione, StatoRegistrazione } from 'app/shared/model/stato-registrazione.model';
import { StatoRegistrazioneService } from './stato-registrazione.service';

@Component({
  selector: 'jhi-stato-registrazione-update',
  templateUrl: './stato-registrazione-update.component.html'
})
export class StatoRegistrazioneUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    stato: [null, [Validators.required]]
  });

  constructor(
    protected statoRegistrazioneService: StatoRegistrazioneService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ statoRegistrazione }) => {
      this.updateForm(statoRegistrazione);
    });
  }

  updateForm(statoRegistrazione: IStatoRegistrazione): void {
    this.editForm.patchValue({
      id: statoRegistrazione.id,
      stato: statoRegistrazione.stato
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const statoRegistrazione = this.createFromForm();
    if (statoRegistrazione.id !== undefined) {
      this.subscribeToSaveResponse(this.statoRegistrazioneService.update(statoRegistrazione));
    } else {
      this.subscribeToSaveResponse(this.statoRegistrazioneService.create(statoRegistrazione));
    }
  }

  private createFromForm(): IStatoRegistrazione {
    return {
      ...new StatoRegistrazione(),
      id: this.editForm.get(['id'])!.value,
      stato: this.editForm.get(['stato'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IStatoRegistrazione>>): void {
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
