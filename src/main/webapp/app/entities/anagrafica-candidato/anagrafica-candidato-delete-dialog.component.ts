import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IAnagraficaCandidato } from 'app/shared/model/anagrafica-candidato.model';
import { AnagraficaCandidatoService } from './anagrafica-candidato.service';

@Component({
  templateUrl: './anagrafica-candidato-delete-dialog.component.html',
})
export class AnagraficaCandidatoDeleteDialogComponent {
  anagraficaCandidato?: IAnagraficaCandidato;

  constructor(
    protected anagraficaCandidatoService: AnagraficaCandidatoService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.anagraficaCandidatoService.delete(id).subscribe(() => {
      this.eventManager.broadcast('anagraficaCandidatoListModification');
      this.activeModal.close();
    });
  }
}
