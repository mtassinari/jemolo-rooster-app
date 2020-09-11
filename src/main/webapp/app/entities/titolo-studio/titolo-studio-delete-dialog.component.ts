import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITitoloStudio } from 'app/shared/model/titolo-studio.model';
import { TitoloStudioService } from './titolo-studio.service';

@Component({
  templateUrl: './titolo-studio-delete-dialog.component.html',
})
export class TitoloStudioDeleteDialogComponent {
  titoloStudio?: ITitoloStudio;

  constructor(
    protected titoloStudioService: TitoloStudioService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.titoloStudioService.delete(id).subscribe(() => {
      this.eventManager.broadcast('titoloStudioListModification');
      this.activeModal.close();
    });
  }
}
