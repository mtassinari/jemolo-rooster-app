import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IAllegato } from 'app/shared/model/allegato.model';
import { AllegatoService } from './allegato.service';

@Component({
  templateUrl: './allegato-delete-dialog.component.html',
})
export class AllegatoDeleteDialogComponent {
  allegato?: IAllegato;

  constructor(protected allegatoService: AllegatoService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.allegatoService.delete(id).subscribe(() => {
      this.eventManager.broadcast('allegatoListModification');
      this.activeModal.close();
    });
  }
}
