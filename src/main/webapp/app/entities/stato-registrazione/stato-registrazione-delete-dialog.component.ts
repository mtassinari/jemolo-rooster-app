import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IStatoRegistrazione } from 'app/shared/model/stato-registrazione.model';
import { StatoRegistrazioneService } from './stato-registrazione.service';

@Component({
  templateUrl: './stato-registrazione-delete-dialog.component.html',
})
export class StatoRegistrazioneDeleteDialogComponent {
  statoRegistrazione?: IStatoRegistrazione;

  constructor(
    protected statoRegistrazioneService: StatoRegistrazioneService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.statoRegistrazioneService.delete(id).subscribe(() => {
      this.eventManager.broadcast('statoRegistrazioneListModification');
      this.activeModal.close();
    });
  }
}
