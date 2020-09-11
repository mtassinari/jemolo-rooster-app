import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IComune } from 'app/shared/model/comune.model';
import { ComuneService } from './comune.service';

@Component({
  templateUrl: './comune-delete-dialog.component.html',
})
export class ComuneDeleteDialogComponent {
  comune?: IComune;

  constructor(protected comuneService: ComuneService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.comuneService.delete(id).subscribe(() => {
      this.eventManager.broadcast('comuneListModification');
      this.activeModal.close();
    });
  }
}
