import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IAmbitoCompetenza } from 'app/shared/model/ambito-competenza.model';
import { AmbitoCompetenzaService } from './ambito-competenza.service';

@Component({
  templateUrl: './ambito-competenza-delete-dialog.component.html'
})
export class AmbitoCompetenzaDeleteDialogComponent {
  ambitoCompetenza?: IAmbitoCompetenza;

  constructor(
    protected ambitoCompetenzaService: AmbitoCompetenzaService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.ambitoCompetenzaService.delete(id).subscribe(() => {
      this.eventManager.broadcast('ambitoCompetenzaListModification');
      this.activeModal.close();
    });
  }
}
