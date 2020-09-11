import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICompetenza } from 'app/shared/model/competenza.model';
import { CompetenzaService } from './competenza.service';

@Component({
  templateUrl: './competenza-delete-dialog.component.html',
})
export class CompetenzaDeleteDialogComponent {
  competenza?: ICompetenza;

  constructor(
    protected competenzaService: CompetenzaService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.competenzaService.delete(id).subscribe(() => {
      this.eventManager.broadcast('competenzaListModification');
      this.activeModal.close();
    });
  }
}
