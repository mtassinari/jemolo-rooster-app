import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICurriculum } from 'app/shared/model/curriculum.model';
import { CurriculumService } from './curriculum.service';

@Component({
  templateUrl: './curriculum-delete-dialog.component.html',
})
export class CurriculumDeleteDialogComponent {
  curriculum?: ICurriculum;

  constructor(
    protected curriculumService: CurriculumService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.curriculumService.delete(id).subscribe(() => {
      this.eventManager.broadcast('curriculumListModification');
      this.activeModal.close();
    });
  }
}
