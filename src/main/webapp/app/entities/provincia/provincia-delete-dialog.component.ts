import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IProvincia } from 'app/shared/model/provincia.model';
import { ProvinciaService } from './provincia.service';

@Component({
  templateUrl: './provincia-delete-dialog.component.html',
})
export class ProvinciaDeleteDialogComponent {
  provincia?: IProvincia;

  constructor(protected provinciaService: ProvinciaService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.provinciaService.delete(id).subscribe(() => {
      this.eventManager.broadcast('provinciaListModification');
      this.activeModal.close();
    });
  }
}
