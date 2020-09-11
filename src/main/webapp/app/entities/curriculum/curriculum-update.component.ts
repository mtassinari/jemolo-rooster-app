import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { ICurriculum, Curriculum } from 'app/shared/model/curriculum.model';
import { CurriculumService } from './curriculum.service';
import { IAllegato } from 'app/shared/model/allegato.model';
import { AllegatoService } from 'app/entities/allegato/allegato.service';
import { IAnagraficaCandidato } from 'app/shared/model/anagrafica-candidato.model';
import { AnagraficaCandidatoService } from 'app/entities/anagrafica-candidato/anagrafica-candidato.service';

type SelectableEntity = IAllegato | IAnagraficaCandidato;

@Component({
  selector: 'jhi-curriculum-update',
  templateUrl: './curriculum-update.component.html',
})
export class CurriculumUpdateComponent implements OnInit {
  isSaving = false;
  attaches: IAllegato[] = [];
  anagraficacandidatoes: IAnagraficaCandidato[] = [];

  editForm = this.fb.group({
    id: [],
    cv: [null, [Validators.required]],
    size: [null, [Validators.required]],
    urlAllegato: [null, [Validators.required]],
    mimeType: [],
    note: [],
    attachId: [null, Validators.required],
    anagraficaId: [null, Validators.required],
  });

  constructor(
    protected curriculumService: CurriculumService,
    protected allegatoService: AllegatoService,
    protected anagraficaCandidatoService: AnagraficaCandidatoService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ curriculum }) => {
      this.updateForm(curriculum);

      this.allegatoService
        .query({ filter: 'curriculum-is-null' })
        .pipe(
          map((res: HttpResponse<IAllegato[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IAllegato[]) => {
          if (!curriculum.attachId) {
            this.attaches = resBody;
          } else {
            this.allegatoService
              .find(curriculum.attachId)
              .pipe(
                map((subRes: HttpResponse<IAllegato>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IAllegato[]) => (this.attaches = concatRes));
          }
        });

      this.anagraficaCandidatoService
        .query()
        .subscribe((res: HttpResponse<IAnagraficaCandidato[]>) => (this.anagraficacandidatoes = res.body || []));
    });
  }

  updateForm(curriculum: ICurriculum): void {
    this.editForm.patchValue({
      id: curriculum.id,
      cv: curriculum.cv,
      size: curriculum.size,
      urlAllegato: curriculum.urlAllegato,
      mimeType: curriculum.mimeType,
      note: curriculum.note,
      attachId: curriculum.attachId,
      anagraficaId: curriculum.anagraficaId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const curriculum = this.createFromForm();
    if (curriculum.id !== undefined) {
      this.subscribeToSaveResponse(this.curriculumService.update(curriculum));
    } else {
      this.subscribeToSaveResponse(this.curriculumService.create(curriculum));
    }
  }

  private createFromForm(): ICurriculum {
    return {
      ...new Curriculum(),
      id: this.editForm.get(['id'])!.value,
      cv: this.editForm.get(['cv'])!.value,
      size: this.editForm.get(['size'])!.value,
      urlAllegato: this.editForm.get(['urlAllegato'])!.value,
      mimeType: this.editForm.get(['mimeType'])!.value,
      note: this.editForm.get(['note'])!.value,
      attachId: this.editForm.get(['attachId'])!.value,
      anagraficaId: this.editForm.get(['anagraficaId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICurriculum>>): void {
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
