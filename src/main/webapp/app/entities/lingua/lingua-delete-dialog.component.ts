import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ILingua } from 'app/shared/model/lingua.model';
import { LinguaService } from './lingua.service';

@Component({
  templateUrl: './lingua-delete-dialog.component.html'
})
export class LinguaDeleteDialogComponent {
  lingua?: ILingua;

  constructor(protected linguaService: LinguaService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.linguaService.delete(id).subscribe(() => {
      this.eventManager.broadcast('linguaListModification');
      this.activeModal.close();
    });
  }
}
