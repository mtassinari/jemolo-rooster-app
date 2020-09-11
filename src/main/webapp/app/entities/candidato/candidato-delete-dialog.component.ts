import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICandidato } from 'app/shared/model/candidato.model';
import { CandidatoService } from './candidato.service';

@Component({
  templateUrl: './candidato-delete-dialog.component.html',
})
export class CandidatoDeleteDialogComponent {
  candidato?: ICandidato;

  constructor(protected candidatoService: CandidatoService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.candidatoService.delete(id).subscribe(() => {
      this.eventManager.broadcast('candidatoListModification');
      this.activeModal.close();
    });
  }
}
